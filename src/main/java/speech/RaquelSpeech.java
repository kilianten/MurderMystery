package speech;

import entity.human.Human;
import entity.human.NPC.NPC;

public class RaquelSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        return "Never in my life. It’s repugnant.";
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Yes, I believe in God.";
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        return "No. I never wanted one";
    }

    @Override
    public String doYouDrink(NPC npc) {
        return "Yes, though I don’t have a problem like some of the people round here";
    }

    @Override
    public String whatBringsYouHere(){
        return null;
    }

    @Override
    public String howLongHaveYouLivedHere() {
        return "I was born here";
    }

    @Override
    public String youJailedMe() {
        return "Is that a joke? You should know";
    }
}
