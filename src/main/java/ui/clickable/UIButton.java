package ui.clickable;

import core.Size;
import state.State;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

import java.awt.*;

public class UIButton extends UIClickable {

    private UIContainer container;
    private UIText label;

    private ClickAction clickAction;

    public UIButton(String label, ClickAction clickAction) {
        this(label, clickAction, new Size(150, 40));
    }

    public UIButton(String label, ClickAction clickAction, Size size) {
        this.label = new UIText(label, size.getHeight(), size.getHeight() - 15, true);
        this.clickAction = clickAction;

        container = new VerticalContainer(new Size(0, 0));
        container.setCenterChildren(true);
        container.addUIComponent(this.label);
        container.setFixedSize(size);
    }

    @Override
    public void update(State state) {
        super.update(state);
        container.update(state);
        size = container.getSize();

        Color color = Color.GRAY;

        if(hasFocus) {
            color = Color.LIGHT_GRAY;
        }

        if(isPressed) {
            color = Color.DARK_GRAY;
        }

        container.setBackgroundColor(color);
    }

    @Override
    protected void onFocus(State state) {
        state.getAudioPlayer().playSound("buttonSound.wav");
    }

    @Override
    public void onDrag(State state) {

    }

    @Override
    public void onClick(State state) {
        if(hasFocus){
            clickAction.execute(state);
        }
    }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }
}
