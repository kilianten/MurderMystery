package game.state;

import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Clock;
import graphics.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;

    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Clock clock;

    protected List<UIContainer> uiContainers;

    public State(Size windowSize, Input input) {
        gameObjects = new ArrayList<>();
        uiContainers = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        this.input = input;
        this.camera = new Camera(windowSize);
        clock = new Clock();
    }

    public void update(){
        clock.update();
        sortObjectsByPosition();
        for(GameObject gameObject: gameObjects){
            gameObject.update(this);
        }
        uiContainers.forEach(UIContainer -> UIContainer.update(this));
        camera.update(this);
    }

    public void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return camera;
    }

    public Clock getClock() {
        return clock;
    }

    public Position getRandomPosition(){
        return gameMap.getRandomPosition();
    }

    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return gameObjects.stream()
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());
    }

    public List<UIContainer> getUiContainers() {
        return uiContainers;
    }

    public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz){
        return gameObjects.stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }
}
