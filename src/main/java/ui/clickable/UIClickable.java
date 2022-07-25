package ui.clickable;

import core.Position;
import game.state.State;
import ui.UIComponent;

import java.awt.*;

public abstract class UIClickable extends UIComponent {

    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void update(State state) {
        Position mousePosition = state.getInput().getMousePosition();

        hasFocus = getBounds().contains(mousePosition.getIntX(), mousePosition.getIntY());
        isPressed = hasFocus && state.getInput().isMousePressed();

        System.out.println("MOUSEY" + mousePosition.getIntY());

        if(hasFocus && state.getInput().isMouseClicked()) {
            onClick();
        }
    }

    protected abstract void onClick();

    private Rectangle getBounds() {
        return new Rectangle(
                absolutePosition.getIntX(),
                absolutePosition.getIntY(),
                size.getWidth(),
                size.getHeight()
        );
    }
}
