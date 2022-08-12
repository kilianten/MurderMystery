package input.mouse.action;

import core.Position;
import entity.Scenery;
import entity.SelectionCircle;
import state.State;
import ui.UIImage;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SceneryTool extends MouseAction {

    private Position dragPosition;
    private List<Scenery> selectedScenery;

    public SceneryTool() {
        selectedScenery = new ArrayList<>();
    }

    @Override
    public void onClick(State state) {

    }

    @Override
    public void onDrag(State state) {
        if(dragPosition == null){
            dragPosition = Position.copyOf(state.getInput().getMousePosition());

            if(!state.getInput().isPressed(KeyEvent.VK_SHIFT)){
                cleanup();
            }

            Position mousePosition = Position.copyOf(state.getInput().getMousePosition());
            mousePosition.add(state.getCamera().getPosition());

            state.getGameObjectsOfClass(Scenery.class).stream()
                    .filter(scenery -> scenery.getCollisionBox().getBounds().contains(mousePosition.getIntX(), mousePosition.getIntY()))
                    .forEach(this::select);
        } else {
            dragPosition.subtract(state.getInput().getMousePosition());
            selectedScenery.forEach(scenery -> scenery.changePositionBy(new Position(-dragPosition.getX(), -dragPosition.getY())));
            dragPosition = Position.copyOf(state.getInput().getMousePosition());
        }
    }

    @Override
    public void onRelease(State state) {
        dragPosition = null;
    }

    @Override
    public void update(State state) {
        if(state.getInput().isPressed(KeyEvent.VK_DELETE)){
            selectedScenery.forEach(state::despawn);
        }
    }

    private void select(Scenery scenery){
        scenery.attach(new SelectionCircle(4));
        selectedScenery.add(scenery);
    }

    private void deselect(Scenery scenery){
        scenery.clearAttachments();
        selectedScenery.remove(scenery);
    }

    @Override
    public UIImage getSprite() {
        return null;
    }

    @Override
    public void cleanup() {
        List.copyOf(selectedScenery).forEach(this::deselect);
    }
}
