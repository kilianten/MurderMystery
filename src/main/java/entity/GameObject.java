package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import entity.human.Human;
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

    protected Position selectionCircleOffset;
    protected Size selectionCircleSize;
    protected  GameObject parent;

    protected List<GameObject> attachments;
    protected int renderLevelOffset;
    protected boolean interactable;

    protected String shouldDelete = null;

    protected boolean renderOnMiniMap = true;

    private String location = "Outside";

    public GameObject(){
        position = new Position(0, 0);
        size = new Size(64, 64);
        renderOffset = new Position(0, 0);
        renderOrder = 5;
        collisionBoxOffset = new Position(0, 0);
        this.collisionBoxSize = new Size(size.getWidth(), size.getHeight());
        attachments = new ArrayList<>();
        renderLevelOffset = 0;
        selectionCircleOffset = new Position(16, 10);
        interactable = false;
        selectionCircleSize = new Size(size.getWidth(), size.getHeight() / 6);
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

    public int getRenderLevel(){
        int offset = position.getIntY() + renderLevelOffset;
        return offset;
    }

    public void moveCollisionBoxRight(){
        collisionBoxOffset.setX(collisionBoxOffset.getIntX() - 1);
    }

    public void moveCollisionBoxLeft(){
        collisionBoxOffset.setX(collisionBoxOffset.getIntX() + 1);
    }

    public void moveCollisionBoxDown(){
        collisionBoxOffset.setY(collisionBoxOffset.getIntY() - 1);
    }

    public void moveCollisionBoxUp(){
        collisionBoxOffset.setY(collisionBoxOffset.getIntY() + 1);
    }

    public boolean isInteractable(){
        return interactable;
    }

    public Size getSelectionCircleSize() {
        return selectionCircleSize;
    }

    public Position getSelectionCircleOffset() {
        return selectionCircleOffset;
    }

    public void interact(State state, Human human){}

    public boolean shouldRenderOnMiniMap() {
        return renderOnMiniMap;
    }

    public List<String> getAssociatedActions() {
        return null;
    }

    public String shouldDelete() {
        return shouldDelete;
    }

    public void delete(){
        shouldDelete = getLocation();
    }

    public void delete(String deleteFrom){
        shouldDelete = deleteFrom;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
