package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import graphics.SpriteLibrary;
import state.State;

import java.awt.*;

public class Scenery extends GameObject {

    private Image sprite;
    private String name;
    private boolean walkable;

    public Scenery() {}

    public Scenery(String name,
                   Size size,
                   Position renderOffset,
                   Size collisionBoxSize,
                   Position collisionBoxOffset,
                   boolean walkable,
                   SpriteLibrary spriteLibrary){
        this.name = name;
        this.size = size;
        this.renderOffset = renderOffset;
        this.collisionBoxSize = collisionBoxSize;
        this.collisionBoxOffset = collisionBoxOffset;
        this.walkable = walkable;

        loadGraphics(spriteLibrary);
    }

    public static Scenery copyOf(Scenery scenery){
        Scenery copy = new Scenery();
        copy.name = scenery.name;
        copy.position = Position.copyOf(scenery.position);
        copy.size = Size.copyOf(scenery.size);
        copy.renderOffset = Position.copyOf(scenery.renderOffset);
        copy.collisionBoxOffset = Position.copyOf(scenery.collisionBoxOffset);
        copy.collisionBoxSize = Size.copyOf(scenery.collisionBoxSize);
        copy.sprite = scenery.sprite;
        copy.walkable = scenery.walkable;

        return copy;
    }

    private void loadGraphics(SpriteLibrary spriteLibrary) {
        sprite = spriteLibrary.getSceneryImage(name);
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position position = Position.copyOf(getPosition());
        position.subtract(collisionBoxOffset);

        return new CollisionBox(
                new Rectangle(
                        position.getIntX(),
                        position.getIntY(),
                        collisionBoxSize.getWidth(),
                        collisionBoxSize.getHeight()
                )
        );
    }

    @Override
    public void update(State state) {}

    @Override
    public Image getSprite() {
        return sprite;
    }
}
