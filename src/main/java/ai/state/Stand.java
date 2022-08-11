package ai.state;

import ai.AITransition;
import entity.human.NPC.NPC;
import state.State;

public class Stand extends AIState {

    private int updatesAlive;

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("wander", ((state, currentCharacter) -> updatesAlive >= state.getClock().getUpdatesFromSeconds(3)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        updatesAlive++;
    }
}
