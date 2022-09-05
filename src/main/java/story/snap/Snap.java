package story.snap;

import entity.human.NPC.NPC;
import state.State;
import story.KillerStory;
import story.generic.KillPlotPoint;


public class Snap extends KillerStory {

    public Snap(State state){
        addRandomKiller(state);
        addRandomKiller(state);

        for(NPC killer: killers){
            System.out.println(killer.getFirstName());
        }

        plotPoints.add(new KillPlotPoint(getRandomKiller(), killers));
        plotPoints.add(new KillPlotPoint(getRandomKiller(), killers));
        plotPoints.peek().initialize(state);
    }

}
