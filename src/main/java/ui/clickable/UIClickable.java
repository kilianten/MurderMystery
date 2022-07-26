package ui.clickable;

import core.Position;
import state.State;
import ui.UIComponent;

import java.awt.*;

public abstract class UIClickable extends UIComponent {

    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void update(State state) {
        Position mousePosition = state.getInput().getMousePosition();
        boolean previousFocus = hasFocus;
        
        hasFocus = getBounds().contains(mousePosition.getIntX(), mousePosition.getIntY());
        isPressed = hasFocus && state.getInput().isMousePressed();

        if(hasFocus && state.getInput().isMouseClicked()) {
            onClick(state);
        }

        if(hasFocus && state.getInput().isMousePressed()){
            onDrag(state);
        }

        if(!previousFocus && hasFocus){
            onFocus(state);
        }
    }

    protected abstract void onFocus(State state);
    protected abstract void onDrag(State state);
    protected abstract void onClick(State state);

    private Rectangle getBounds() {
        return new Rectangle(
                absolutePosition.getIntX(),
                absolutePosition.getIntY(),
                size.getWidth(),
                size.getHeight()
        );
    }
}
