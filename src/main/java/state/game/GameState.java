package state.game;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.human.NPC.Douglas;
import entity.human.NPC.Karl;
import entity.human.NPC.Nolan;
import entity.Player;
import entity.SelectionCircle;
import game.settings.GameSettings;
import state.game.ui.ConversationBox;
import state.game.ui.UIGameTime;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIComponent;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.Collections;

public class GameState extends State {

    @Override
    protected void handleMouseInput(){
        super.handleMouseInput();
    }

    @Override
    public void handleKeyInput(){
        if(input.isPressed(KeyEvent.VK_ESCAPE)){
            paused = !paused;
            if(paused){
                pause();
            }
        }
    }


    private void pause(){
        paused = true;
        VerticalContainer pauseContainer = new VerticalContainer(camera.getSize());
        pauseContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        pauseContainer.setBackgroundColor(Color.DARK_GRAY);
        pauseContainer.addUIComponent(new UIButton("Menu", (state) -> state.setNextState(new MenuState(windowSize, input, settings))));
        pauseContainer.addUIComponent(new UIButton("Button 2", (state) -> System.out.println("Button 2 pressed")));
        pauseContainer.addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
        uiContainers.add(pauseContainer);
    }

    public GameState(Size windowSize, Input input, GameSettings settings) {
        super(windowSize, input, settings);
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);
        initialiseCharacters();
        initializeUI(windowSize);
    }

    private void initialiseCharacters() {
        SelectionCircle selectionCircle = new SelectionCircle();

        Player player = new Player(new PlayerController(input), spriteLibrary, selectionCircle);
        gameObjects.add(player);
        camera.focusOn(player);

        gameObjects.add(selectionCircle);

        initialiseNPCs();
    }

    private void initialiseNPCs() {
        Collections.addAll(
                gameObjects,
                new Douglas(new NPCController(), spriteLibrary),
                new Nolan(new NPCController(), spriteLibrary),
                new Karl(new NPCController(), spriteLibrary));
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
