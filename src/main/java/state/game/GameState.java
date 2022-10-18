package state.game;

import controller.NPCController;
import controller.PlayerController;
import core.Direction;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.environment.Lighting;
import entity.human.Human;
import entity.human.NPC.*;
import entity.human.Player;
import entity.scenery.building.Building;
import game.settings.GameSettings;
import graphics.SpriteLibrary;
import map.GameMap;
import map.location.Church;
import map.location.PoliceStation;
import state.game.ui.ConversationBoxContainer;
import state.game.ui.UIGameMenu;
import state.game.ui.UIGameTime;
import input.Input;
import state.State;
import story.StoryManager;
import ui.UIContainer;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

public class GameState extends State {

    protected boolean paused;

    private UIGameMenu gameMenu;
    private ConversationBoxContainer conversationBoxContainer;
    private boolean conversating;
    private StoryManager storyManager;

    public GameState(Size windowSize, Input input, GameSettings settings, String gameMap) {
        super(windowSize, input, settings);

        loadGameMap(gameMap);
        initializeUI(windowSize);
        gameMenu = new UIGameMenu(windowSize, input, settings);
        conversationBoxContainer = new ConversationBoxContainer(windowSize);
        locations.get("Outside").setGameMap(this.gameMap);
        lighting = new Lighting(this);
        createLocations();
        initialiseCharacters();
        storyManager = new StoryManager(this);
    }

    private void createLocations() {
        locations.put("church", new Church(this));
        locations.put("policeStation", new PoliceStation(this));
        syncBuildingLocationsPositions();
    }

    private void syncBuildingLocationsPositions() {
        for(Building building:getGameObjectsOfClassInLocation(Building.class, "Outside")){
            Position buildingPosition = Position.copyOf(building.getPosition());
            buildingPosition.add(new Position(0, building.getSprite().getHeight(null)));
            locations.get(building.getName()).setOutsidePosition(buildingPosition);
        }
    }

    protected void updateGameObjects() {
        if(!paused){
            super.updateGameObjects();
            storyManager.update(this);
            gameTimeManager.update(this);
        }
    }

    @Override
    public void handleKeyInput(){
        if(input.isPressed(KeyEvent.VK_ESCAPE)){
            togglePause(!paused);
            if(conversating){
                toggleConversationBox(false);
                conversating = false;
            }
        }
    }

    public void endConversation(){
        togglePause(!paused);
        toggleConversationBox(false);
        conversating = false;
    }

    @Override
    public void setDefaultSettings() { }

    private void initialiseCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        player.setPosition(gameMap.getRandomAvailablePosition());
        getCurrentLocation().getGameObjects().add(player);
        camera.focusOn(player);

