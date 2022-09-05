package speech;

import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class VanessaSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.getAction() instanceof Smoke){
            return "Is that supposed to be funny? You don’t see all this smoke surrounding me? Fuck off.";
        } else {
            return "Yup.";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Religion is the opium of the people";
    }
}
