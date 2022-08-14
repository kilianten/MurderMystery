package entity.human.NPC;

import ai.AIManager;
import controller.Controller;
import entity.ColourHandler;
import entity.GameObject;
import entity.Player;
import entity.Scenery;
import entity.human.Human;
import entity.human.effect.Effect;
import state.State;
import graphics.AnimationManager;
import graphics.SpriteLibrary;

public abstract class NPC extends Human {

    private AIManager aiManager;

    public NPC(Controller controller, SpriteLibrary spriteLibrary, String spriteSheet, ColourHandler colourHandler) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit(spriteSheet, colourHandler));

        aiManager = new AIManager();
    }

    @Override
    public void update(State state){
        super.update(state);
        aiManager.update(state, this);
    }

    public void addEffect(Effect effect){
        //effects.add(effect);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player ||
                (other instanceof Scenery && !((Scenery) other).isWalkable())){
            motion.stop(willCollideX(other.getCollisionBox()), willCollideY(other.getCollisionBox()));
        }
    }

}
