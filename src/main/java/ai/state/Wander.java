package ai.state;

import ai.AITransition;
import controller.NPCController;
import core.Position;
import entity.NPC;
import state.State;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AIState{

    private List<Position> targets;

    public Wander(){
        super();
        targets = new ArrayList<>();
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentCharacter) -> hasArrived(currentCharacter)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if(targets.isEmpty()){
            targets.add(state.getRandomPosition());
        }

        NPCController controller = (NPCController) currentCharacter.getController();
        controller.moveToTarget(targets.get(0), currentCharacter.getPosition());

        if(hasArrived(currentCharacter)){
            controller.stop();
        }
    }

    private boolean hasArrived(NPC currentCharacter){
        return currentCharacter.getPosition().isInRangeOf(targets.get(0));
    }
}
