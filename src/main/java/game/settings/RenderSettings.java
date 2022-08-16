package game.settings;

public class RenderSettings {

    private final Setting<Boolean> grid;
    private final Setting<Boolean> collisionBox;
    private final Setting<Boolean> pathable;

    public RenderSettings() {
        this.grid = new Setting<>(false);

        collisionBox = new Setting<>(false);
        pathable = new Setting<>(false);
    }

    public Setting<Boolean> getShouldRenderGrid(){
        return grid;
    }

    public Setting<Boolean> getCollisionBox() {
        return collisionBox;
    }

    public Setting<Boolean> getPathable() {
        return pathable;
    }
}
