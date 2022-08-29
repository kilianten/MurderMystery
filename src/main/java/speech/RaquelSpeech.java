package speech;

import entity.human.Human;
import entity.human.NPC.NPC;

public class RaquelSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        return "Never in my life. It's repugnant.";
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Yes, I believe in God.";
    }
}
