package game;

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

    public static int SPRITE_SIZE = 64;

    public Game(int width, int height){
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(new Size(width, height), input);
        settings = new GameSettings(true);
    }

    public void update(){
        state.update();
    }

    public void render(){
        display.render(state, settings.isDebugMode());
    }

}
