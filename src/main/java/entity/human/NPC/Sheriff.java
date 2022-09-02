package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.DouglasSpeech;
import speech.SheriffSpeech;

public class Sheriff extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xFF2F2F2F,
            0xffBE9B86,
            0xFFB2917E,
            0xFFC9A48E,
            0xff837067,
            0xFF7A6860,
            0xFF000000,
            0xFF000000,
            0xFFf6e74b

    );

    public Sheriff(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "sheriff", colourHandler);

        speech = new SheriffSpeech();

        firstName = "Sheriff";
        secondName = "Hanson";

        religious = true;
    }

    @Override
    public void setTraits(){
        smoker = randomiseTrait(0.5);
    }

}
