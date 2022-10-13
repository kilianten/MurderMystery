package entity.scenery;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.scenery.building.Building;
import graphics.SpriteLibrary;
import io.Persistable;
import state.State;

import java.awt.*;

public class Scenery extends GameObject implements Persistable {

    protected Image sprite;
    protected String name;
    private boolean walkable;

    public Scenery() {}

    public Scenery(String name, SpriteLibrary spriteLibrary, Position position){
        this.name = name;
        loadGraphics(spriteLibrary);
        size = new Size(sprite.getHeight(null), sprite.getHeight(null));
        renderOffset = new Position(size.getWidth()/2, size.getHeight()/2);
        this.position = position;
        renderLevelOffset = 5;
        walkable = false;
        collisionBoxOffset = new Position(0, 0);
    }

    public Scenery(String name,
                   Size size,
                   Position renderOffset,
                   Size collisionBoxSize,
                   Position collisionBoxOffset,
                   boolean walkable,
                   SpriteLibrary spriteLibrary,
                   int renderLevelOffset,
                   Position selectionCircleOffset){
        this.name = name;
        this.size = size;
        this.renderOffset = renderOffset;
        this.collisionBoxSize = collisionBoxSize;
        this.collisionBoxOffset = collisionBoxOffset;
        this.walkable = walkable;
        this.renderLevelOffset = renderLevelOffset;
        this.selectionCircleOffset = selectionCircleOffset;
        loadGraphics(spriteLibrary);
        if(size.getWidth() <= 64){
            renderOnMiniMap = false;
        }
    }

    public Scenery(String name,
                   Size size,
                   Position renderOffset,
                   Size collisionBoxSize,
                   Position collisionBoxOffset,
                   boolean walkable,
                   SpriteLibrary spriteLibrary){
        this(name, size, renderOffset, collisionBoxSize, collisionBoxOffset, walkable, spriteLibrary, 0, new Position(0, 0));
    }

    public static Scenery copyOf(Scenery scenery){
        Scenery copy;

        if(scenery instanceof Building){
            copy = new Building();
        } else {
            copy = new Scenery();
        }
        copy.name = scenery.name;
        copy.position = Position.copyOf(scenery.position);

        copy.size = Size.copyOf(scenery.size);
        copy.renderOffset = Position.copyOf(scenery.renderOffset);
        copy.collisionBoxOffset = Position.copyOf(scenery.collisionBoxOffset);
        copy.collisionBoxSize = Size.copyOf(scenery.collisionBoxSize);
        copy.sprite = scenery.getSprite();
        copy.walkable = scenery.walkable;
        copy.renderLevelOffset = scenery.renderLevelOffset;
        copy.selectionCircleOffset = Position.copyOf(scenery.selectionCircleOffset);
        copy.interactable = scenery.isInteractable();
        copy.selectionCircleSize = scenery.selectionCircleSize;

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
        stringBuilder.append(isInteractable());
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
        stringBuilder.append(renderLevelOffset);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(selectionCircleOffset.serialise());
        stringBuilder.append(DELIMITER);

        return stringBuilder.toString();
    }

    @Override
    public void applySerialisedData(String serializedData) {
        String[] tokens = serializedData.split(DELIMITER);
        name = tokens[2];
        position.applySerialisedData(tokens[3]);
        size.applySerialisedData(tokens[4]);
        renderOffset.applySerialisedData(tokens[5]);
        collisionBoxOffset.applySerialisedData(tokens[6]);

        collisionBoxSize.applySerialisedData(tokens[7]);
        walkable = Boolean.parseBoolean(tokens[8]);
        renderLevelOffset = Integer.parseInt(tokens[9]);
        selectionCircleOffset.applySerialisedData(tokens[10]);
        selectionCircleSize = new Size(size.getWidth(), size.getHeight() / 6);
    }

    public boolean isWalkable(){
        return walkable;
    }

    public String getName() {
        return name;
    }

    public Position getSelectionCircleOffset() {
        return selectionCircleOffset;
    }
}
