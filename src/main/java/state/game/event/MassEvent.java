package state.game.event;

import entity.human.NPC.Nolan;
import state.State;

public class MassEvent extends Event {

    public MassEvent(){

    }


    public void update(){

    }

    @Override
    public boolean meetsRequirements(State state) {
        if(state.getGameObjectsOfClass(Nolan.class).isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean setUpEvent() {

        return false;
    }
}
