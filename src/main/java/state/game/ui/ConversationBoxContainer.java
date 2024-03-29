package state.game.ui;

import core.Size;
import entity.human.NPC.NPC;
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

    public void setConversant(NPC npc) {
        if(npc.getTitle() == null){
            setConversantName(npc.getFirstName() + " " + npc.getSecondName());
        } else {
            setConversantName(npc.getTitle() + " " + npc.getFirstName() + " " + npc.getSecondName());
        }

        ((ConversationBox) children.get(1)).setConversant(npc);
    }

    public void reset() {
        ((ConversationBox) children.get(1)).setDefault();
    }
}
