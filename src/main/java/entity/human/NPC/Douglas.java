package entity.human.NPC;

import controller.Controller;
import entity.ColourHandler;
import entity.NPC;
import graphics.SpriteLibrary;

public class Douglas extends NPC {

    private static final ColourHandler colourHandler = new ColourHandler(
            0xff8E5819,
            0xff6A8C60,
            0xFF4F6848,
            0xFF799E6E,
            0xff9E9E9E,
            0xFF919191,
            0xFFFFFFFF,
            0xFFDFDCE5);

    public Douglas(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary, "defaultMan", colourHandler);
        firstName = "Douglas";
    }
}
