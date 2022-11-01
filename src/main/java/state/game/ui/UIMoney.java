package state.game.ui;

import core.Size;
import state.game.GameState;
import ui.Alignment;
import ui.UIText;

import state.State;
import ui.VerticalContainer;

public class UIMoney extends VerticalContainer {

    private UIText money;

    public UIMoney(Size windowSize) {
        super(windowSize);
        this.alignment = new Alignment(Alignment.Position.END, Alignment.Position.START);
        this.money = new UIText("$0");
        addUIComponent(money);
    }

    @Override
    public void update(State state){
        super.update(state);
        money.setText("$" + (((GameState) state).getPlayer().getMoney()));
    }
}
