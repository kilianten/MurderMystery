package entity.human.NPC;

import ai.AIManager;
import controller.Controller;
import core.Size;
import entity.ColourHandler;
import entity.GameObject;
import entity.human.Player;
import entity.scenery.Scenery;
import entity.human.Human;
import entity.human.effect.Effect;
import state.State;
import graphics.AnimationManager;
import graphics.SpriteLibrary;
import state.game.GameState;

public abstract class NPC extends Human {

    private AIManager aiManager;

    public NPC(Controller controller, SpriteLibrary spriteLibrary, String spriteSheet, ColourHandler colourHandler) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit(spriteSheet, colourHandler));
        interactable = true;
        aiManager = new AIManager();
        selectionCircleSize = new Size(32, 10);
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

    @Override
    public void interact(State state, Player player) {
        ((GameState) state).toggleConversationBox(true);
    }
}
