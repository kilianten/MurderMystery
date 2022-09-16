package entity.human.NPC;

import entity.GameObject;
import entity.scenery.Corpse;

public class ReactionManager {

    public String getReaction(GameObject gameObject){
        if(gameObject instanceof Corpse){
            return "Oh a body";
        }
        return null;
    }

}
