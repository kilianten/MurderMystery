package speech;

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

    @Override
    public String doYouHaveTattoos(NPC npc) {
        if(npc.hasTattoo()){
            return "Yes. It’s an eagle on a cactus. There’s a cool mito about it in Mexico";
        } else if (npc.isInterestedInGettingTattoo()) {
            return "Por ahora no. I don’t like needles, but I talked to Vanessa about it";
        } else {
            return "No. Odio las agujas";
        }
    }

    @Override
    public String doYouDrink(NPC npc) {
        return "Yes.";
    }
}
