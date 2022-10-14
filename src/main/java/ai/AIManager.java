package ai;

import ai.state.AIState;
import ai.state.Stand;
import ai.state.Wander;
import core.Direction;
import core.Position;
import entity.human.Human;
import entity.human.NPC.NPC;
import entity.human.action.Sit;
import entity.human.action.Smoke;
import entity.scenery.Bench;
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
        transitionTo(npc,"stand", null);
    }

    public void update(State state, NPC currentCharacter){
        currentAIState.update(state, currentCharacter);
        if(currentAIState.shouldTransition(state, currentCharacter)){
            String nextState = currentAIState.getNextState();
            if(nextState.equals("random")){
                transitionToRandomState(state, currentCharacter);
            } else {
                transitionTo(currentCharacter, currentAIState.getNextState(), state);
            }
        }
    }

    public AIState getState(){
        return currentAIState;
    }

    public void transitionTo(NPC npc, String nextState, State state) {
        switch(nextState) {
            case "wander":
                currentAIState = new Wander();
                break;
            case "smoke":
                npc.perform(new Smoke(npc));
                break;
            case "sit":
                Bench bench = (Bench) npc.findNearObjectsOfAction(state, "sit").get(0);
                bench.interact(state, npc);
                break;
            default:
                currentAIState = new Stand();
                break;
        }
    }

    public void transitionToRandomState(State state, NPC npc){
        List<String> actions = new ArrayList<>();

        actions.addAll(defaultActions);
        actions.addAll(npc.getCharacterActions());

        if(npc.isJailed()){
            actions.remove("wander");
        }

        actions.addAll(npc.findNearObjectActionionables(state));

        Random random = new Random();
        transitionTo(npc, actions.get(random.nextInt(actions.size())), state);
    }

}
