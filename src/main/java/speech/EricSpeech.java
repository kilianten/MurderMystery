package speech;


import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class EricSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            if(npc.getAction() instanceof Smoke){
                return "Please, this is my time to wind down.";
            } else {
                return "Yeah.";
            }
        } else {
            return "I don’t. Time and money for nothing.";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Nope. I’m too cynical for that nonsense";
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        return "No way. I like them though. They are a good way of identifying morons from a distance";
    }

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Trying working my job. You’ll be an alcoholic before you've gotten your first paycheck";
        } else {
            return "Nope. I’ve seen it destroy a lot of good people. Mainly men";
        }
    }

    @Override
    public String whatBringsYouHere(){
        return "Strictly business";
    }

    @Override
    public String howLongHaveYouLivedHere() {
        return null;
    }
}
