package entity.scenery.building;

import audio.SoundClip;
import core.Position;
import core.Size;
import entity.scenery.Scenery;
import graphics.SpriteLibrary;
import state.State;
import state.game.GameState;

import java.awt.*;

public class Building extends Scenery {

    private String buildingName;
    private boolean isOpen = false;

    public Building(){

    }

    public Building(SpriteLibrary spriteLibrary, String buildingName){
        this.name = buildingName;
        loadGraphics(spriteLibrary, buildingName);
        this.size = new Size(sprite.getWidth(null), sprite.getHeight(null));
        if(size.getWidth() <= 64){
            renderOnMiniMap = false;
        }
        renderOffset = new Position(size.getWidth() / 2, size.getHeight() / 2);
        this.renderLevelOffset = size.getHeight() / 2 - 4;
        this.collisionBoxSize = new Size(size.getWidth(), (int) (size.getHeight() * .75));
        this.collisionBoxOffset = new Position(size.getWidth() / 2, size.getHeight() * .25);
        this.selectionCircleOffset = new Position(0, 60000);
    }

    @Override
    public void update(State state) {
        if(state instanceof GameState){
            if(((GameState) state).getPlayer().isNear(this) && !isOpen){
                isOpen = true;
                loadGraphics(state.getSpriteLibrary(), name + "Open");
                state.getAudioPlayer().playSound("doorOpen.wav");
            } else if (isOpen && !((GameState) state).getPlayer().isNear(this)){
                isOpen = false;
                loadGraphics(state.getSpriteLibrary(), name);
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
    public void loadGraphics(SpriteLibrary spriteLibrary) {
        sprite = spriteLibrary.getBuildingImage(name);
    }

}
