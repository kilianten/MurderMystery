package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.human.NPC.Douglas;
import entity.human.NPC.Karl;
import entity.human.NPC.Nolan;
import entity.Player;
import entity.SelectionCircle;
import game.ui.UIGameTime;
import input.Input;
import map.GameMap;
import ui.Alignment;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;

public class GameState extends State {

    private boolean paused;

    @Override
    protected void handleMouseInput(){
        super.handleMouseInput();

        if(input.isPressed(KeyEvent.VK_ESCAPE)){
            pause();
        }
    }

    private void pause(){
        paused = true;
        VerticalContainer pauseContainer = new VerticalContainer(camera.getSize());
        pauseContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        pauseContainer.setBackgroundColor(Color.DARK_GRAY);
        pauseContainer.addUIComponent(new UIButton("Button 1", () -> System.out.println("Button 1 pressed")));
        pauseContainer.addUIComponent(new UIButton("Button 2", () -> System.out.println("Button 2 pressed")));
        pauseContainer.addUIComponent(new UIButton("Exit", () -> System.exit(0)));
        uiContainers.add(pauseContainer);
    }

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
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

}
