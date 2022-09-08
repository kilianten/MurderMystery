package speech;

import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class SheriffSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            if(npc.getAction() instanceof Smoke){
                return "...";
            } else {
                return "I do. I’ve quit before, but it has never stuck";
            }
        } else {
            return "I used to smoke a pack a day. But I quit in my 30s";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "I try to be.";
    }


    @Override
    public String doYouHaveTattoos(NPC npc) {
        return "No. And I don’t regret my decision either. I doubt most people with a tattoo can say the same";
    }

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Sometimes this job demands it";
        }  else {
            return "Not anymore. That stays in the past with a lot of other demons";
        }
    }

    @Override
    public String whatBringsYouHere(){
        return null;
    }
}
