package game.settings;

public class GameSettings {

    private boolean debugMode;
    private AudioSettings audioSettings;

    public GameSettings() {
        this.audioSettings = new AudioSettings();
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void toggleDebugMode() {
        debugMode = !debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public AudioSettings getAudioSettings() {
        return audioSettings;
    }

}
