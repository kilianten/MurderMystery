package speech;

import entity.human.Human;
import entity.human.NPC.NPC;

public class KarlSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
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
