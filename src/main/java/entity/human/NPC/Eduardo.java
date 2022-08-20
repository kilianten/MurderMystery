package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import graphics.SpriteLibrary;

public class Eduardo extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xff2e2321,
            0xff5C6E99,
            0xFF495779,
            0xFF7989AF,
            0xff35415F,
            0xFF35415F,
            0xFF2e2321,
            0xFF2e2321,
            0xFFd19979,
            0xFFbf7d57);

    public Eduardo(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "defaultMan", colourHandler);
        firstName = "Eduardo";
        secondName = "Palmer";
        religious = true;
    }
}
