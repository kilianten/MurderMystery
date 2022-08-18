package state.editor;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import input.mouse.action.CameraMovement;
import input.mouse.action.ClearAction;
import input.mouse.action.SceneryTool;
import map.GameMap;
import state.State;
import state.editor.ui.*;

public class EditorState extends State {

    private UIRenderingSettings renderSettings;

    public EditorState(Size windowSize, Input input, GameSettings settings, Size mapSize) {
        super(windowSize, input, settings);
        gameMap = new GameMap(mapSize, spriteLibrary);
        setupMouseActions();
        setupUI(windowSize, settings);
        settings.getRenderSettings().getCollisionBox().setValue(true);
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
    }

    @Override
    public void loadGameMap(String filePath) {
        super.loadGameMap(filePath);
        renderSettings.resetMiniMap(gameMap);
    }
}
