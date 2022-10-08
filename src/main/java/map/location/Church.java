package map.location;

import core.Position;
import core.Size;
import entity.scenery.building.Door;
import graphics.SpriteLibrary;
import map.GameMap;
import state.State;

public class Church extends Location {

    public Church(State state, SpriteLibrary spriteLibrary){
        super();
        gameMap = new GameMap(new Size(10, 10), spriteLibrary, "woodFloor");
        entrance = new Position(0, 0);
        state.spawn("church", new Door(spriteLibrary, "churchDoor"));
    }

}