        initialiseNPCs(spriteLibrary, player);
    }

    private void initialiseNPCs(SpriteLibrary spriteLibrary, Player player) {
        for(int i = 0;i < 1; i++){
            Karl karl = new Karl(new NPCController(), spriteLibrary);
            Douglas douglas = new Douglas(new NPCController(), spriteLibrary);
            Nolan nolan = new Nolan(new NPCController(), spriteLibrary);
            Eduardo eduardo = new Eduardo(new NPCController(), spriteLibrary);
            Raquel raquel = new Raquel(new NPCController(), spriteLibrary);
            Sheriff sheriff = new Sheriff(new NPCController(), spriteLibrary);
            Vanessa vanessa = new Vanessa(new NPCController(), spriteLibrary);
            Eric eric = new Eric(new NPCController(), spriteLibrary);
            Mary mary = new Mary(new NPCController(), spriteLibrary);
            Tim tim = new Tim(new NPCController(), spriteLibrary);
            Kate kate = new Kate(new NPCController(), spriteLibrary);
            nolan.setPosition(gameMap.getRandomAvailablePosition());
            douglas.setPosition(gameMap.getRandomAvailablePosition());
            karl.setPosition(gameMap.getRandomAvailablePosition());
            eduardo.setPosition(gameMap.getRandomAvailablePosition());
            raquel.setPosition(gameMap.getRandomAvailablePosition());
            sheriff.setPosition(gameMap.getRandomAvailablePosition());
            vanessa.setPosition(gameMap.getRandomAvailablePosition());
            mary.setPosition(gameMap.getRandomAvailablePosition());
            eric.setPosition(gameMap.getRandomAvailablePosition());
            kate.setPosition(gameMap.getRandomAvailablePosition());
            jail(tim, "Beatin the shit out of Karl. No fucking regrets", false);
            getCurrentLocation().getGameObjects().add(raquel);
            getCurrentLocation().getGameObjects().add(karl);
            getCurrentLocation().getGameObjects().add(douglas);
            getCurrentLocation().getGameObjects().add(sheriff);
            //getCurrentLocation().getGameObjects().add(nolan);
            getCurrentLocation().getGameObjects().add(eduardo);
            getCurrentLocation().getGameObjects().add(vanessa);
            getCurrentLocation().getGameObjects().add(mary);
            getCurrentLocation().getGameObjects().add(eric);
            getCurrentLocation().getGameObjects().add(tim);
            getCurrentLocation().getGameObjects().add(kate);
        }
        Nolan nolan = new Nolan(new NPCController(), spriteLibrary);
        Position position = gameMap.getRandomAvailablePosition();
        nolan.setPosition(position);
        getCurrentLocation().getGameObjects().add(nolan);
        player.setPosition(Position.copyOf(nolan.getPosition()));
    }

    private void initializeUI(Size windowSize) {
         uiContainers.add(new UIGameTime(windowSize));
    }

    public void toggleConversationBox(boolean isConversating){
        if(isConversating){
            paused = true;
            conversating = true;
            conversationBoxContainer.reset();
            toggleMenu(true, conversationBoxContainer);
        } else {
            paused = false;
            toggleMenu(false, conversationBoxContainer);
        }
    }

    public void togglePause(boolean shouldPause){
        if(shouldPause){
            paused = true;
            toggleMenu(true, gameMenu);
        } else {
            paused = false;
            toggleMenu(false, gameMenu);
        }
    }

    private void toggleMenu(boolean shouldShowMenu, UIContainer container) {
        if(shouldShowMenu && !uiContainers.contains(container)){
            uiContainers.add(container);
        } else if (!shouldShowMenu){
            uiContainers.remove(container);
        }
    }

    public ConversationBoxContainer getConversationBox() {
        return conversationBoxContainer;
    }

    @Override
    public void cleanUp(){
        super.cleanUp();
    }

    public void setPaused(boolean paused){
        this.paused = paused;
    };

    public boolean isPaused(){
        return paused;
    }

    public Player getPlayer() {
        return (Player) getAllGameObjects().stream()
                .filter(object -> object instanceof Player)
                .findFirst().get();
    }

    public void changeObjectLocation(GameObject gameObject, String locationName) {
        gameObject.delete(gameObject.getLocation());
        gameObject.setLocation(locationName);
        gameObject.setPosition(Position.copyOf(getLocation(locationName).getEntrancePosition()));
        spawn(locationName, gameObject);
    }

    public void changeObjectLocation(GameObject gameObject, String locationName, Position position) {
        gameObject.delete(gameObject.getLocation());
        gameObject.setLocation(locationName);
        gameObject.setPosition(position);
        spawn(locationName, gameObject);
    }

    public GameMap getGameMapOfObject(GameObject gameObject){
        return locations.get(gameObject.getLocation()).getGameMap();
    }

    public void jail(NPC npc, String reason, boolean playSound){
        PoliceStation.CellArea cell = ((PoliceStation) locations.get("policeStation")).getEmptyCell();
        if(cell != null){
            if(playSound){
                getAudioPlayer().playSound("jail.wav");
            }
            changeObjectLocation(npc, "policeStation", Position.copyOf(cell.getSpawnPosition()));
            npc.setJailed(true);
            npc.setStanding(this);
            npc.setDirection(Direction.S);
            cell.setOccupied(true);
            npc.setJailedReason(reason);
        }

    }

}
