package story.generic;

import entity.scenery.Corpse;
import entity.human.NPC.NPC;
import state.State;
import story.PlotPoint;

public class KillPlotPoint extends PlotPoint {

    protected NPC target;
    private NPC killer;
    private int updatesSinceLastSeen = 0;
    private final int KILL_THRESHOLD = 200;

    public KillPlotPoint(NPC killer) {
        this.killer = killer;
    }

    @Override
    public void update(State state){
        if(updatesSinceLastSeen >= KILL_THRESHOLD){
            state.spawn(new Corpse(target.getPosition(), target.getSprite("corpse")));
            state.getGameObjects().remove(target);
            isDone = true;
        } else {
            if(!state.getCamera().isInView(killer) && !state.getCamera().isInView(target)){
                updatesSinceLastSeen++;
            } else {
                updatesSinceLastSeen = 0;
            }
        }
    }

}
