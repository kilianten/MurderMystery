package ui.clickable;

import state.State;
import state.editor.EditorState;

import java.awt.*;

public class UISaveButton extends UIButton {

    public UISaveButton(String label, ClickAction clickAction) {
        super(label, clickAction);
    }

    @Override
    public void update(State state) {
        super.update(state);

        if(!((EditorState) state).hasRequiredElements()){
            container.setBackgroundColor(new Color(182, 53, 53));
        }
    }

}
