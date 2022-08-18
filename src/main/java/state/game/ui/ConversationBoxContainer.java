package state.game.ui;

import core.Size;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;

public class ConversationBoxContainer extends VerticalContainer  {
    public ConversationBoxContainer(Size windowSize) {
        super(windowSize);
        new Alignment(Alignment.Position.CENTER, Alignment.Position.START);
        conversantName = new UIText("", 35, 35, false);
        addUIComponent(conversantName);
        addUIComponent(new ConversationBox(windowSize));
    }


    public UIText conversantName;

    public void setConversantName(String fullName){
        conversantName.setText(fullName);
    }

}
