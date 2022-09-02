package speech;

import entity.human.Human;
import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class KarlSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            if(npc.getAction() instanceof Smoke){
                return "You see this smoke in my hand, right? If you are trying to be funny, I’d suggest working on better material";
            }
            return "Yes, though I intend to give it up soon";
        } else {
            return "No.";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Ha. No. Complete waste of time";
    }
}
