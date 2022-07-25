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

import java.util.Collections;

public class GameState extends State {

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
