package state.menu.ui;

import core.Size;
import game.Game;
import state.editor.EditorState;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

public class UIMainMenu extends VerticalContainer {

    public UIMainMenu(Size windowSize) {
        super(windowSize);

        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        addUIComponent(new UIText(Game.GAME_TITLE, 35, 30, true));
        addUIComponent(new UIButton("Play", (state) -> state.setNextState(new GameState(windowSize, state.getInput(), state.getSettings()))));
        addUIComponent(new UIButton("Level Edit", (state) -> ((MenuState) state).enterMenu(new UIPreEditorMenu(windowSize, state.getSettings()))));
        addUIComponent(new UIButton("Options", (state) -> ((MenuState) state).enterMenu(new UIOptionMenu(windowSize, state.getSettings()))));
        addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));

    }

}
