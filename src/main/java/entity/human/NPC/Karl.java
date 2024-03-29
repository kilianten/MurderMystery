package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import entity.human.action.Smoke;
import graphics.SpriteLibrary;
import speech.KarlSpeech;
import speech.NolanSpeech;

public class Karl extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xff000000,
            0xffEA454E,
            0xFFC93C45,
            0xFFF55C67,
            0xFF4D65B4,
            0xFF484A77,
            0xFF000000,
            0xFF000000);

    public Karl(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "defaultMan", colourHandler);

        firstName = "Karl";
        secondName = "Bryson";

        this.speech = new KarlSpeech();
    }

    @Override
    public void setTraits(){
        super.setTraits();
        smoker = randomiseTrait(0.4);
        randomiseTattooTraits();
        drinksAlcohol = randomiseTrait(0.7);
    }
}
