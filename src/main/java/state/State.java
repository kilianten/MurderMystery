package state;

import audio.AudioPlayer;
import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import entity.environment.Lighting;
import entity.scenery.Scenery;
import game.Clock;
import game.Game;
import game.settings.GameSettings;
import graphics.SpriteLibrary;
import input.Input;
import input.mouse.MouseHandler;
import io.MapIO;
import map.GameMap;
import map.location.Location;
import state.game.time.GameTimeManager;
import ui.UIComponent;
import ui.UIContainer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class State {

    protected GameSettings settings;
    protected AudioPlayer audioPlayer;
    protected GameMap gameMap;

    protected Map<String, Location> locations;

    protected List<GameObject> gameObjects;
    protected List<GameObject> readyToSpawn;

    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Clock clock;
    protected MouseHandler mouseHandler;

    protected Size windowSize;
    private State nextState;

    protected List<UIContainer> uiContainers;
    public List<UIComponent> UIElements;
    public GameTimeManager gameTimeManager;
    protected Lighting lighting;

    protected String currentLocation =  "Outside";

    public State(Size windowSize, Input input, GameSettings settings) {
        this.settings = settings;
        this.windowSize = windowSize;
        readyToSpawn = new ArrayList<>();
        uiContainers = new ArrayList<>();
        UIElements = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        this.input = input;
        audioPlayer = new AudioPlayer(settings.getAudioSettings());
        mouseHandler = new MouseHandler();
        this.camera = new Camera(windowSize);
        clock = new Clock();
        gameTimeManager = new GameTimeManager();
        setDefaultSettings();
        locations = new HashMap<>();
        locations.put("Outside", new Location());
        locations.put("church", new Location());
    }

    public void update(Game game){
        audioPlayer.update();
        clock.update();
        sortObjectsByPosition();
        updateGameObjects();
        List.copyOf(uiContainers).forEach(UIContainer -> UIContainer.update(this));
        camera.update(this);
        mouseHandler.update(this);
        handleKeyInput();
        if(lighting != null){
            lighting.update(this);
        }

        spawnReadyObjects();
        despawnObjects();

        if(nextState != null){
            game.enterState(nextState);
        }
    }

    protected void despawnObjects(){
        //allLocations

        Iterator iterator = locations.get("Outside").getGameObjects().iterator();

        while (iterator.hasNext()) {
            GameObject object = (GameObject) iterator.next();
            if (object.shouldDelete()) {
                iterator.remove();
                break;
            }
        }
    }

    private void spawnReadyObjects() {
        locations.get("Outside").getGameObjects().addAll(readyToSpawn);
        readyToSpawn.clear();
    }

    protected void updateGameObjects() {
        for(Location location: locations.values()){
            for(GameObject gameObject: location.getGameObjects()){
                gameObject.update(this);
            }
        }

    }

    public void handleKeyInput(){}

    public void sortObjectsByPosition() {
        getCurrentGameObjects().sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(gameObject -> gameObject.getRenderLevel()));
    }

    public List<GameObject> getCurrentGameObjects() {
        return locations.get(currentLocation).getGameObjects();
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
        return gameMap.getRandomAvailablePosition();
    }

    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return getCurrentGameObjects().stream()
                .filter(other -> other.getCollisionBox() != null)
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());
    }

    public List<UIContainer> getUiContainers() {
        return uiContainers;
    }

    public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz){
        return getAllGameObjects().stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

    public <T extends GameObject> List<T> getGameObjectsOfClassInLocation(Class<T> clazz){
        return getCurrentLocation().getGameObjects().stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

    public <T extends GameObject> Optional<T> getGameObjectOfClass(Class<T> clazz){
        return getAllGameObjects().stream()
                .filter(clazz::isInstance)
                .findFirst()
                .map(gameObject -> (T) gameObject);
    }

    public void spawn(GameObject gameObject) {
        readyToSpawn.add(gameObject);
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

    public abstract void setDefaultSettings();

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public void loadGameMap(String filePath) {
        getCurrentLocation().getGameObjects().clear();
        gameMap = MapIO.load(spriteLibrary, filePath);
        getCurrentLocation().getGameObjects().addAll(gameMap.getSceneryList());
    }

    public void saveGameMap(String filePath) {
        gameMap.setSceneryList(getGameObjectsOfClass(Scenery.class));
        MapIO.save(gameMap, filePath);
    }

    public void despawn(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public GameTimeManager getGameTimeManager() {
        return gameTimeManager;
    }

    public Lighting getLighting() {
        return lighting;
    }

    public SpriteLibrary getSpriteLibrary() {
        return spriteLibrary;
    }

    public Size getWindowSize() {
        return windowSize;
    }

    public void addUIComponent(UIContainer component) {
        uiContainers.add(component);
    }

    public void removeUIComponent(UIContainer component){
        uiContainers.remove(component);
    }

    public List<GameObject> getAllGameObjects(){
        List<GameObject> allGameObjects = new ArrayList<>();
        for(Location location: locations.values()){
            allGameObjects.addAll(location.getGameObjects());
        }
        return allGameObjects;
    }

    public Location getCurrentLocation(){
        return locations.get(currentLocation);
    }

    public Location getLocation(String location){
        return locations.get(location);
    }

}
