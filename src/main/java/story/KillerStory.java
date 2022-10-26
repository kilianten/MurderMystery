package story;

import entity.human.NPC.NPC;
import state.State;

import java.util.*;
import java.util.stream.Collectors;

public abstract class KillerStory {

    protected Queue<PlotPoint> plotPoints;
    protected List<NPC> killers;
    protected boolean storyComplete;

    public KillerStory(){
        plotPoints = new LinkedList<>();
        killers = new ArrayList<>();
    }

    public void update(State state){
        if(!plotPoints.isEmpty()){
            if(plotPoints.peek().isDone){
                plotPoints.remove();
                if(!plotPoints.isEmpty()){
                    plotPoints.peek().initialize(state);
                }
            } else {
                plotPoints.peek().update(state);
            }
        }
    }

    public void addRandomKiller(State state){
        Random rand = new Random();
        List<NPC> potentialNPCs = state.getGameObjectsOfClass(NPC.class)
                .stream()
                .filter(npc -> !killers.contains(npc))
                .filter(npc -> npc.isAlive())
                .collect(Collectors.toList());
        if(!potentialNPCs.isEmpty()){
            killers.add(potentialNPCs.get(rand.nextInt(potentialNPCs.size())));
        }
    }

    public NPC getRandomKiller(){
        Random random = new Random();
        return killers.get(random.nextInt(killers.size()));
    }

    protected NPC getKillerBasedOnTrait(State state, String methodName, boolean negation) {
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
        if(allNPCs.size() > 0){
            return allNPCs.get(rand.nextInt(allNPCs.size()));
        }
        return null;
    }

    public List<NPC> getKillers() {
        return killers;
    }
}
