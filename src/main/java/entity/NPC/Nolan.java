package entity.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;

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
            0xFFFFFFF);

    public Nolan(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "priest", colourHandler);
    }

}
