package entity.human.action;

import core.Position;
import entity.MovingEntity;
import entity.human.Player;
import entity.scenery.Bench;
import game.GameLoop;
import state.State;

import java.awt.event.KeyEvent;

public class Sit extends Action {

    private Position previousPosition;
    private boolean done;
    private MovingEntity movingEntity;
    private Bench bench;
    private int lifeSpanInSeconds;

    public Sit(MovingEntity movingEntity, Bench bench) {
        this.movingEntity = movingEntity;
        previousPosition = Position.copyOf(movingEntity.getPosition());
        done = false;
        this.bench = bench;
        lifeSpanInSeconds = GameLoop.UPDATES_PER_SECOND * 7;
    }

    @Override
    public void update(State state, MovingEntity movingEntity) {
        if(movingEntity instanceof Player){
            done = state.getInput().isPressed(KeyEvent.VK_SPACE);
        } else {
            lifeSpanInSeconds--;
            done = lifeSpanInSeconds <= 0;
        }

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
