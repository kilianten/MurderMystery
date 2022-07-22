package entity.NPC;

import ai.AIManager;
import controller.Controller;
import entity.ColourHandler;
import entity.GameObject;
import entity.MovingEntity;
import entity.Player;
import entity.effect.Effect;
import game.state.State;
import graphics.AnimationManager;
import graphics.SpriteLibrary;

public abstract class NPC extends MovingEntity {

    private AIManager aiManager;

    public NPC(Controller controller, SpriteLibrary spriteLibrary, String spriteSheet,ColourHandler colourHandler) {
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
        effects.add(effect);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player){
            motion.stop();
        }
    }

}
