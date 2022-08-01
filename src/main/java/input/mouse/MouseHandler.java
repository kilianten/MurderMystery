package input.mouse;

import input.Input;
import state.State;

public class MouseHandler {

    private MouseConsumer activeConsumer;

    public void update(State state){
        final Input input = state.getInput();

        handleActiveConsumer(state, input);

        cleanUp(input);
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
}
