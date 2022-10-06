package state.menu;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import map.location.Location;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

public class MenuState extends State {
    public MenuState(Size windowSize, Input input, GameSettings settings) {
        super(windowSize, input, settings);
        loadGameMap(getClass().getResource("/maps/map.rim").getFile());
        settings.getRenderSettings().getCollisionBox().setValue(false);
        uiContainers.add(new UIMainMenu(windowSize));
        audioPlayer.playMusic("metal.wav");
        locations.get("Outside").setGameMap(this.gameMap);
    }

    @Override
    public void setDefaultSettings() {
        settings.getRenderSettings().getShouldRenderGrid().setValue(false);
        settings.getRenderSettings().getCollisionBox().setValue(false);
        settings.getRenderSettings().getPathable().setValue(false);
    }

    public void enterMenu(UIContainer container){
        uiContainers.clear();
        uiContainers.add(container);
    }
}
