package map;

import core.Position;
import core.Size;
import display.Camera;
import entity.Scenery;
import game.Game;
import graphics.SpriteLibrary;
import io.Persistable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMap implements Persistable {

    private static final int SAFETY_SPACE = 2;

    private Tile[][] tiles;
    private List<Scenery> sceneryList;

    public GameMap(){
        sceneryList = new ArrayList<>();
    }

    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];
        sceneryList = new ArrayList<>();
        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary) {
        for(Tile[] row: tiles) {
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return tiles.length * Game.SPRITE_SIZE;
    }

    public int getHeight() {
        return tiles[0].length * Game.SPRITE_SIZE;
    }

    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * Game.SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * Game.SPRITE_SIZE;

        return new Position(x, y);
    }

    public void reloadGraphics(SpriteLibrary spriteLibrary) {
        for(Tile[] row: tiles) {
            for(Tile tile: row) {
                tile.reloadGraphics(spriteLibrary);
            }
        }
        sceneryList.forEach(scenery -> scenery.loadGraphics(spriteLibrary));
    }

    public Position getViewableStartingGridPosition(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / Game.SPRITE_SIZE - SAFETY_SPACE),
                Math.max(0, camera.getPosition().getY() / Game.SPRITE_SIZE - SAFETY_SPACE)
        );
    }

    public Position getViewableEndingGridPosition(Camera camera) {
        return new Position(
                Math.min(tiles.length, camera.getPosition().getX() / Game.SPRITE_SIZE + camera.getSize().getWidth() / Game.SPRITE_SIZE + SAFETY_SPACE),
                Math.min(tiles[0].length, camera.getPosition().getY() / Game.SPRITE_SIZE + camera.getSize().getHeight() / Game.SPRITE_SIZE + SAFETY_SPACE)
        );
    }

    public boolean gridWithinBounds(int gridX, int gridY) {
        return gridX >= 0 && gridX < tiles.length
                && gridY >= 0 && gridY < tiles[0].length;
    }

    public void setTile(int gridX, int gridY, Tile tile) {
        tiles[gridX][gridY] = tile;
    }

    public List<Scenery> getSceneryList() {
        return sceneryList;
    }

    public void setSceneryList(List<Scenery> sceneryList) {
        this.sceneryList = sceneryList;
    }

    @Override
    public String serialise() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(tiles.length);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(tiles[0].length);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(SECTION_DELIMITER);
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[0].length; y++){
                stringBuilder.append(tiles[x][y].serialise());
                stringBuilder.append(LIST_DELIMITER);
            }
            stringBuilder.append(COLUMN_DELIMITER);
        }

        stringBuilder.append(SECTION_DELIMITER);
        sceneryList.forEach(scenery -> {
            stringBuilder.append(scenery.serialise());
            stringBuilder.append(COLUMN_DELIMITER);
        });
        return stringBuilder.toString();
    }

    @Override
    public void applySerialisedData(String serializedData) {
        String[] tokens = serializedData.split(DELIMITER);
        String[] sections = serializedData.split(SECTION_DELIMITER);
        tiles = new Tile[Integer.parseInt(tokens[1])][Integer.parseInt(tokens[2])];

        String tileSection = sections[1];
        String[] columns = tileSection.split(COLUMN_DELIMITER);

        for(int x = 0; x < tiles.length; x++) {
            String[] serializedTiles = columns[x].split(LIST_DELIMITER);
            for(int y = 0; y < tiles[0].length; y++) {
                Tile tile = new Tile();
                tile.applySerialisedData(serializedTiles[y]);

                tiles[x][y] = tile;
            }
        }

        if(sections.length > 2){ //have scenery
            String scenerySection = sections[2];
            String[] serializedSceneries = scenerySection.split(COLUMN_DELIMITER);
            for(String serializedScenery: serializedSceneries){
                Scenery scenery = new Scenery();
                scenery.applySerialisedData(serializedScenery);
                sceneryList.add(scenery);
            }
        }
    }
}
