package entity.human;

import controller.Controller;
import core.Size;
import entity.GameObject;
import entity.SelectionCircle;
import entity.scenery.InteractableScenery;
import entity.scenery.Scenery;
import game.Game;
import state.State;
import graphics.SpriteLibrary;
import state.game.GameState;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Player extends Human {

    private GameObject target;
    private double targetRange;
    private SelectionCircle selectionCircle;

    public Player(Controller controller, SpriteLibrary spriteLibrary){
        super(controller, spriteLibrary);
        this.selectionCircle = new SelectionCircle();
        this.targetRange = 2 * Game.SPRITE_SIZE;
        motion.setSpeed(3);
    }

    @Override
    public void update(State state){
        super.update(state);
        handleTarget(state);
        handleInput(state);
    }

    private void handleInput(State state) {
        if(state.getInput().isPressed(KeyEvent.VK_SPACE)){
            if(target != null){
                target.interact(state, this);
            }
        }
    }

    private void handleTarget(State state) {
        Optional<GameObject> nearestObject =  findNearestGameObject(state);

        if(nearestObject.isPresent()){
            GameObject nearObj = nearestObject.get();
            if(!nearObj.equals(target)){
                if(target != null){
                    target.detach(selectionCircle);
                }
                selectionCircle.resize(Size.copyOf(nearObj.getSelectionCircleSize()), nearObj.getSelectionCircleOffset());
                nearObj.attach(selectionCircle);
                target = nearObj;
            }
        } else {
            if(target != null){
                target.detach(selectionCircle);
                target = null;
            }
        }
    }

    private Optional<GameObject> findNearestGameObject(State state) {
        return state.getGameObjectsOfClassInCurrentLocation(GameObject.class).stream()
                .filter(gameObject -> (gameObject.isInteractable()))
                .filter(gameObject -> getPosition().distanceTo(gameObject.getPosition()) < targetRange)
                .filter(gameObject -> isFacing(gameObject.getPosition()))
                .min(Comparator.comparingDouble(gameObject -> position.distanceTo(gameObject.getPosition())));
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Scenery && !((Scenery) other).isWalkable()){
            motion.stop(willCollideX(other.getCollisionBox()), willCollideY(other.getCollisionBox()));
        }
    }




}
