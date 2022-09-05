package story.inkedRed;

import entity.human.NPC.NPC;
import entity.human.NPC.Vanessa;
import state.State;
import story.KillerStory;
import story.generic.KillPlotPointBasedOnTrait;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class InkedRed extends KillerStory {

    Vanessa vanessa;
    NPC accomplice;

    public InkedRed(State state){
        vanessa = state.getGameObjectOfClass(Vanessa.class).get();
        killers.add(vanessa);
        killers.add(accomplice);
        plotPoints.add(new KillPlotPointBasedOnTrait(vanessa, killers, "hasTattoo"));
        plotPoints.add(new KillPlotPointBasedOnTrait(vanessa, killers, "hasTattoo"));
        plotPoints.peek().initialize(state);
    }

    public void getReligiousCharacter(State state){
        Random rand = new Random();
        List<NPC> allNPCs = state.getGameObjectsOfClass(NPC.class)
                .stream()
                .filter(npc -> npc.isReligious())
                .collect(Collectors.toList());
        allNPCs.remove(vanessa);
        accomplice = allNPCs.get(rand.nextInt(allNPCs.size()));
    }
}
