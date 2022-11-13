package speech;

import entity.human.NPC.Kate;
import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class BenjaminSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            if(npc.getAction() instanceof Smoke){
                return "This is my time to relax. If you’re not asking anything serious... leave me alone?";
            } else {
                return "When you work the shifts I do, you need to smoke";
            }
        } else {
            return "Nope. Seen a lot of people’s lives abridged because of it";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        if(npc.isReligious()){
            return "Yes sir.";
        } else {
            return "I am not anymore. This place has made me a cynic";
        }
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        if(npc.hasTattoo()){
            return "I do. It’s a baleful message to miscreants at the bar that says \"Don’t fuck with me\"";
        } else if (npc.isInterestedInGettingTattoo()) {
            return "Not yet, but I’d like to. Have to toughen up to show bad customers I amn’t messing";
        } else {
            return "No. I respect them but it’s not for me";
        }
    }

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Yes sir. I know I shouldn’t drink my own supply.";
        } else {
            if(npc.isSmoker()){
                return "No, it was either giving up drinking or smoking... I chose the former, which I kind of regret";
            } else {
                return "No, your health is your wealth.";
            }
        }
    }

    @Override
    public String whatBringsYouHere(){
        return null;
    }

    @Override
    public String howLongHaveYouLivedHere() {
        return "My whole life. Set up the bar 10 years ago";
    }

    @Override
    public String youJailedMe() {
        return "Ask the guy in the mirror who jailed me";
    }

}
