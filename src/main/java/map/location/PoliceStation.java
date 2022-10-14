package map.location;

import core.Position;
import core.Size;
import entity.scenery.Scenery;
import entity.scenery.building.Door;
import map.GameMap;
import map.Tile;
import state.State;

public class PoliceStation extends Location {

    CellArea[] cells;

    public PoliceStation(State state){
        super(state, "policeStation");
        gameMap = new GameMap(new Size(20, 10), state.getSpriteLibrary(), "tile");
        entrancePosition = new Position(0, 0);
        door = new Door(state.getSpriteLibrary(), "policeStationDoor", name);
        gameObjects.add(door);
        setBrickTiles(state);
        cells = new CellArea[2];
        createObjects(state);
    }

    @Override
    public void createObjects(State state){
        createCell(state, new Position(192, 80));
        createCell(state, new Position(384, 80));
        gameObjects.forEach(gameObject -> gameObject.setLocation(name));
    }

    public void createCell(State state, Position position){
        Scenery scenery = new Scenery(
                "cellBars",
                state.getSpriteLibrary(),
                position
        );
        scenery.alterCollisionBoxSize(new Size(0, -10));
        gameObjects.add(scenery);
        for(int i = 0; i < cells.length; i++){
            if(cells[i] == null){
                Position spawnPosition = Position.copyOf(position);
                spawnPosition.add(new Position(96, 0));
                cells[i] = new CellArea(spawnPosition);
            }
        }
    }

    public void setBrickTiles(State state){
        gameMap.setTile(1, 0, new Tile(state.getSpriteLibrary(), "brickWall"));
        gameMap.setTile(1, 1, new Tile(state.getSpriteLibrary(), "brickWall"));
        gameMap.setTile(4, 0, new Tile(state.getSpriteLibrary(), "brickWall"));
        gameMap.setTile(4, 1, new Tile(state.getSpriteLibrary(), "brickWall"));
        gameMap.setTile(7, 0, new Tile(state.getSpriteLibrary(), "brickWall"));
        gameMap.setTile(7, 1, new Tile(state.getSpriteLibrary(), "brickWall"));
    }

    public class CellArea{

        private boolean isOccupied;
        private Position spawnPosition;

        public CellArea(Position spawnPosition){
            this.spawnPosition = spawnPosition;
        }
    }


}
