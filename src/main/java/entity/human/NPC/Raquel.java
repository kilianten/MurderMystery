package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import entity.human.action.Smoke;
import graphics.SpriteLibrary;

public class Raquel extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xffBAA393,
            0xffB18E80,
            0xFFA68578,
            0xFFC29C8C,
            0xFF292621,
            0xFF1E1C18,
            0xFF000000,
            0xFF000000);

    public Raquel(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "defaultWoman", colourHandler);
        perform(new Smoke());
        firstName = "Raquel";
        secondName = "Brown";
    }
}
