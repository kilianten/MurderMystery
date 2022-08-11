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
            } else if (state.getInput().isMiddleMousePressed()){
                middleButtonAction.onDrag(state);
            }
        }
    }

    private void handleRightButton(State state) {
        if(rightButtonAction != null){
            rightButtonAction.update(state);
            if(state.getInput().isRightMouseClicked()){
                rightButtonAction.onClick(state);
            } else if (state.getInput().isRightMousePressed()){
                rightButtonAction.onDrag(state);
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

        input.clearMouseClick();
    }

    private void handleActiveConsumer(State state, Input input) {
        if(activeConsumer != null){
            if(input.isMouseClicked()){
                activeConsumer.onClick(state);
            } else if (input.isMousePressed()){
                activeConsumer.onDrag(state);
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

    public void setPrimaryButtonAction(MouseAction primaryButtonAction) {
        this.primaryButtonAction = primaryButtonAction;
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
