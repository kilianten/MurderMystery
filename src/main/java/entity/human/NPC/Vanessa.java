package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.DouglasSpeech;
import speech.VanessaSpeech;

public class Vanessa extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xffC9C150,
            0xff4E0F3F,
            0xFF410D34,
            0xFF541044,
            0xff303468,
            0xFF272A54,
            0xFF0B0C21,
            0xFF080817,
            0xFF82685A);

    public Vanessa(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "tattoodWoman", colourHandler);

        speech = new VanessaSpeech();

        firstName = "Vanessa";
        secondName = "Carson";

    }

    @Override
    public void setTraits(){
        super.setTraits();
        smoker = true;
        hasTattoos = true;
    }

}
