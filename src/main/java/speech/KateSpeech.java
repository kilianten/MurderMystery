package speech;

import entity.human.NPC.Kate;
import entity.human.NPC.NPC;
import entity.human.action.Smoke;

public class KateSpeech extends NPCSpeech {

    @Override
    public String doYouSmoke(NPC npc) {
        if(npc.isSmoker()){
            if(npc.getAction() instanceof Smoke){
                return "Are you mocking me?";
            } else {
                return "Yes. I know, a doctor that smokes. I’m saturated in irony";
            }
        } else {
            return "Nope. As corny as it may be, I want to set a good example for my patients.";
        }
    }

    @Override
    public String areYouReligious(NPC npc) {
        if(npc.isReligious()){
            return "My mother, Mary, raised me that way. I’m not as pious as her, but I still follow God";
        } else {
            return "My mother, Mary, raised me that way but as I grew older, I didn’t believe in it any more. It still breaks my mam’s heart.";
        }
    }

    @Override
    public String doYouHaveTattoos(NPC npc) {
        if(npc.hasTattoo()){
            return "Yeah I do. It’s a brain in a jar. Don’t ask.";
        } else if (npc.isInterestedInGettingTattoo()) {
            return "Not yet. Vanessa is always trying to tempt me, and I think I will succumb eventually";
        } else {
            return "No. I want my patients to take me seriously";
        }
    }

    @Override
    public String doYouDrink(NPC npc) {
        if(npc.drinksAlcohol()){
            return "Only when I’m feeling melancholic. Which translates to a 6-pack a day";
        } else {
            if(npc.isSmoker()){
                return "No, I like to set an example for my patients. But I smoke. Kind of defeating the purpose.";
            } else {
                return "No, I like to set an example for my patients.";
            }
        }
    }

    @Override
    public String whatBringsYouHere(){
        return null;
    }

    @Override
    public String howLongHaveYouLivedHere() {
        return "I was born and raised here by my mother Mary.";
    }

    @Override
    public String youJailedMe() {
        return "You tell me. Now, please, let me out.";
    }

    @Override
    public String whoTakesAntihistamines(NPC npc) {
        String antiHistamineUsers = ((Kate) npc).getListOfAntiHistamineUsers();
        if(!npc.isJailed()){
            if(antiHistamineUsers == null){
                if(((Kate) npc).isCompilingListRed() == false){
                    return "It’s that time of year. Half the town takes them. I’ll compile a list of people who are taking them. I should have it by tomorrow";
                } else {
                    return "I told you I’d make a list of the people. I haven’t finished yet.";
                }
            } else {
                return antiHistamineUsers;
            }
        } else {
            return "You lock me up and then ask for my help? Really?";
        }
    }

}
