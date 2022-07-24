package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;

import java.awt.*;

public abstract class GameObject {

    protected Position position;
    protected Size size;

    protected  GameObject parent;

    public GameObject(){
        position = new Position(50, 50);
        size = new Size(50, 50);
    }

    public abstract CollisionBox getCollisionBox();

    public abstract void update(State state);

    public abstract Image getSprite();

    public Position getPosition() {
        Position finalPosition = Position.copyOf(position);

        if(parent != null){
            finalPosition.add(parent.getPosition());

        }
        return finalPosition;
    }

    public Size getSize() {
        return size;
    }

    public boolean collidesWith(GameObject other) {
        return getCollisionBox().collidesWith(other.getCollisionBox());
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
