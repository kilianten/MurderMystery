package story.snap;

import entity.human.NPC.NPC;
import entity.human.NPC.Nolan;
import state.State;
import story.KillerStory;
import story.generic.KillPlotPoint;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Snap extends KillerStory {

    public Snap(State state){
        getRandomCharacter(state);
        getRandomCharacter(state);
        getRandomCharacter(state);
        getRandomCharacter(state);

        plotPoints.add(new KillPlotPoint(getRandomKiller()));
        plotPoints.add(new KillPlotPoint(getRandomKiller()));
        plotPoints.peek().initialize(state);
    }

}
