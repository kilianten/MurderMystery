package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import graphics.SpriteLibrary;
import io.Persistable;
import state.State;

import java.awt.*;

public class Scenery extends GameObject implements Persistable {

    private Image sprite;
    private String name;
    private boolean walkable;

    public Scenery() {
        System.out.println(size);
        System.out.println(collisionBoxSize);
    }

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

    public void loadGraphics(SpriteLibrary spriteLibrary) {
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

    @Override
    public String serialise() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(name);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(position.serialise());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(size.serialise());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(renderOffset.serialise());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(collisionBoxOffset.serialise());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(collisionBoxSize.serialise());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(walkable);
        stringBuilder.append(DELIMITER);

        return stringBuilder.toString();
    }

    @Override
    public void applySerialisedData(String serializedData) {
        String[] tokens = serializedData.split(DELIMITER);
        name = tokens[1];
        position.applySerialisedData(tokens[2]);
        size.applySerialisedData(tokens[3]);
        renderOffset.applySerialisedData(tokens[4]);
        collisionBoxOffset.applySerialisedData(tokens[5]);

        collisionBoxSize.applySerialisedData(tokens[6]);
        walkable = Boolean.parseBoolean(tokens[7]);
    }

    public boolean isWalkable(){
        return walkable;
    }
}
