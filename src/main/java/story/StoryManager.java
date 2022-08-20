package story;

import state.State;
import story.sacrilege.Sacrilege;

public class StoryManager {

    KillerStory killerStory;

    public StoryManager(State state){
        killerStory = new Sacrilege(state);
    }

    public void update(State state){
        killerStory.update(state);
    }

}
