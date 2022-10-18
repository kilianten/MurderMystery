package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.KarlSpeech;
import speech.MarySpeech;

public class Mary extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xffA6A6A6,
            0xff429F55,
            0xFF41914C,
            0xFF4DAB5A,
            0xFFAB9A7D,
            0xFF9C8C72,
            0xFF000000,
            0xFF000000);

    public Mary(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "defaultWoman", colourHandler);

        firstName = "Mary";
        secondName = "Greene";

        this.speech = new MarySpeech();
    }

    @Override
    public void setTraits(){
        super.setTraits();
        religious = true;
        drinksAlcohol = randomiseTrait(0.4);
    }
}