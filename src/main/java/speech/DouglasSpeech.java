package speech;

import entity.human.NPC.NPC;

public class DouglasSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            return "Yes. And like every addict, I intent to stop soon";
        } else {
            return "Not anymore. I hope I don't succumb to it's temptations ever again";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return null;
    }
}
