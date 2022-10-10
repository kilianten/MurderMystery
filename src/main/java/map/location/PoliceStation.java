package map.location;

import core.Position;
import core.Size;
import entity.scenery.building.Door;
import graphics.SpriteLibrary;
import map.GameMap;
import state.State;

public class PoliceStation extends Location {

    public PoliceStation(State state, SpriteLibrary spriteLibrary){
        super();
        gameMap = new GameMap(new Size(20, 10), spriteLibrary, "tile");
        entrancePosition = new Position(0, 0);
        door = new Door(spriteLibrary, "policeStationDoor");
        state.spawn("policeStation", door);
    }


}
