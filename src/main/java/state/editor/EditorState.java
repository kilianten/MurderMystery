package state.editor;

import core.Size;
import entity.scenery.building.Building;
import game.Game;
import game.settings.GameSettings;
import input.Input;
import input.mouse.action.CameraMovement;
import input.mouse.action.ClearAction;
import input.mouse.action.SceneryTool;
import map.GameMap;
import map.location.Location;
import state.State;
import state.editor.ui.*;
import ui.UIText;

import java.util.ArrayList;
import java.util.List;

public class EditorState extends State {

    private UIRenderingSettings renderSettings;
    private boolean hasRequiredElements = false;
    private String[] requiredBuildings = {"church", "policeStation"};

    public EditorState(Size windowSize, Input input, GameSettings settings, Size mapSize) {
        super(windowSize, input, settings);
        gameMap = new GameMap(mapSize, spriteLibrary);
        setupMouseActions();
        setupUI(windowSize, settings);
        locations.get("Outside").setGameMap(this.gameMap);
    }

    private void setupMouseActions() {
        mouseHandler.switchPrimaryButtonAction(new SceneryTool());
        mouseHandler.setRightButtonAction(new ClearAction());
        mouseHandler.setMiddleButtonAction(new CameraMovement());
    }

    private void setupUI(Size windowSize, GameSettings settings) {
        uiContainers.add(new UIEditorMenu(windowSize));
        renderSettings = new UIRenderingSettings(windowSize, settings.getRenderSettings(), gameMap);
        uiContainers.add(renderSettings);
        uiContainers.add(new UIObjectMenu(windowSize, spriteLibrary));
    }

    @Override
    public void setDefaultSettings() {
        settings.getRenderSettings().getShouldRenderGrid().setValue(true);
        settings.getRenderSettings().getCollisionBox().setValue(true);
    }

    @Override
    public void loadGameMap(String filePath) {
        super.loadGameMap(filePath);
        renderSettings.resetMiniMap(gameMap);
    }

    public boolean hasRequiredElements() {
        return hasRequiredElements;
    }

    @Override
    public void update(Game game){
        super.update(game);
        checkRequiredElements();
    }

    private void checkRequiredElements() {
        List<Building> buildings = getGameObjectsOfClass(Building.class);
        List<String> buildingNames = new ArrayList<>();
        for(Building building: buildings){
            buildingNames.add(building.getName());
        }
        for(String building: requiredBuildings){
            if(buildingNames.contains(building)){
                hasRequiredElements = true;
                renderSettings.setMissingBuilding("");
            } else {
                renderSettings.setMissingBuilding("Missing: " + building);
                hasRequiredElements = false;
                break;
            }
        }

    }

}
