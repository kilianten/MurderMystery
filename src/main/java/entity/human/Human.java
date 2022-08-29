package entity.human;

import controller.Controller;
import core.Motion;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.MovingEntity;
import entity.human.action.Action;
import entity.human.effect.Effect;
import game.Game;
import state.State;
import graphics.SpriteLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Human extends MovingEntity {


    protected List<Effect> effects;
    protected Optional<Action> action;

    protected String firstName;
    protected String secondName;

    public Human(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        effects = new ArrayList<>();
        action = Optional.empty();

        this.collisionBoxSize = new Size(14, 16);
        this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
    }

    public void update(State state){
        super.update(state);
        handleAction(state);
        effects.forEach(effect -> effect.update(state, null));

        if(state.getSettings().isDebugMode()){
            System.out.println("Player X: " + position.getIntX() + " Y: " + position.getIntY());
        }

        cleanup();
    }

    private void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()){
            action.get().cleanUp();
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

    public Action getAction(){
        if(action.isPresent()){
            return action.get();
        }
        return null;
    }

    public List<String> findNearObjectActionionables(State state) {
        List<String> actions = new ArrayList<>();
        state.getGameObjectsOfClass(GameObject.class).stream()
                .filter(gameObject -> getPosition().distanceTo(gameObject.getPosition()) < 2 * Game.SPRITE_SIZE)
                .filter(gameObject -> (gameObject.getAssociatedActions() != null))
                .forEach(gameObject -> actions.addAll(gameObject.getAssociatedActions()));
        return actions;
    }

    public List<GameObject> findNearObjectsOfAction(State state, String action) {
        return state.getGameObjectsOfClass(GameObject.class).stream()
                .filter(gameObject -> getPosition().distanceTo(gameObject.getPosition()) < 2 * Game.SPRITE_SIZE)
                .filter(gameObject -> (gameObject.getAssociatedActions() != null))
                .filter(gameObject -> (gameObject.getAssociatedActions().contains(action)))
                .collect(Collectors.toList());
    }

}
