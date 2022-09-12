package entity.scenery;

import core.Position;
import core.Size;
import graphics.SpriteLibrary;

public class LampPost extends Scenery {

    public LampPost(){

    }

    public LampPost(
            String name,
            Size size,
            Position renderOffset,
            Size collisionBoxSize,
            Position collisionBoxOffset,
            boolean walkable,
            SpriteLibrary spriteLibrary){
        super(name, size, renderOffset, collisionBoxSize, collisionBoxOffset, walkable, spriteLibrary, 35, new Position(0, 0));
    }
}
