package ai;

import entity.human.NPC.NPC;
import state.State;

public interface AICondition {
    boolean isMet(State state, NPC currentCharacter);
}
