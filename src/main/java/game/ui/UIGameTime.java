package game.ui;

import core.Size;
import ui.Alignment;
import ui.UIText;

import game.state.State;
import ui.VerticalContainer;

public class UIGameTime extends VerticalContainer {

    private UIText gameTime;
    private UIText gameDay;


    public UIGameTime(Size windowSize) {
        super(windowSize);
        this.alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
        this.gameTime = new UIText("");
        this.gameDay = new UIText("");

        addUIComponent(gameDay);
        addUIComponent(gameTime);
    }

    @Override
    public void update(State state){
        super.update(state);
        gameTime.setText(state.getClock().getFormattedTime());
        gameDay.setText(state.getClock().getDay());
    }
}
