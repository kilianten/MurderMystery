import game.Game;
import game.GameLoop;

public class Launcher {

    public static void main(String[] args){
        new Thread(new GameLoop(new Game(1080, 640))).start();
    }

}
