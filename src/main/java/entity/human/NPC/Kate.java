package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.KarlSpeech;
import speech.KateSpeech;
import speech.MarySpeech;

public class Kate extends NPC {

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

    public void compileListOfRedPillUsers(){

    }
}