package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import state.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

    protected Position position;
    protected Size size;

    protected int renderOrder;
    protected Position renderOffset;
    protected Position collisionBoxOffset;
    protected Size collisionBoxSize;

    protected  GameObject parent;

    protected List<GameObject> attachments;

    public GameObject(){
        position = new Position(0, 0);
        size = new Size(64, 64);
        renderOffset = new Position(0, 0);
        renderOrder = 5;
        collisionBoxOffset = new Position(0, 0);
        this.collisionBoxSize = new Size(size.getWidth(), size.getHeight());
        attachments = new ArrayList<>();
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

    public void attach(GameObject gameObject){
        gameObject.setPosition(position);
        attachments.add(gameObject);
    }

    public void detach(GameObject gameObject){
        attachments.remove(gameObject);
    }

    public List<GameObject> getAttachments() {
        return attachments;
    }

    public void clearAttachments(){
        attachments.clear();
    }

    public void changePositionBy(Position position){
        this.position.add(position);
    }

    public Position getRenderOffset() {
        return renderOffset;
    }
}
