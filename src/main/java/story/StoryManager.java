package story;

import entity.human.NPC.NPC;
import state.State;
import story.inkedRed.InkedRed;
import story.sacrilege.Sacrilege;
import story.snap.Snap;

import java.util.List;
import java.util.Random;

public class StoryManager {

    private KillerStory killerStory;
    private final String[] KILLER_STORIES = {"Sacrilege"};

    public StoryManager(State state){
        killerStory = getRandomStory(state);
    }

    private KillerStory getRandomStory(State state) {
        Random random = new Random();
        String story = KILLER_STORIES[random.nextInt(KILLER_STORIES.length)];
        switch (story){
            case "Sacrilege":
                return new Sacrilege(state);
            case "InkedRed":
                return new InkedRed(state);
            default:
                return new Snap(state);
        }
    }

    public void update(State state){
        killerStory.update(state);
    }

    public List<NPC> getKillers(){
        return killerStory.getKillers();
    }

}
