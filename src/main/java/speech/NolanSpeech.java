package speech;

import entity.human.Human;
import entity.human.NPC.NPC;

public class NolanSpeech extends NPCSpeech {


    @Override
    public String doYouSmoke(NPC npc) {
        return "The Bible doesn’t specifically say we shouldn’t smoke. If that’s why you’re asking";
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "What gave it away?";
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        return "You shall not make gashes in your flesh for the dead or tattoo any marks upon you. Leviticus 19:28.";
    }

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Yes. Being a priest doesn’t mean I have to be completely puritanical";
        }  else {
            return "No. I don’t indulge in alcohol these days";
        }
    }
}
