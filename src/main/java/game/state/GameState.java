package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC.Douglas;
import entity.NPC.Karl;
import entity.NPC.NPC;
import entity.NPC.Nolan;
import entity.Player;
import input.Input;
import map.GameMap;
import ui.UIContainer;

import java.util.Collections;

public class GameState extends State {

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);
        initialiseCharacters();
        initializeUI();
    }

    private void initialiseCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);

        
        initialiseNPCs();
    }

    private void initialiseNPCs() {
        Collections.addAll(
                gameObjects,
                new Douglas(new NPCController(), spriteLibrary),
                new Nolan(new NPCController(), spriteLibrary),
                new Karl(new NPCController(), spriteLibrary));
    }

    private void initializeUI(){
        UIContainer container = new UIContainer();
        uiContainers.add(container);
    }

}
