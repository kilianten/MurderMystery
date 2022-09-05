package entity.human.NPC;

import ai.AIManager;
import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;
import speech.NolanSpeech;
import state.State;

public class Nolan extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xffb2a89f,
            0xff222034,
            0xFF000000,
            0xFF3A3759,
            0xff222034,
            0xFF000000,
            0xFF000000,
            0xFF000000,
            0xFFFFFFFF);

    public Nolan(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "priest", colourHandler);

        this.speech = new NolanSpeech();

        firstName = "Fr.";
        secondName = "Nolan";

    }

    @Override
    public void update(State state){
        super.update(state);
    }

    @Override
    public void setTraits(){
        religious = true;
        drinksAlcohol = randomiseTrait(0.5);
    }
}
