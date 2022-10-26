package state.game.event;

import state.State;
import state.game.time.GameTime;

public abstract class Event {

    private GameTime startTime;
    private GameTime endTime;
    private boolean isSetUp = false;

    public void update(State state){
        if(!isSetUp){
            isSetUp = setUpEvent();
        }
    }

    public void startEvent(State state){

    }

    public abstract boolean meetsRequirements(State state);

    public abstract boolean setUpEvent();

}
