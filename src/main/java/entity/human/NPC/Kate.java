package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.KateSpeech;
import state.State;

import java.util.List;

public class Kate extends NPC {

    private boolean isCompilingListRed = false;
    private String listOfAntiHistamineUsers = null;

    private static final ColourHandler colourHandler = new ColourHandler(
            0xff0C010E,
            0xffEFEFEF,
            0xFFD6D6D6,
            0xFFFBFBFB,
            0xFF497CFB,
            0xFF4272E5,
            0xFF000000,
            0xFF000000);

    public Kate(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "defaultWoman", colourHandler);

        firstName = "Kate";
        secondName = "Greene";
        title = "dr";

        this.speech = new KateSpeech();
    }

    @Override
    public void setTraits(){
        super.setTraits();
        hasHayFever = false;
        religious = randomiseTrait(0.2);
        drinksAlcohol = randomiseTrait(0.5);
        randomiseTattooTraits();
        smoker = randomiseTrait(0.2);
    }

    public void compileListOfPillUsers(){
        isCompilingListRed = true;
    }

    @Override
    public void newDayTasks(State state){
        super.newDayTasks(state);
        if(isCompilingListRed){
            List<NPC> antiHystamineUsers = state.getGameObjectsOfClass(NPC.class);
            listOfAntiHistamineUsers = "";
            for(int i = 0; i < antiHystamineUsers.size(); i++){
                if(antiHystamineUsers.get(i).hasHayFever){
                    if(i != 0){
                        listOfAntiHistamineUsers += " ";
                    }
                    listOfAntiHistamineUsers += antiHystamineUsers.get(i).getFirstName() + " " + antiHystamineUsers.get(i).getSecondName() + ",";
                }
            }
            listOfAntiHistamineUsers += " take antihistamines. I hope that is helpful";
        }
    }

    public boolean isCompilingListRed() {
        if(!isCompilingListRed){
            isCompilingListRed = true;
            return false;
        } else {
            return true;
        }
    }

    public String getListOfAntiHistamineUsers() {
        return listOfAntiHistamineUsers;
    }

}