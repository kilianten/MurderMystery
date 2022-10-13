package map.location;

import core.Position;
import core.Size;
import entity.scenery.Scenery;
import entity.scenery.building.Door;
import map.GameMap;
import state.State;

public class PoliceStation extends Location {

    public PoliceStation(State state){
        super(state, "policeStation");
        gameMap = new GameMap(new Size(20, 10), state.getSpriteLibrary(), "tile");
        entrancePosition = new Position(0, 0);
        door = new Door(state.getSpriteLibrary(), "policeStationDoor", name);
        gameObjects.add(door);
    }

    @Override
    public void createObjects(State state){
        gameObjects.add(new Scenery(
                "cellBars",
                state.getSpriteLibrary(),
                new Position(0, 0)
        ));
        gameObjects.forEach(gameObject -> gameObject.setLocation(name));
    }


}
