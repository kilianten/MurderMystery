package story;

import entity.human.NPC.NPC;
import state.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public abstract class KillerStory {

    protected Queue<PlotPoint> plotPoints;
    protected List<NPC> killers;

    public KillerStory(){
        plotPoints = new LinkedList<>();
    }

    public void update(State state){
        if(plotPoints.size() > 0){
            if(plotPoints.peek().isDone){
                plotPoints.remove();
            } else {
                plotPoints.peek().update(state);
            }
        }
    }

    public NPC getRandomKiller(){
        Random random = new Random();
        return killers.get(random.nextInt(killers.size()));
    }

}
