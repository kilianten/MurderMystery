package entity.human;

import controller.Controller;
import entity.GameObject;
import entity.SelectionCircle;
import entity.scenery.Scenery;
import game.Game;
import state.State;
import graphics.SpriteLibrary;
import state.game.GameState;

import java.util.Comparator;
import java.util.Optional;


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
        if(controller.isRequestingAction()){
            if(target != null){
                ((GameState) state).startConversation();
            }
        }
    }

    private void handleTarget(State state) {
        Optional<GameObject> nearestNPC = findNearestNPC(state);

        if(nearestNPC.isPresent()){
            GameObject npc = nearestNPC.get();
            if(!npc.equals(target)){
                if(target != null){
                    target.detach(selectionCircle);
                }
                selectionCircle.resize(npc.getSelectionCircleSize(), npc.getSelectionCircleOffset());
                npc.attach(selectionCircle);
                target = npc;
            }
        } else {
            if(target != null){
                target.detach(selectionCircle);
                target = null;
            }
        }
    }

    private Optional<GameObject> findNearestNPC(State state) {
        return state.getGameObjectsOfClass(GameObject.class).stream()
                .filter(npc -> (npc.isInteractable()))
                .filter(npc -> getPosition().distanceTo(npc.getPosition()) < targetRange)
                .filter(npc -> isFacing(npc.getPosition()))
                .min(Comparator.comparingDouble(npc -> position.distanceTo(npc.getPosition())));
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Scenery && !((Scenery) other).isWalkable()){
            motion.stop(willCollideX(other.getCollisionBox()), willCollideY(other.getCollisionBox()));
        }
    }




}
