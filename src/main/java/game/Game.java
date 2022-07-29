package game;

import controller.GameController;
import core.Size;
import display.Display;
import game.settings.GameSettings;
import state.State;
import input.Input;
import state.menu.MenuState;

public class Game {

    private Display display;
    private Input input;
    private State state;
    private GameSettings settings;
    private GameController gameController;
    public static final String GAME_TITLE = "Roaten Island";

    public static int SPRITE_SIZE = 64;

    public Game(int width, int height){
        input = new Input();
        display = new Display(width, height, input);
        settings = new GameSettings();
        state = new MenuState(new Size(width, height), input, settings);
        gameController = new GameController(input);
    }

    public void update(Game game){
        state.update(game);
        gameController.update(this);
    }

    public void render(){
        display.render(state, settings.isDebugMode());
    }

    public GameSettings getSettings() {
        return settings;
    }

    public void enterState(State nextState) {
        state.cleanUp();
        state = nextState;
    }
}
