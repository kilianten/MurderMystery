package entity.scenery.building;

import core.CollisionBox;
import core.Position;
import entity.GameObject;
import entity.human.Human;
import entity.scenery.InteractableScenery;
import graphics.SpriteLibrary;
import state.State;
import state.game.GameState;

import java.awt.*;

public class Door extends InteractableScenery {

    private Image sprite;
    private String doorName;
    private boolean isOpen = false;
    private Position outsidePosition;

    public Door(SpriteLibrary spriteLibrary, String doorName, String location, Position position){
        super();
        sprite = spriteLibrary.getBuildingImage(doorName);
        this.doorName = doorName;
        this.location = location;
        this.position = new Position(position.getX(), position.getIntY() - sprite.getHeight(null));
    }

    @Override
    public CollisionBox getCollisionBox() {
        return null;
    }

    @Override
    public void update(State state) {
        if(state instanceof GameState) {
            if (((GameState) state).getPlayer().isNear(this) && ((GameState) state).getPlayer().isFacing(position) && !isOpen) {
                isOpen = true;
                loadGraphics(state.getSpriteLibrary(), doorName + "Open");
                state.getAudioPlayer().playSound("doorOpen.wav");
            } else if (isOpen && (!((GameState) state).getPlayer().isNear(this) || !((GameState) state).getPlayer().isFacing(position))) {
                isOpen = false;
                loadGraphics(state.getSpriteLibrary(), doorName);
            }
        }
    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    public void loadGraphics(SpriteLibrary spriteLibrary, String fileName) {
        sprite = spriteLibrary.getBuildingImage(fileName);
    }

    @Override
    public void interact(State state, Human human){
        if(isOpen){
            state.setCurrentLocation("Outside");
            ((GameState) state).changeObjectLocation(human, "Outside");
        }
    }

    public void setOutsidePosition(Position outsidePosition) {
        this.outsidePosition = outsidePosition;
    }
}
