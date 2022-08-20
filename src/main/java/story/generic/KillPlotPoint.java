package story.generic;

import ai.state.Wander;
import ai.state.WanderTowardsEntity;
import core.Position;
import entity.human.NPC.Nolan;
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
        boolean isWithinRange = killer.getPosition().isInRangeOf(target.getPosition(), 40);
        if(!(killer.getAiManager().getState() instanceof WanderTowardsEntity)){
            killer.getAiManager().transitionToWanderToEntity(target);
        } else if(isWithinRange && target.isAlive()){//&& killer.isAloneWith(state, target)
            target.kill();
            state.spawn(new Corpse(target.getPosition(), target.getSprite("corpse")));
            state.getGameObjects().remove(target);
            isDone = true;
        }
    }

}
