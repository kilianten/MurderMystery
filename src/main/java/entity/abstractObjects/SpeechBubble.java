package entity.abstractObjects;

import core.CollisionBox;
import entity.GameObject;
import state.State;

import java.awt.*;

public class SpeechBubble extends GameObject {

    public String speech;

    public SpeechBubble(String speech){
        this.speech = speech;
        renderOrder = 7;
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
        return null;
    }
}
