package entity.human.action;

import core.Direction;
import core.Position;
import entity.MovingEntity;
import entity.abstractObjects.SpeechBubble;
import entity.human.Player;
import entity.scenery.Bench;
import game.GameLoop;
import state.State;

import java.awt.event.KeyEvent;

public class Speaking extends Action {

    private boolean done;
    private SpeechBubble speechBubble;

    private int lifeSpanInSeconds = 500;

    public Speaking(String speech, State state, MovingEntity movingEntity){
        speechBubble = new SpeechBubble(speech, movingEntity.getPosition());
        state.spawn(movingEntity.getLocation(),speechBubble);
    }

    @Override
    public void update(State state, MovingEntity movingEntity) {
        lifeSpanInSeconds--;
    }

    @Override
    public boolean isDone() {
        return lifeSpanInSeconds <= 0;
    }

    @Override
    public String getAnimationName() {
        return "stand";
    }

    @Override
    public void cleanUp() {
        speechBubble.delete();
    }

}
