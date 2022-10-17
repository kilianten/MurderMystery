package speech;

import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class TimSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            if(npc.getAction() instanceof Smoke){
                return "Fuck off.";
            } else {
                return "Yup. Are you going to be all sanctimonious about it?";
            }
        } else {
            return "Nope";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Nonsense";
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        return "I do";
    }

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Only when I can";
        } else {
            return "Not anymore. Look where it got me";
        }
    }

    @Override
    public String whatBringsYouHere(){
        return null;
    }

    @Override
    public String howLongHaveYouLivedHere() {
        return "My whole life. I was supposed to be transferred out of here. This kip couldn’t handle me anymore.";
    }

    @Override
    public String youJailedMe() {
        return "You stuck me in here you piece of shit";
    }
}
