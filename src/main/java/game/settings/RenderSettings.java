package game.settings;

public class RenderSettings {

    private final Setting<Boolean> shouldRenderGrid;

    public RenderSettings() {
        this.shouldRenderGrid = new Setting<>(false);
    }

    public Setting<Boolean> getShouldRenderGrid(){
        return shouldRenderGrid;
    }
}
