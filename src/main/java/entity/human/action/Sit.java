package entity.human.action;

import core.Position;
import entity.MovingEntity;
import entity.scenery.Bench;
import state.State;

import java.awt.event.KeyEvent;

public class Sit extends Action {

    private Position previousPosition;
    private boolean done;
    private MovingEntity movingEntity;
    private Bench bench;

    public Sit(MovingEntity movingEntity, Bench bench) {
        this.movingEntity = movingEntity;
        previousPosition = Position.copyOf(movingEntity.getPosition());
        done = false;
        this.bench = bench;
    }

    @Override
    public void update(State state, MovingEntity movingEntity) {
        done = state.getInput().isPressed(KeyEvent.VK_SPACE);
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public String getAnimationName() {
        return "stand";
    }

    @Override
    public void cleanUp() {
        bench.setInUse(false);
        movingEntity.setPosition(previousPosition);
    }


}
