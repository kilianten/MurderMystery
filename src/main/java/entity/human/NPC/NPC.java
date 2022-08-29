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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class NPC extends Human {

    private AIManager aiManager;
    private Rectangle proximity;
    private boolean alive = true;

    protected boolean religious;
    protected boolean smoker;

    public NPC(Controller controller, SpriteLibrary spriteLibrary, String spriteSheet, ColourHandler colourHandler) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit(spriteSheet, colourHandler));
        interactable = true;
        aiManager = new AIManager(this);
        selectionCircleSize = new Size(32, 10);
    }

    private void calculateProximity() {
        proximity = new Rectangle(
                position.getIntX(),
                position.getIntY(),
                30,
                30
        );
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
    public void interact(State state, Human human) {
        ((GameState) state).toggleConversationBox(true);
        ((GameState) state).getConversationBox().setConversantName(firstName + " " + secondName);
    }

    public boolean isReligious() {
        return religious;
    }

    public AIManager getAiManager() {
        return aiManager;
    }

    public boolean isNear(GameObject gameObject) {
        calculateProximity();
        return proximity.intersects(
                gameObject.getPosition().getIntX(),
                gameObject.getPosition().getIntY(),
                gameObject.getSize().getWidth(),
                gameObject.getSize().getHeight());
    }

    public boolean isAloneWith(State state, NPC target){
        ArrayList<NPC> nearNPCs = (ArrayList) state.getGameObjectsOfClass(NPC.class)
                .stream()
                .filter(gameObject -> isNear(gameObject))
                .collect(Collectors.toList());
        nearNPCs.remove(this);
        if(nearNPCs.size() == 1 && nearNPCs.get(0) == target){
            return true;
        }
        return false;
    }

    public Image getSprite(String spriteName){
        return animationManager.getSprite(spriteName);
    }

    public boolean isAlive() {
        return alive;
    }

    public List getCharacterActions(){
        List<String> characterActions = new ArrayList<String>();
        if(smoker){
            characterActions.add("smoke");
        }
        return characterActions;
    }
}
