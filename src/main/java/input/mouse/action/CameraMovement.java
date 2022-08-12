package input.mouse.action;

import core.Position;
import state.State;
import ui.UIImage;

public class CameraMovement extends MouseAction {

    private Position dragPosition;

    @Override
    public void onClick(State state) {

    }

    @Override
    public void onDrag(State state) {
        if(dragPosition == null){
            dragPosition = Position.copyOf(state.getInput().getMousePosition());
        } else {
            dragPosition.subtract(state.getInput().getMousePosition());
            Position cameraPosition = Position.copyOf(state.getCamera().getPosition());
            cameraPosition.add(dragPosition);
            state.getCamera().setPosition(cameraPosition);
            dragPosition = Position.copyOf(state.getInput().getMousePosition());
        }
    }

    @Override
    public void onRelease(State state) {
        dragPosition = null;
    }

    @Override
    public void update(State state) {

    }

    @Override
    public UIImage getSprite() {
        return null;
    }

    @Override
    public void cleanup() {

    }
}
