package entity.human.NPC;

public class NPCSpeechHandler {

    public static final String DO_YOU_SMOKE = "Do you smoke?";
    public static final String ARE_YOU_RELIGIOUS = "Are you religious?";
    public static final String DO_YOU_HAVE_TATTOOS = "Do you have tattoos?";

    public static String[] getCategoryOptions(String category) {
        switch (category){
            case "Small Talk":
                return new String[]{
                        DO_YOU_SMOKE,
                        ARE_YOU_RELIGIOUS,
                        DO_YOU_HAVE_TATTOOS};
            default:
                return new String[]{"Hi"};
        }
    }

    public static String getSpeech(String speech, NPC npc) {
        switch(speech){
            case ARE_YOU_RELIGIOUS:
                return npc.getSpeech(speech).areYouReligious(npc);
            case DO_YOU_SMOKE:
                return npc.getSpeech(speech).doYouSmoke(npc);
            case DO_YOU_HAVE_TATTOOS:
                return npc.getSpeech(speech).doYouHaveTattoos(npc);
            default:
                System.out.print("Speech not found for " + npc.getFirstName() + " :" + speech);
                return null;
        }
    }
}
