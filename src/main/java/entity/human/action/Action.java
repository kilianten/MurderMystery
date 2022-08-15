package entity.human.action;

import entity.MovingEntity;
import state.State;

public abstract class Action {

    public abstract void update(State state, MovingEntity movingEntity);
    public abstract boolean isDone();
    public abstract String getAnimationName();
    public abstract void cleanUp();
}
