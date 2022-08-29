package story;

import state.State;
import story.sacrilege.Sacrilege;
import story.snap.Snap;

import java.util.Random;

public class StoryManager {

    private KillerStory killerStory;
    private final String[] killerStories = {"Sacrilege", "Snap"};

    public StoryManager(State state){
        killerStory = getRandomStory(state);
    }

    private KillerStory getRandomStory(State state) {
        Random random = new Random();
        String story = killerStories[random.nextInt(killerStories.length)];
        switch (story){
            case "Sacrilege":
                return new Sacrilege(state);
            default:
                return new Snap(state);
        }
    }

    public void update(State state){
        killerStory.update(state);
    }

}
