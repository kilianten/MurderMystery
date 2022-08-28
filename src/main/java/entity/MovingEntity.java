package entity;

import controller.Controller;
import core.*;
import entity.human.NPC.NPC;
import state.State;
import graphics.AnimationManager;
import graphics.SpriteLibrary;

import java.awt.*;


public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    protected Vector2D directionVector;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary){
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.directionVector = new Vector2D(0, 0);
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("dave", null));
    }

    public void update(State state){
        motion.update(controller);
        handleMotion();
        animationManager.update(direction);

        handleCollisions(state);
        handleTileCollision(state);
        
        manageDirection();
        animationManager.playAnimation(decideAnimation());

        position.apply(motion);
    }

    protected void handleTileCollision(State state){
        state.getGameMap().getCollidingUnWalkableTileBoxes(getCollisionBox())
                .forEach(tileCollisionBox -> motion.stop(willCollideX(tileCollisionBox), willCollideY(tileCollisionBox)));
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(GameObject other);

    protected abstract void handleMotion();

    protected abstract String decideAnimation();

    private void manageDirection(){
        if(motion.isMoving()){
            this.direction = Direction.fromMotion(motion);
            this.directionVector = motion.getDirection();
        }
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(getPosition());
        positionWithMotion.apply(motion);
        positionWithMotion.subtract(collisionBoxOffset);

        return new CollisionBox(
                new Rectangle(
                        positionWithMotion.getIntX(),
                        positionWithMotion.getIntY(),
                        collisionBoxSize.getWidth(),
                        collisionBoxSize.getHeight()
                )
        );
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }

    public Controller getController() {
        return controller;
    }

    public boolean willCollideX(CollisionBox otherBox){
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);
        positionWithXApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean willCollideY(CollisionBox otherBox){
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);
        positionWithYApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean isFacing(Position other) {
        Vector2D direction = Vector2D.directionBetweenPositions(other, getPosition());
        double dotProduct = Vector2D.dotProduct(direction, directionVector);

        return dotProduct > 0;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }
}
