package game;

import controller.GameController;
import core.Size;
import display.Display;
import game.settings.GameSettings;
import game.state.GameState;
import game.state.State;
import input.Input;

public class Game {

    private Display display;
    private Input input;
    private State state;
    private GameSettings settings;
    private GameController gameController;

    public static int SPRITE_SIZE = 64;

    public Game(int width, int height){
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(new Size(width, height), input);
        settings = new GameSettings();
        gameController = new GameController(input);
    }

    public void update(){
        state.update();
        gameController.update(this);
    }

    public void render(){
        display.render(state, settings.isDebugMode());
    }

    public GameSettings getSettings() {
        return settings;
    }
}
