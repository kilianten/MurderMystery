package state.game.ui;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;

public class UIGameMenu extends VerticalContainer {

    private UIText label;

    public UIGameMenu(Size windowSize, Input input, GameSettings settings) {
        super(windowSize);

        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        setBackgroundColor(Color.DARK_GRAY);
        addUIComponent(new UIText("PAUSED"));
        addUIComponent(new UIButton("Continue", (state) -> ((GameState) state).togglePause(false)));
        addUIComponent(new UIButton("Menu", (state) -> state.setNextState(new MenuState(windowSize, input, settings))));
        addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
    }
}
