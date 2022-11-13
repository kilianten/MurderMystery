package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.BenjaminSpeech;
import speech.DouglasSpeech;

public class Benjamin extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xff8E5819,
            0xff6A8C60,
            0xFF4F6848,
            0xFF799E6E,
            0xff9E9E9E,
            0xFF919191,
            0xFFFFFFFF,
            0xFFDFDCE5);

    public Benjamin(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "tubbyMan", colourHandler);

        speech = new BenjaminSpeech();

        firstName = "Benjamin";
        secondName = "Lomas";
    }

    @Override
    public void setTraits(){
        super.setTraits();
        hasHayFever = false;
        religious = randomiseTrait(0.4);
        drinksAlcohol = randomiseTrait(0.95);
        randomiseTattooTraits();
        smoker = randomiseTrait(0.3);
    }

}
