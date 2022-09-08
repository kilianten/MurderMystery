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

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Yes. The doctor says I should cut back but, I’ve made it this far haven’t I?";
        }  else {
            return "I used to but the doctor said I had to stop.";
        }
    }

    @Override
    public String whatBringsYouHere(){
        return null;
    }
}
