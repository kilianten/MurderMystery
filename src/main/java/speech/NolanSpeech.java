package speech;

import entity.human.Human;
import entity.human.NPC.NPC;

public class NolanSpeech extends NPCSpeech {


    @Override
    public String doYouSmoke(NPC npc) {
        return "The Bible doesn’t specifically say we shouldn’t smoke. If that’s why you’re asking";
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "What gave it away?";
    }
}
