package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.DouglasSpeech;
import speech.TimSpeech;

public class Tim extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xff8E5819,
            0xffFC6C41,
            0xFFE3613B,
            0xFFFF6D42,
            0xFFFF6D42,
            0xFFE3613B,
            0xFF292929,
            0xFF292929);

    public Tim(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "longHairMan", colourHandler);

        speech = new TimSpeech();

        firstName = "Tim";
        secondName = "Kurson";
    }

    @Override
    public void setTraits(){
        smoker = randomiseTrait(0.8);
        hasTattoos = true;
        drinksAlcohol = randomiseTrait(0.9);
    }

}
