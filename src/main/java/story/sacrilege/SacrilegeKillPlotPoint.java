package story.sacrilege;

import entity.human.NPC.NPC;
import state.State;
import story.generic.KillPlotPoint;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SacrilegeKillPlotPoint extends KillPlotPoint  {

    public SacrilegeKillPlotPoint(NPC killer) {
        super(killer);
    }

    @Override
    public void initialize(State state){
        target = getNonReligiousCharacter(state);
    }

    private NPC getNonReligiousCharacter(State state) {
        Random rand = new Random();
        List<NPC> allNPCs = state.getGameObjectsOfClass(NPC.class)
                .stream()
                .filter(npc -> !npc.isReligious())
                .collect(Collectors.toList());
        if(allNPCs.size() > 0){
            return allNPCs.get(rand.nextInt(allNPCs.size()));
        }
        return null;
    }
}
