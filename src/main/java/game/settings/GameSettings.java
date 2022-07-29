package game.settings;

public class GameSettings {

    private boolean debugMode;
    private final AudioSettings audioSettings;
    private final RenderSettings renderSettings;

    public GameSettings() {
        this.audioSettings = new AudioSettings();
        this.renderSettings = new RenderSettings();
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

    public RenderSettings getRenderSettings() {
        return renderSettings;
    }
}
