package input.mouse.action;

import core.Position;
import entity.scenery.InteractableScenery;
import entity.scenery.Scenery;
import entity.SelectionCircle;
import state.State;
import ui.UIImage;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SceneryTool extends MouseAction {

    private Position dragPosition;
    private Set<Scenery> selectedScenery;

    public SceneryTool() {
        selectedScenery = new HashSet<>();
    }

    @Override
    public void update(State state) {
        if(state.getInput().isPressed(KeyEvent.VK_DELETE)) {
            selectedScenery.forEach(state::despawn);
        }
        if(state.getInput().isPressed(KeyEvent.VK_RIGHT)){
            selectedScenery.forEach(scenery -> {scenery.moveCollisionBoxRight();});
        }
        if(state.getInput().isPressed(KeyEvent.VK_UP)){
            selectedScenery.forEach(scenery -> {scenery.moveCollisionBoxUp();});
        }
        if(state.getInput().isPressed(KeyEvent.VK_LEFT)){
            selectedScenery.forEach(scenery -> {scenery.moveCollisionBoxLeft();});
        }
        if(state.getInput().isPressed(KeyEvent.VK_DOWN)){
            selectedScenery.forEach(scenery -> {scenery.moveCollisionBoxDown();});
        }
        if(state.getInput().isRightMousePressed()){
            cleanup();
        }
    }

    private void select(Scenery scenery) {
        if(!selectedScenery.contains(scenery)){
            if(scenery.isInteractable()){
                scenery.attach(new SelectionCircle(scenery));
            }
            else {
                scenery.attach(new SelectionCircle(4));
            }
            selectedScenery.add(scenery);
        }
    }

    private void deselect(Scenery scenery) {
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

    @Override
    public void onClick(State state) {

    }

    @Override
    public void onDrag(State state) {
        if(dragPosition == null) {
            dragPosition = Position.copyOf(state.getInput().getMousePosition());

            if(!state.getInput().isCurrentlyPressed(KeyEvent.VK_SHIFT)) {
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
}
