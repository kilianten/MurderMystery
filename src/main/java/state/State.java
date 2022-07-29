package state;

import audio.AudioPlayer;
import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Clock;
import game.Game;
import game.settings.GameSettings;
import graphics.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIComponent;
import ui.UIContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class State {

    protected GameSettings settings;
    protected AudioPlayer audioPlayer;
    protected GameMap gameMap;
    protected List<GameObject> gameObjects;

    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Clock clock;

    protected Size windowSize;
    private State nextState;

    protected List<UIContainer> uiContainers;
    public List<UIComponent> UIElements;

    protected boolean paused;

    public State(Size windowSize, Input input, GameSettings settings) {
        this.settings = settings;
        this.windowSize = windowSize;
        gameObjects = new ArrayList<>();
        uiContainers = new ArrayList<>();
        UIElements = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        this.input = input;
        audioPlayer = new AudioPlayer(settings.getAudioSettings());
        this.camera = new Camera(windowSize);
        clock = new Clock();
    }

    public void update(Game game){
        audioPlayer.update();
        clock.update();
        sortObjectsByPosition();
        for(GameObject gameObject: gameObjects){
            gameObject.update(this);
        }
        List.copyOf(uiContainers).forEach(UIContainer -> UIContainer.update(this));
        camera.update(this);
        handleMouseInput();
        handleKeyInput();

        if(nextState != null){
            game.enterState(nextState);
        }
    }

    protected void handleMouseInput(){
        if(input.isMouseClicked()){

        }

        input.clearMouseClick();
    }

    public void handleKeyInput(){}

    public void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(gameObject -> gameObject.getPosition().getY()));
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

    public Input getInput() {
        return input;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public GameSettings getSettings() {
        return settings;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public void cleanUp() {
        audioPlayer.clear();
    }

}
