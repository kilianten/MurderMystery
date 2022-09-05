package story.sacrilege;

import entity.human.NPC.NPC;
import entity.human.NPC.Nolan;
import state.State;
import story.KillerStory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Sacrilege extends KillerStory {

    Nolan nolan;
    NPC accomplice;

    public Sacrilege(State state){
        nolan = state.getGameObjectOfClass(Nolan.class).get();
        killers.add(nolan);
        killers.add(accomplice);
        plotPoints.add(new SacrilegeKillPlotPoint(nolan, killers));
        plotPoints.add(new SacrilegeKillPlotPoint(nolan, killers));
        plotPoints.peek().initialize(state);
    }



    public void getReligiousCharacter(State state){
        Random rand = new Random();
        List<NPC> allNPCs = state.getGameObjectsOfClass(NPC.class)
                .stream()
                .filter(npc -> npc.isReligious())
                .collect(Collectors.toList());
        allNPCs.remove(nolan);
        accomplice = allNPCs.get(rand.nextInt(allNPCs.size()));
    }
}
