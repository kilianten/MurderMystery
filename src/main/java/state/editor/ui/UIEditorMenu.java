package state.editor.ui;

import core.Size;
import state.menu.MenuState;
import ui.HorizontalContainer;
import ui.clickable.UIButton;

public class UIEditorMenu extends HorizontalContainer {

    public UIEditorMenu(Size windowSize) {
        super(windowSize);
        addUIComponent(new UIButton("Main Menu", state -> state.setNextState(new MenuState(state.getCamera().getSize(), state.getInput(), state.getSettings()))));

    }
}
