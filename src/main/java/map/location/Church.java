package map.location;

import core.Position;
import core.Size;
import entity.scenery.building.Door;
import graphics.SpriteLibrary;
import map.GameMap;
import state.State;

public class Church extends Location {

    public Church(State state){
        super(state, "church");
        gameMap = new GameMap(new Size(10, 10), state.getSpriteLibrary(), "woodFloor");
        entrancePosition = new Position(gameMap.getWidth() / 2, gameMap.getHeight());
        this.name = "church";
        door = new Door(state.getSpriteLibrary(), "churchDoor", name, entrancePosition);
        state.spawn("church", door);
    }

}
