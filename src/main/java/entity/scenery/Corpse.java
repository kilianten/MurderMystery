package entity.scenery;

import core.CollisionBox;
import core.Position;
import entity.GameObject;
import state.State;
import java.awt.*;


public class Corpse extends GameObject {

    private Image sprite;


    public Corpse(Position position, Image sprite) {
        this.position = position;
        position.subtract(new Position(-16, -16));
        this.sprite = sprite;
        renderOrder = 1;
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
