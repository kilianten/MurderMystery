package story.generic;

import entity.human.NPC.NPC;
import state.State;

import java.util.List;

public class KillPlotPointBasedOnTrait extends KillPlotPoint {

    String traitMethodName;

    public KillPlotPointBasedOnTrait(NPC killer, List<NPC> killers, String traitMethodName) {
        super(killer, killers);
        this.traitMethodName = traitMethodName;
    }

    @Override
    public void initialize(State state){
        target = getCharacterBasedOnTrait(state, traitMethodName, false);
        if(target == null){
            isDone = true;
        }
    }
}
