package speech;

import entity.human.Human;
import entity.human.NPC.NPC;

public class EduardoSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        return "Si. Yo fumo";
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Si.";
    }
}
