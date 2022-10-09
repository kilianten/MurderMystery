package map.location;

import core.Position;
import entity.GameObject;
import entity.scenery.building.Door;
import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class Location {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected Position entrancePosition;
    protected Door door;

    public Location(){
        gameObjects = new ArrayList<>();
    }

    public Location(GameMap gameMap){
        this.gameMap = gameMap;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }


    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setOutsidePosition(Position position) {
        door.setOutsidePosition(position);
    }
}
