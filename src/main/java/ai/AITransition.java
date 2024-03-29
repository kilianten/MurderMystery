package ai;

import entity.human.NPC.NPC;
import state.State;

public class AITransition {
    private String nextState;
    private AICondition condition;

    public AITransition(String nextState, AICondition condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    public boolean shouldTransition(State state, NPC currenctCharacter){
        return condition.isMet(state, currenctCharacter);
    }

    public String getNextState() {
        return nextState;
    }
}
