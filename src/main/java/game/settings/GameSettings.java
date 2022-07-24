package game.settings;

public class GameSettings {

    private boolean debugMode;

    public GameSettings() {
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void toggleDebugMode() {
        debugMode = !debugMode;
    }
}
