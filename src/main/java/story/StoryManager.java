package story;

import state.State;
import story.inkedRed.InkedRed;
import story.sacrilege.Sacrilege;
import story.snap.Snap;

import java.util.Random;

public class StoryManager {

    private KillerStory killerStory;
    private final String[] KILLER_STORIES = {"Sacrilege", "Snap", "InkedRed"};

    public StoryManager(State state){
        killerStory = getRandomStory(state);
        System.out.println(killerStory);
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

}
