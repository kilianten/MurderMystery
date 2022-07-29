package state.editor;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import state.State;
import state.editor.ui.UIEditorMenu;
import state.editor.ui.UIRenderingSettings;

public class EditorState extends State {
    public EditorState(Size windowSize, Input input, GameSettings settings) {
        super(windowSize, input, settings);
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);

        uiContainers.add(new UIEditorMenu(windowSize));
        uiContainers.add(new UIRenderingSettings(windowSize, settings.getRenderSettings()));
    }
}
