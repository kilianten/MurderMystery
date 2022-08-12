package input.mouse.action;

import state.State;
import ui.UIImage;

public class ClearAction extends MouseAction {

    @Override
    public void onClick(State state) {
        state.getMouseHandler().switchPrimaryButtonAction(new SceneryTool());
    }

    @Override
    public void onDrag(State state) {

    }

    @Override
    public void onRelease(State state) {

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
