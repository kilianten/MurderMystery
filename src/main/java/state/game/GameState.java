package state.game;

import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import entity.environment.Lighting;
import entity.human.NPC.*;
import entity.human.Player;
import game.settings.GameSettings;
import graphics.SpriteLibrary;
import speech.SpeechManager;
import state.game.ui.ConversationBoxContainer;
import state.game.ui.UIGameMenu;
import state.game.ui.UIGameTime;
import input.Input;
import state.State;
import story.StoryManager;
import ui.UIContainer;

import java.awt.event.KeyEvent;

public class GameState extends State {

    protected boolean paused;

    private UIGameMenu gameMenu;
    private ConversationBoxContainer conversationBoxContainer;
    private boolean conversating;
    private StoryManager storyManager;
    private SpeechManager speechManager;

    public GameState(Size windowSize, Input input, GameSettings settings, String gameMap) {
        super(windowSize, input, settings);
        loadGameMap(gameMap);
        initialiseCharacters();
        initializeUI(windowSize);
        gameMenu = new UIGameMenu(windowSize, input, settings);
        conversationBoxContainer = new ConversationBoxContainer(windowSize);
        storyManager = new StoryManager(this);
        speechManager = new SpeechManager();
        lighting = new Lighting(windowSize, this);
    }

    protected void updateGameObjects() {
        if(!paused){
            super.updateGameObjects();
            storyManager.update(this);
            gameTimeManager.update(this);
        }
    }

    @Override
    public void handleKeyInput(){
        if(input.isPressed(KeyEvent.VK_ESCAPE)){
            togglePause(!paused);
            if(conversating){
                toggleConversationBox(false);
                conversating = false;
            }
        }
    }

    @Override
    public void setDefaultSettings() { }

    private void initialiseCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        player.setPosition(gameMap.getRandomAvailablePosition());
        gameObjects.add(player);
        camera.focusOn(player);

        initialiseNPCs(spriteLibrary, player);
    }

    private void initialiseNPCs(SpriteLibrary spriteLibrary, Player player) {
        for(int i = 0;i < 1; i++){
            Karl karl = new Karl(new NPCController(), spriteLibrary);
            Douglas douglas = new Douglas(new NPCController(), spriteLibrary);
            Nolan nolan = new Nolan(new NPCController(), spriteLibrary);
            Eduardo eduardo = new Eduardo(new NPCController(), spriteLibrary);
            Raquel raquel = new Raquel(new NPCController(), spriteLibrary);
            nolan.setPosition(gameMap.getRandomAvailablePosition());
            douglas.setPosition(gameMap.getRandomAvailablePosition());
            karl.setPosition(gameMap.getRandomAvailablePosition());
            eduardo.setPosition(gameMap.getRandomAvailablePosition());
            raquel.setPosition(gameMap.getRandomAvailablePosition());
            gameObjects.add(raquel);
            gameObjects.add(karl);
            gameObjects.add(douglas);
            //gameObjects.add(nolan);
            gameObjects.add(eduardo);
        }
        Nolan nolan = new Nolan(new NPCController(), spriteLibrary);
        Position position = gameMap.getRandomAvailablePosition();
        nolan.setPosition(position);
        gameObjects.add(nolan);
        player.setPosition(Position.copyOf(nolan.getPosition()));
    }

    private void initializeUI(Size windowSize) {
         uiContainers.add(new UIGameTime(windowSize));
    }

    public void toggleConversationBox(boolean isConversating){
        if(isConversating){
            paused = true;
            conversating = true;
            conversationBoxContainer.reset();
            toggleMenu(true, conversationBoxContainer);
        } else {
            paused = false;
            toggleMenu(false, conversationBoxContainer);
        }
    }

    public void togglePause(boolean shouldPause){
        if(shouldPause){
            paused = true;
            toggleMenu(true, gameMenu);
        } else {
            paused = false;
            toggleMenu(false, gameMenu);
        }
    }

    private void toggleMenu(boolean shouldShowMenu, UIContainer container) {
        if(shouldShowMenu && !uiContainers.contains(container)){
            uiContainers.add(container);
        } else if (!shouldShowMenu){
            uiContainers.remove(container);
        }
    }

    public ConversationBoxContainer getConversationBox() {
        return conversationBoxContainer;
    }

    @Override
    public void cleanUp(){
        super.cleanUp();
    }

    public SpeechManager getSpeechManager() {
        return speechManager;
    }

}
