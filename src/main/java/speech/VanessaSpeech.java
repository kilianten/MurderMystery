package speech;

import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class VanessaSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.getAction() instanceof Smoke){
            return "Is that supposed to be funny? You don’t see all this smoke surrounding me? Fuck off.";
        } else {
            return "Yup.";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Religion is the opium of the people";
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        return "They really sent us their best guy, didn’t they? Did the conspicuous tattoos on my arms give it away? If you like what you see, I have a tattoo shop.";
    }

    @Override
    public String doYouDrink(NPC npc) {
        return "I wouldn’t be the quintessential punk rock chic if I didn’t";
    }

    @Override
    public String whatBringsYouHere(){
        return null;
    }

    @Override
    public String howLongHaveYouLivedHere() {
        return "My whole life. I visit the city often. I like to pretend I’m more cosmopolitan than I am. I love this place. It’s a damn shame what’s become of it";
    }

    @Override
    public String youJailedMe() {
        return "Are you really asking that?";
    }
}
