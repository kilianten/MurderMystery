package entity.human.NPC;

public class NPCSpeechHandler {

    public static final String DO_YOU_SMOKE = "Do you smoke?";
    public static final String ARE_YOU_RELIGIOUS = "Are you religious?";

    public static String[] getCategoryOptions(String category) {
        switch (category){
            case "Small Talk":
                return new String[]{
                        NPCSpeechHandler.DO_YOU_SMOKE,
                        NPCSpeechHandler.ARE_YOU_RELIGIOUS};
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
            default:
                System.out.print("Speech not found for " + npc.getFirstName() + " :" + speech);
                return null;
        }
    }
}
