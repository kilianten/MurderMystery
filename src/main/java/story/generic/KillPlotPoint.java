package story.generic;

import ai.state.WalkTowards;
import ai.state.Wander;
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
    private List<NPC> killers;

    public KillPlotPoint(NPC killer, List<NPC> killers) {
        this.killer = killer;
        this.killers = killers;
    }

    @Override
    public void update(State state){
        if(!(killer.getAiManager().getState() instanceof Wander)){
            if(killer.getAiManager().getState() instanceof WalkTowards){
                if(!state.getCamera().isInView(killer)){
                    killer.setSpeed(3);
                } else {
                    killer.setSpeed(2.0);
                }
            } else {
                if(killer.isNear(target)){
                    if(!state.getCamera().isInView(killer)){
                        killTarget(state);
                        killer.setSpeed(2.0);
                    }
                    killer.getAiManager().setState(target, "wander", state);
                } else {
                    killer.getAiManager().setState(target, "walkToward", state);
                }
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
        allNPCs.removeAll(killers);
        if(allNPCs.size() > 0){
            return allNPCs.get(rand.nextInt(allNPCs.size()));
        }
        return null;
    }

    protected NPC getCharacterBasedOnTrait(State state, String methodName, boolean negation) {
        Random rand = new Random();
        List<NPC> allNPCs = state.getGameObjectsOfClass(NPC.class)
                .stream()
                .filter(npc -> npc.isAlive())
                .filter(npc -> {
                    try {
                        return npc.getTrait(methodName, negation);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());
        allNPCs.removeAll(killers);
        if(allNPCs.size() > 0){
            return allNPCs.get(rand.nextInt(allNPCs.size()));
        }
        return null;
    }

    public void killTarget(State state){
        Corpse corpse = new Corpse(target);
        corpse.setClue(new UIClue(state, UIClue.getClue(killer)));
        state.spawn(target.getLocation(), corpse);

        state.getLocation(corpse.getLocation()).getGameObjects().remove(target);
        isDone = true;
    }

}
