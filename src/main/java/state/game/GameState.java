package state.game;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.GameObject;
import entity.human.NPC.Douglas;
import entity.human.NPC.Karl;
import entity.human.NPC.Nolan;
import entity.human.Player;
import game.settings.GameSettings;
import graphics.SpriteLibrary;
import state.game.ui.ConversationBox;
import state.game.ui.UIGameMenu;
import state.game.ui.UIGameTime;
import input.Input;
import state.State;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIContainer;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;

public class GameState extends State {

    protected boolean paused;

    private UIGameMenu gameMenu;
    private ConversationBox conversationBox;
    private boolean conversating;

    public GameState(Size windowSize, Input input, GameSettings settings) {
        super(windowSize, input, settings);
        loadGameMap();
        initialiseCharacters();
        initializeUI(windowSize);
        gameMenu = new UIGameMenu(windowSize, input, settings);
        conversationBox = new ConversationBox(windowSize);
    }

    protected void updateGameObjects() {
        if(!paused){
            super.updateGameObjects();
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
    public void setDefaultSettings() {
        settings.getRenderSettings().getShouldRenderGrid().setValue(false);
    }

    private void initialiseCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        player.setPosition(gameMap.getRandomAvailablePosition());
        gameObjects.add(player);
        camera.focusOn(player);

        initialiseNPCs(spriteLibrary);
    }

    private void initialiseNPCs(SpriteLibrary spriteLibrary) {
        for(int i = 0;i < 33; i++){
            Karl karl = new Karl(new NPCController(), spriteLibrary);
            Douglas douglas = new Douglas(new NPCController(), spriteLibrary);
            Nolan nolan = new Nolan(new NPCController(), spriteLibrary);
            nolan.setPosition(gameMap.getRandomAvailablePosition());
            douglas.setPosition(gameMap.getRandomAvailablePosition());
            karl.setPosition(gameMap.getRandomAvailablePosition());
            gameObjects.add(karl);
            gameObjects.add(douglas);
            gameObjects.add(nolan);
        }

    }

    private void initializeUI(Size windowSize) {
         uiContainers.add(new UIGameTime(windowSize));
    }

    public void toggleConversationBox(boolean isConversating){
        if(isConversating){
            paused = true;
            conversating = true;
            toggleMenu(true, conversationBox);
        } else {
            paused = false;
            toggleMenu(false, conversationBox);
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
}
