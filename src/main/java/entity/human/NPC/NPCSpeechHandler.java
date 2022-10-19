package entity.human.NPC;

import game.Game;

import java.util.ArrayList;
import java.util.List;

public class NPCSpeechHandler {

    public static final String DO_YOU_SMOKE = "Do you smoke?";
    public static final String ARE_YOU_RELIGIOUS = "Are you religious?";
    public static final String DO_YOU_HAVE_TATTOOS = "Do you have tattoos?";
    public static final String DO_YOU_DRINK_ALCOHOL = "Do you drink alcohol?";
    public static final String ARE_YOU_LOCAL = "What brings you to " + Game.GAME_TITLE + "?";
    public static final String HOW_LONG_HAVE_YOU_LIVED_HERE = "How long have you lived here?";
    public static final String WHAT_ARE_YOU_IN_FOR = "What are you in for?";
    public static final String WHO_TAKES_ANTIHISTAMINES = "Who in this town takes antihistamines?";

    public static ArrayList<String> getCategoryOptions(String category, NPC npc) {
        ArrayList<String> options = new ArrayList<>();
        switch (category){
            case "Small Talk":
                options.add(DO_YOU_SMOKE);
                options.add(DO_YOU_DRINK_ALCOHOL);
                options.add(ARE_YOU_RELIGIOUS);
                options.add(DO_YOU_HAVE_TATTOOS);
                if(!npc.isLocal()){
                    options.add(ARE_YOU_LOCAL);
                } else {
                    options.add(HOW_LONG_HAVE_YOU_LIVED_HERE);
                }
                break;
            case "Investigate":
                if(npc.isJailed()){
                    options.add(WHAT_ARE_YOU_IN_FOR);
                } else {
                    options.add(" ");
                }
                if(npc instanceof Kate){
                    options.add(WHO_TAKES_ANTIHISTAMINES);
                }
                break;
            case "Actions":
                if(!npc.isJailed()){
                    options.add("Jail");
                }
                break;
            default:
                return options;
        }
        return options;
    }

    public static String getSpeech(String speech, NPC npc) {
        switch(speech){
            case ARE_YOU_RELIGIOUS:
                return npc.getSpeech().areYouReligious(npc);
            case DO_YOU_SMOKE:
                return npc.getSpeech().doYouSmoke(npc);
            case DO_YOU_HAVE_TATTOOS:
                return npc.getSpeech().doYouHaveTattoos(npc);
            case DO_YOU_DRINK_ALCOHOL:
                return npc.getSpeech().doYouDrink(npc);
            case ARE_YOU_LOCAL:
                return npc.getSpeech().whatBringsYouHere();
            case HOW_LONG_HAVE_YOU_LIVED_HERE:
                return npc.getSpeech().howLongHaveYouLivedHere();
            case WHAT_ARE_YOU_IN_FOR:
                return npc.getSpeech().whatAreYouInFor(npc);
            case WHO_TAKES_ANTIHISTAMINES:
                return npc.getSpeech().whoTakesAntihistamines(npc);
            default:
                System.out.print("Speech not found for " + npc.getFirstName() + " :" + speech);
                return null;
        }
    }
}
