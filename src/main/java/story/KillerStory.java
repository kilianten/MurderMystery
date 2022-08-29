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

    public void getRandomCharacter(State state){
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

}
