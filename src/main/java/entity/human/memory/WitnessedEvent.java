package entity.human.memory;

import state.State;
import state.game.time.GameTime;

public class WitnessedEvent {

    private GameTime time;
    private String EventDescription;

    public WitnessedEvent(State state, String eventDescription) {
        EventDescription = eventDescription;
        time = state.getGameTimeManager().getCurrentTime();
    }

}
