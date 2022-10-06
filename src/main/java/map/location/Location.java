package map.location;

import entity.GameObject;
import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class Location {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;

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
}
