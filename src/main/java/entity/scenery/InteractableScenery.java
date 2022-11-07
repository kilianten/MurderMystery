package entity.scenery;

import core.Position;
import core.Size;
import graphics.SpriteLibrary;

public class InteractableScenery extends Scenery {

    public InteractableScenery(){
        interactable = true;
    }

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
        interactable = true;
    }

    public InteractableScenery(String name, SpriteLibrary spriteLibrary, Position position){
        this.name = name;
        loadGraphics(spriteLibrary);
        size = new Size(sprite.getWidth(null), sprite.getHeight(null));
        renderOffset = new Position(size.getWidth()/2, size.getHeight()/2);
        this.position = position;
        renderLevelOffset = 5;
        this.collisionBoxSize = new Size(size.getWidth(), size.getHeight()/4);
        resetCollisionBoxOffset();
        renderLevelOffset = size.getHeight()/2;
        interactable = true;
    }

}
