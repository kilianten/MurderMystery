package state.menu;

import core.Size;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

public class MenuState extends State {
    public MenuState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);

        uiContainers.add(new UIMainMenu(windowSize));
    }

    public void enterMenu(UIContainer container){
        uiContainers.clear();
        uiContainers.add(container);
    }
}
