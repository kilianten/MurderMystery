package speech;

import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class DouglasSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            if(npc.getAction() instanceof Smoke){
                return "You are a peerless detective.";
            } else {
                return "Yes. And like every addict, I intent to stop soon";
            }
        } else {
            return "Not anymore. I hope I don’t succumb to its temptations ever again";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "I came from a religious background, but I grew out of it";
    }
}
