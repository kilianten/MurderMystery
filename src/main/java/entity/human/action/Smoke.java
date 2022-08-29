package entity.human.action;

import core.Direction;
import entity.MovingEntity;
import entity.human.Human;
import game.GameLoop;
import state.State;

public class Smoke extends Action {

    private int lifeSpanInSeconds;

    public Smoke(Human human) {
        human.setDirection(Direction.S);
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

    @Override
    public void cleanUp() {}

}
