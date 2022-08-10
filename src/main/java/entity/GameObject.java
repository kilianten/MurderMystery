package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import state.State;

import java.awt.*;

public abstract class GameObject {

    protected Position position;
    protected Size size;

    protected int renderOrder;
    protected Position renderOffset;
    protected Position collisionBoxOffset;
    protected Size collisionBoxSize;

    protected  GameObject parent;

    public GameObject(){
        position = new Position(0, 0);
        size = new Size(64, 64);
        renderOffset = new Position(0, 0);
        renderOrder = 5;
        collisionBoxOffset = new Position(0, 0);
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

    public Position getRenderPosition(Camera camera) {
        return new Position(
                getPosition().getX() - camera.getPosition().getX() - renderOffset.getX(),
                getPosition().getY() - camera.getPosition().getY() - renderOffset.getY()
        );
    }

    public int getRenderOrder() {
        return renderOrder;
    }

    protected void clearParent(){
        parent = null;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
