package state.editor;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import input.mouse.action.ClearAction;
import input.mouse.action.TilePlacer;
import map.GameMap;
import map.Tile;
import state.State;
import state.editor.ui.*;

public class EditorState extends State {

    public EditorState(Size windowSize, Input input, GameSettings settings) {
        super(windowSize, input, settings);
        gameMap = new GameMap(new Size(16, 32), spriteLibrary);
        mouseHandler.setPrimaryButtonAction(new TilePlacer(new Tile(spriteLibrary, "pathcenter")));
        mouseHandler.setRightButtonAction(new ClearAction());

        uiContainers.add(new UIEditorMenu(windowSize));
        uiContainers.add(new UIRenderingSettings(windowSize, settings.getRenderSettings(), gameMap));
        uiContainers.add(new UIObjectMenu(windowSize, spriteLibrary));

    }

    @Override
    public void setDefaultSettings() {
        settings.getRenderSettings().getShouldRenderGrid().setValue(true);
    }
}
