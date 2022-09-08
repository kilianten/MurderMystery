package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.EricSpeech;

public class Eric extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xff000000,
            0xff212121,
            0xFF171717,
            0xFF353535,
            0xFF737373,
            0xFF646464,
            0xFF452C0E,
            0xFF452C0E);

    public Eric(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "defaultMan", colourHandler);

        firstName = "Eric";
        secondName = "Wiley";

        this.speech = new EricSpeech();
    }

    @Override
    public void setTraits(){
        smoker = randomiseTrait(0.4);
        drinksAlcohol = randomiseTrait(0.5);
        isLocal = false;
    }
}

