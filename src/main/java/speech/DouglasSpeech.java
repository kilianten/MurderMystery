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

    @Override
    public String doYouHaveTattoos(NPC npc) {
        if(npc.hasTattoo()){
            return "Yes. I have an Ouroboros on my leg. It’s an ancient symbol of a snake eating its own tail. It symbolizes the cyclic Nature of the Universe: Life out of Death";
        } else if (npc.isInterestedInGettingTattoo()) {
            return "Not yet. But I had spoken to Vanessa about getting one.";
        } else {
            return "No. I didn’t have any idea of what to get that wasn’t trite or cringe";
        }
    }

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Only when I’m feeling melancholic. Which translates to a 6-pack a day";
        } else {
            return "Not anymore";
        }
    }
}
