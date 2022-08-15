package entity.scenery;

import core.Position;
import core.Size;
import graphics.SpriteLibrary;

public class InteractableScenery extends Scenery {

    public InteractableScenery(String name,
                   Size size,
                   Position renderOffset,
                   Size collisionBoxSize,
                   Position collisionBoxOffset,
                   boolean walkable,
                   SpriteLibrary spriteLibrary,
                               int renderLevelOffset,
                               Position selectionCircleOffset){
        super(name, size, renderOffset, collisionBoxSize, collisionBoxOffset, walkable, spriteLibrary, renderLevelOffset, selectionCircleOffset);
        loadGraphics(spriteLibrary);
    }

}
