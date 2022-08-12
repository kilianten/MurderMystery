package input.mouse;

import input.Input;
import input.mouse.action.MouseAction;
import state.State;
import ui.UIImage;

import java.util.Optional;

public class MouseHandler {

    private MouseConsumer activeConsumer;
    private MouseAction primaryButtonAction;
    private MouseAction rightButtonAction;
    private MouseAction middleButtonAction;

    public void update(State state){
        final Input input = state.getInput();

        handlePrimaryButton(state);
        handleRightButton(state);
        handleMiddleButton(state);
        handleActiveConsumer(state, input);

        cleanUp(input);
    }

    private void handleMiddleButton(State state) {
        if(middleButtonAction != null){
            middleButtonAction.update(state);
            if(state.getInput().isMiddleMouseClicked()){
                middleButtonAction.onClick(state);
            }
            if (state.getInput().isMiddleMousePressed()){
                middleButtonAction.onDrag(state);
            }
            if(state.getInput().isMiddleMouseReleased()){
                middleButtonAction.onRelease(state);
            }
        }
    }

    private void handleRightButton(State state) {
        if(rightButtonAction != null){
            rightButtonAction.update(state);
            if(state.getInput().isRightMouseClicked()){
                rightButtonAction.onClick(state);
            }
            if (state.getInput().isRightMousePressed()){
                rightButtonAction.onDrag(state);
            }
            if(state.getInput().isRightMouseReleased()){
                rightButtonAction.onRelease(state);
            }
        }
    }

    private void handlePrimaryButton(State state) {
        if(primaryButtonAction != null){
            setActiveConsumer(primaryButtonAction);
            primaryButtonAction.update(state);
        }
    }

    private void cleanUp(Input input) {
        if(!input.isMousePressed()){
            activeConsumer = null;
        }

        input.cleanUpMouseEvents();
    }

    private void handleActiveConsumer(State state, Input input) {
        if(activeConsumer != null){
            if(input.isMouseClicked()){
                activeConsumer.onClick(state);
            }
            if (input.isMousePressed()){
                activeConsumer.onDrag(state);
            }
            if(input.isMouseReleased()){
                activeConsumer.onRelease(state);
            }
        }
    }

    public MouseConsumer getActiveConsumer() {
        return activeConsumer;
    }

    public void setActiveConsumer(MouseConsumer mouseConsumer) {
        if(activeConsumer == null){
            activeConsumer = mouseConsumer;
        }
    }

    public void switchPrimaryButtonAction(MouseAction mouseAction) {
        if(mouseAction != null){
            mouseAction.cleanup();
        }
        this.primaryButtonAction = mouseAction;
    }

    public Optional<UIImage> getPrimaryButtonUI(){
        if(primaryButtonAction != null){
            return Optional.ofNullable(primaryButtonAction.getSprite());
        }

        return Optional.empty();
    }

    public MouseAction getPrimaryButtonAction() {
        return primaryButtonAction;
    }

    public MouseAction getRightButtonAction() {
        return rightButtonAction;
    }

    public void setRightButtonAction(MouseAction rightButtonAction) {
        this.rightButtonAction = rightButtonAction;
    }

    public MouseAction getMiddleButtonAction() {
        return middleButtonAction;
    }

    public void setMiddleButtonAction(MouseAction middleButtonAction) {
        this.middleButtonAction = middleButtonAction;
    }
}
