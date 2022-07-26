package state.menu.ui;

import core.Size;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

public class UIOptionMenu extends VerticalContainer {

    public UIOptionMenu(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        addUIComponent(new UIText("OPTIONS"));
        addUIComponent(new UIButton("BACK", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));
    }
}
