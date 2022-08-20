package ai.state;

import ai.AITransition;
import controller.NPCController;
import core.Position;
import entity.human.NPC.NPC;
import map.Pathfinder;
import state.State;

import java.util.ArrayList;
import java.util.List;

public class WanderTowardsEntity extends AIState{

    private List<Position> path;
    private Position target;
    private NPC targetEntity;
    public WanderTowardsEntity(NPC targetEntity){
        super();
        path = new ArrayList<>();
        this.targetEntity = targetEntity;
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentCharacter) -> hasArrived(currentCharacter)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if(target == null && targetEntity.isAlive()){
            List<Position> path = Pathfinder.findPath(currentCharacter.getPosition(), targetEntity.getPosition(), state.getGameMap());

            if(!path.isEmpty()){
                target = path.get(path.size() - 1);
                this.path.addAll(path);
            }
        }

        NPCController controller = (NPCController) currentCharacter.getController();

        if(hasArrived(currentCharacter)){
            controller.stop();
        }

        if(!path.isEmpty() && currentCharacter.getPosition().isInRangeOf(path.get(0))){
            path.remove(0);
        }

        if(!path.isEmpty()){
            controller.moveToTarget(path.get(0), currentCharacter.getPosition());
        }
    }

    private boolean hasArrived(NPC currentCharacter){
        return target != null && currentCharacter.getPosition().isInRangeOf(target, 10);
    }
}
