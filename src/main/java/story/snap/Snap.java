package story.snap;

import entity.human.NPC.NPC;
import state.State;
import story.KillerStory;
import story.generic.KillPlotPoint;


public class Snap extends KillerStory {

    public Snap(State state){
        addRandomKiller(state);
        addRandomKiller(state);

        plotPoints.add(new KillPlotPoint(getRandomKiller(), killers));
        plotPoints.add(new KillPlotPoint(getRandomKiller(), killers));
        plotPoints.peek().initialize(state);
    }

}
