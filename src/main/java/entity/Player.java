package entity;

import controller.Controller;
import entity.human.Human;
import entity.human.NPC.NPC;
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

    public Player(Controller controller, SpriteLibrary spriteLibrary, SelectionCircle selectionCircle){
        super(controller, spriteLibrary);
        this.selectionCircle = selectionCircle;
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
                System.out.println(target);
            }
        }
    }

    private void handleTarget(State state) {
        Optional<NPC> nearestNPC = findNearestNPC(state);

        if(nearestNPC.isPresent()){
            NPC npc = nearestNPC.get();
            if(!npc.equals(target)){
                selectionCircle.setParent(npc);
                target = npc;
            }
        } else {
            selectionCircle.clearParent();
            target = null;
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
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }
}
