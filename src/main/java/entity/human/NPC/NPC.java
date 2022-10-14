package entity.human.NPC;

import ai.AIManager;
import controller.Controller;
import core.Size;
import entity.ColourHandler;
import entity.GameObject;
import entity.human.Player;
import entity.human.action.Speaking;
import entity.human.memory.WitnessedEventHandler;
import entity.scenery.Scenery;
import entity.human.Human;
import entity.human.effect.Effect;
import speech.NPCSpeech;
import state.State;
import graphics.AnimationManager;
import graphics.SpriteLibrary;
import state.game.GameState;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class NPC extends Human {

    private AIManager aiManager;
    private boolean alive = true;
    protected String title;

    protected boolean religious;
    protected boolean smoker;
    protected boolean interestedInGettingTattoo;
    protected boolean hasTattoos;
    protected boolean drinksAlcohol = true;
    protected ReactionManager reactionManager;

    protected boolean isLocal = true;

    protected NPCSpeech speech;

    protected WitnessedEventHandler witnessedEventHandler;

    private boolean isJailed;

    private String jailedReason = "";

    public NPC(Controller controller, SpriteLibrary spriteLibrary, String spriteSheet, ColourHandler colourHandler) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit(spriteSheet, colourHandler));
        interactable = true;
        aiManager = new AIManager(this);
        selectionCircleSize = new Size(32, 10);
        setTraits();
        reactionManager = new ReactionManager();
        witnessedEventHandler = new WitnessedEventHandler();
    }

    @Override
    public void update(State state){
        super.update(state);
        aiManager.update(state, this);
        for(GameObject gameObject : getNearViewableObjects(state)){
            String thought = reactionManager.getReaction(gameObject);
            if(thought != null){
                if(witnessedEventHandler.insertWitnessedEvent(state, gameObject)
                        && (!action.isPresent()
                        || !(action.get() instanceof Speaking))){
                    perform(new Speaking(thought, state, this));
                }

            }
        }
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
        ((GameState) state).getConversationBox().setConversant(this);
    }

    public AIManager getAiManager() {
        return aiManager;
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

    public String getSecondName() {
        return secondName;
    }

    public NPCSpeech getSpeech(String option) {
        return speech;
    }

    public void setTraits(){}

    protected boolean randomiseTrait(double chance) {
        if(Math.random() <= chance){
            return true;
        }
        return false;
    }

    public boolean isReligious() {
        return religious;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public boolean hasTattoo(){
        return hasTattoos;
    }

    public boolean isInterestedInGettingTattoo(){
        return interestedInGettingTattoo;
    }

    public void randomiseTattooTraits(){
        hasTattoos = randomiseTrait(0.3);
        if(!hasTattoos){
            interestedInGettingTattoo = randomiseTrait(0.2);
        }
    }

    public boolean getTrait(String methodName, boolean negation) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object[] parameters = new Object[1];
        parameters[0] = parameters;
        Method method = NPC.class.getMethod(methodName, null);
        if(negation){
            return (Boolean) method.invoke(this);
        } else {
            return !(Boolean) method.invoke(this);
        }
    }

    public boolean drinksAlcohol() {
        return drinksAlcohol;
    }

    public String getTitle(){
        return title;
    }

    public boolean isLocal(){
        return isLocal;
    }

    private List<GameObject> getNearObjects(State state){
        return state.getAllGameObjects()
                .stream()
                .filter(gameObject -> isNear(gameObject))
                .collect(Collectors.toList());
    }

    private List<GameObject> getNearViewableObjects(State state){
        return state.getAllGameObjects()
                .stream()
                .filter(gameObject -> isNear(gameObject))
                .filter(gameObject -> isFacing(gameObject.getPosition()))
                .collect(Collectors.toList());
    }

    public boolean isJailed() {
        return isJailed;
    }

    public void setJailed(boolean jailed) {
        isJailed = jailed;
    }

    public void setJailedReason(String reason) {
        this.jailedReason = reason;
    }

    public String getJailedReason() {
        return jailedReason;
    }
}

