package input.mouse.action;

import core.Position;
import entity.scenery.Scenery;
import state.State;
import ui.UIImage;

public class SceneryPlacer extends MouseAction {

    private Scenery scenery;
    private UIImage preview;

    public SceneryPlacer(Scenery scenery) {
        this.scenery = scenery;
        preview = new UIImage(scenery.getSprite());
    }

    @Override
    public void onClick(State state) {
        state.spawn(Scenery.copyOf(scenery));
    }

    @Override
    public void onDrag(State state) {}

    @Override
    public void onRelease(State state) {}

    @Override
    public void update(State state) {
        Position position = Position.copyOf(state.getInput().getMousePosition());
        position.add(state.getCamera().getPosition());

        scenery.setPosition(position);
        preview.setAbsolutePosition(scenery.getRenderPosition(state.getCamera()));
    }

    @Override
    public UIImage getSprite() {
        return preview;
    }

    @Override
    public void cleanup() {

    }
}
