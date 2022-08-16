package state.game;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.human.NPC.Douglas;
import entity.human.NPC.Karl;
import entity.human.NPC.Nolan;
import entity.human.Player;
import game.settings.GameSettings;
import graphics.SpriteLibrary;
import state.game.ui.ConversationBox;
import state.game.ui.UIGameTime;
import input.Input;
import state.State;
import state.menu.MenuState;
import ui.Alignment;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;

public class GameState extends State {

    @Override
    public void handleKeyInput(){
        if(input.isPressed(KeyEvent.VK_ESCAPE)){
            paused = !paused;
            if(paused){
                pause();
            }
        }
    }

    @Override
    public void setDefaultSettings() {
        settings.getRenderSettings().getShouldRenderGrid().setValue(false);
    }


    private void pause(){
        paused = true;
        VerticalContainer pauseContainer = new VerticalContainer(camera.getSize());
        pauseContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        pauseContainer.setBackgroundColor(Color.DARK_GRAY);
        pauseContainer.addUIComponent(new UIButton("Menu", (state) -> state.setNextState(new MenuState(windowSize, input, settings))));
        pauseContainer.addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
        uiContainers.add(pauseContainer);
    }

    public GameState(Size windowSize, Input input, GameSettings settings) {
        super(windowSize, input, settings);
        loadGameMap();
        initialiseCharacters();
        initializeUI(windowSize);
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

    public void startConversation(){
        ConversationBox conversationBox = new ConversationBox();
        conversationBox.setSize(new Size(600, 200));
        UIElements.add(conversationBox);
    }
}
