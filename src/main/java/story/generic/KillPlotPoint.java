package story.generic;

import entity.scenery.Corpse;
import entity.human.NPC.NPC;
import state.State;
import state.game.ui.UIClue;
import story.PlotPoint;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
            Corpse corpse = new Corpse(target.getPosition(), target.getSprite("corpse"));
            corpse.setClue(new UIClue(state, UIClue.getClue(killer)));
            state.spawn(corpse);

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

    public void initialize(State state){
        target = getRandomCharacter(state);
        if(target == null){
            isDone = true;
        }
    }

    private NPC getRandomCharacter(State state) {
        Random rand = new Random();
        List<NPC> allNPCs = state.getGameObjectsOfClass(NPC.class)
                .stream()
                .filter(npc -> npc.isAlive())
                .collect(Collectors.toList());
        allNPCs.remove(killer);
        if(allNPCs.size() > 0){
            return allNPCs.get(rand.nextInt(allNPCs.size()));
        }
        return null;
    }

}
