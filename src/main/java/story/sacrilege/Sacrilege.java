package story.sacrilege;

import entity.human.NPC.NPC;
import entity.human.NPC.Nolan;
import state.State;
import story.KillerStory;
import story.generic.KillPlotPointBasedOnTrait;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Sacrilege extends KillerStory {

    Nolan nolan;
    NPC accomplice;

    public Sacrilege(State state){
        nolan = state.getGameObjectOfClass(Nolan.class).get();
        killers.add(nolan);
        accomplice = getKillerBasedOnTrait(state, "isReligious", true);
        killers.add(accomplice);
        System.out.println("SACRILEGE religious accomplice: " + accomplice.getFirstName());
        plotPoints.add(new KillPlotPointBasedOnTrait(nolan, killers, "isReligious"));
        plotPoints.add(new KillPlotPointBasedOnTrait(getRandomKiller(), killers, "isReligious"));
        plotPoints.peek().initialize(state);
    }

}
