package speech;

import entity.human.Human;
import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class KarlSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            if(npc.getAction() instanceof Smoke){
                return "You see this smoke in my hand, right? If you are trying to be funny, I’d suggest working on better material";
            }
            return "Yes, though I intend to give it up soon";
        } else {
            return "No.";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        return "Ha. No. Complete waste of time";
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        if(npc.hasTattoo()){
            return "Yes. It’s of those cool Moai human sculptures by the Rapa Nui people. They’re off Chile on Easter Island. Look it up... You’ll know them to see.";
        } else if (npc.isInterestedInGettingTattoo()) {
            return "Not yet. But the idea has always appealed to me. I’m gonna chat to Vanessa about it.";
        } else {
            return "Though I respect everyone’s right to do it, I think tattoos are stupid.";
        }
    }

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Yes. It’s a lot of fun and makes life less boring. YAY";
        }  else {
            return "No. Im a workaholic and the thought of wasting a day with a hangover is more painful than the hangover itself";
        }
    }

    @Override
    public String whatBringsYouHere(){
        return null;
    }

}
