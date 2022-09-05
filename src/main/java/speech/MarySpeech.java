package speech;

import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class MarySpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        return "No. I probably wouldn’t be the sprightly woman I am today if I did.";
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Yes. The Lord is there for me, especially since my Ron passed away.";
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        return "Lord no.";
    }
}
