package ai;

import ai.state.AIState;
import ai.state.Stand;
import ai.state.Wander;
import entity.human.Human;
import entity.human.NPC.NPC;
import entity.human.action.Smoke;
import state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIManager {

    private AIState currentAIState;

    private final List<String> defaultActions = new ArrayList<>() {
        {
            add("stand");
            add("wander");
        }
    };

    public AIManager(NPC npc) {
        transitionTo(npc,"stand");
    }

    public void update(State state, NPC currentCharacter){
        currentAIState.update(state, currentCharacter);
        if(currentAIState.shouldTransition(state, currentCharacter)){
            String nextState = currentAIState.getNextState();
            if(nextState.equals("random")){
                transitionToRandomState(currentCharacter);
            } else {
                transitionTo(currentCharacter, currentAIState.getNextState());
            }
        }
    }

    public AIState getState(){
        return currentAIState;
    }

    public void transitionTo(NPC npc, String nextState) {
        switch(nextState) {
            case "wander":
                currentAIState = new Wander();
                break;
            case "smoke":
                System.out.println("SMOKING");
                npc.perform(new Smoke());
                break;
            default:
                currentAIState = new Stand();
                break;
        }
    }

    public void transitionToRandomState(NPC npc){
        List<String> actions = new ArrayList<>();
        actions.addAll(defaultActions);
        actions.addAll(npc.getCharacterActions());

        Random random = new Random();
        transitionTo(npc, actions.get(random.nextInt(actions.size())));
    }

}
