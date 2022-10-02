package entity.human.NPC;

import entity.GameObject;
import entity.scenery.Corpse;

public class ReactionManager {

    public String getReaction(GameObject gameObject){
        if(gameObject instanceof Corpse){
            return "My God " + ((Corpse) gameObject).getName() + " is dead";
        }
        return null;
    }

}
