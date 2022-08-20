package ai;

import ai.state.AIState;
import ai.state.Stand;
import ai.state.Wander;
import ai.state.WanderTowardsEntity;
import entity.human.NPC.NPC;
import state.State;

public class AIManager {

    private AIState currentAIState;

    public AIManager() {
        transitionTo("stand");
    }

    public void update(State state, NPC currentCharacter){
        currentAIState.update(state, currentCharacter);
        System.out.println(currentAIState);
        if(currentAIState.shouldTransition(state, currentCharacter)){
            transitionTo(currentAIState.getNextState());
        }
    }

    public AIState getState(){
        return currentAIState;
    }

    public void transitionTo(String nextState) {
        switch(nextState) {
            case "wander":
                currentAIState = new Wander();
                break;
            default:
                currentAIState = new Stand();
                break;
        }
    }

    public void transitionToWanderToEntity(NPC targetEntity){
        currentAIState = new WanderTowardsEntity(targetEntity);

    }
}
