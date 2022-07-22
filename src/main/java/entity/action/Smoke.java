package entity.action;

import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

import java.util.Random;

public class Smoke extends Action {

    private int lifeSpanInSeconds;

    public Smoke() {
        this.lifeSpanInSeconds = GameLoop.UPDATES_PER_SECOND * 7;
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
        return "smoke";
    }
}
