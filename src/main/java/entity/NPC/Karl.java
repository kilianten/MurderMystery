package entity.NPC;

import controller.Controller;
import entity.ColourHandler;
import entity.action.Smoke;
import graphics.SpriteLibrary;

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
        perform(new Smoke());
    }
}
