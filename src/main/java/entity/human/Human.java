package entity.human;

import controller.Controller;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.MovingEntity;
import entity.human.action.Action;
import entity.human.effect.Effect;
import state.State;
import graphics.SpriteLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Human extends MovingEntity {


    protected List<Effect> effects;
    protected Optional<Action> action;

    protected String firstName = "";

    public Human(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        effects = new ArrayList<>();
        action = Optional.empty();

        this.collisionBoxSize = new Size(14, 30);
        this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
    }

    public void update(State state){
        super.update(state);
        handleAction(state);
        effects.forEach(effect -> effect.update(state, null));

        cleanup();
    }

    private void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()){
            action = Optional.empty();
        }
    }

    @Override
    protected String decideAnimation(){
        if(action.isPresent()){
            return action.get().getAnimationName();
        } else if(motion.isMoving()) {
            return "walk";
        }
        return "stand";
    }
    protected void handleAction(State state){
        if(action.isPresent()){
            action.get().update(state, this);
        }
    }

    protected void handleMotion() {
        if(action.isPresent()) {
            motion.stop(true, true);
        }
    }

    public void perform(Action action){
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect){
        effects.add(effect);
    }


    @Override
    protected void handleCollision(GameObject other) {

    }

    public String getFirstName() {
        return firstName;
    }
}
