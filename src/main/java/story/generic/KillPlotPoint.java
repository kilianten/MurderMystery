package story.generic;

import entity.scenery.Corpse;
import entity.human.NPC.NPC;
import state.State;
import story.PlotPoint;

public class KillPlotPoint extends PlotPoint {

    NPC target;
    NPC killer;

    public KillPlotPoint(NPC killer, NPC target) {
        this.killer = killer;
        this.target = target;
    }

    @Override
    public void update(State state){

    }

}
