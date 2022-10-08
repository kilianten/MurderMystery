package entity.scenery.building;

import core.CollisionBox;
import entity.GameObject;
import graphics.SpriteLibrary;
import state.State;

import java.awt.*;

public class Door extends GameObject {

    private Image sprite;
    private String doorName;

    public Door(SpriteLibrary spriteLibrary, String doorName){
        super();
        sprite = spriteLibrary.getBuildingImage(doorName);
        this.doorName = doorName;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return null;
    }

    @Override
    public void update(State state) {

    }

    @Override
    public Image getSprite() {
        return sprite;
    }
}
