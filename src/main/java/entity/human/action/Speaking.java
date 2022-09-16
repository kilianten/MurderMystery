package entity.human.action;

import core.Position;
import entity.MovingEntity;
import entity.human.Player;
import entity.scenery.Bench;
import game.GameLoop;
import state.State;

import java.awt.event.KeyEvent;

public class Speaking extends Action {

    private boolean done;
    private MovingEntity movingEntity;

    private int lifeSpanInSeconds;

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
        return "smoke";
    }

    @Override
    public void cleanUp() {

    }

}
