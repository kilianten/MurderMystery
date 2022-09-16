package entity.human.memory;

import entity.GameObject;
import entity.scenery.Corpse;
import state.State;

import java.util.HashMap;
import java.util.Map;

public class WitnessedEventHandler {

    Map<String, WitnessedEvent> rememberedEvents;

    public WitnessedEventHandler(){
        rememberedEvents = new HashMap<>();
    }

    public boolean insertWitnessedEvent(State state, GameObject gameObject){
        String eventName = "";
        WitnessedEvent witnessedEvent;

        if(gameObject instanceof Corpse){
            eventName = "DeadBody" + ((Corpse) gameObject).getName();
            witnessedEvent = new WitnessedEvent(state, "I saw the corpse of" + ((Corpse) gameObject).getName());
        } else {
            witnessedEvent = null;
        }

        if(rememberedEvents.containsKey(eventName)){
            return false;
        } else {
            rememberedEvents.put(eventName, witnessedEvent);
            return true;
        }
    }

}
