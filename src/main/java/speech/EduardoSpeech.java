package speech;

import entity.human.Human;
import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class EduardoSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.getAction() instanceof Smoke){
            return "Eh... claro";
        }
        return "Si. I smoke";
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Si.";
    }
}
