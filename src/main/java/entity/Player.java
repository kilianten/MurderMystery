package entity;

import controller.Controller;
import entity.human.Human;
import entity.human.NPC.NPC;
import entity.scenery.Scenery;
import game.Game;
import state.State;
import graphics.SpriteLibrary;
import state.game.GameState;

import java.util.Comparator;
import java.util.Optional;


public class Player extends Human {

    private NPC target;
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
        Optional<NPC> nearestNPC = findNearestNPC(state);

        if(nearestNPC.isPresent()){
            NPC npc = nearestNPC.get();
            if(!npc.equals(target)){
                if(target != null){
                    target.detach(selectionCircle);
                }
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

    private Optional<NPC> findNearestNPC(State state) {
        return state.getGameObjectsOfClass(NPC.class).stream()
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
