package map;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import entity.scenery.Bench;
import entity.scenery.Bin;
import entity.scenery.LampPost;
import entity.scenery.Scenery;
import entity.scenery.building.Building;
import game.Game;
import graphics.SpriteLibrary;
import io.Persistable;

import java.awt.*;
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

    public GameMap(Size size, SpriteLibrary spriteLibrary, String tileName){
        tiles = new Tile[size.getWidth()][size.getHeight()];
        sceneryList = new ArrayList<>();
        initializeTiles(spriteLibrary, tileName);
    }

    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];
        sceneryList = new ArrayList<>();
        initializeTiles(spriteLibrary, "grass");
    }

    private void initializeTiles(SpriteLibrary spriteLibrary, String tileName) {
        for(Tile[] row: tiles) {
            Arrays.fill(row, new Tile(spriteLibrary, tileName));
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return tiles.length * Game.TILE_SIZE;
    }

    public int getHeight() {
        return tiles[0].length * Game.TILE_SIZE;
    }

    public Position getRandomAvailablePosition() {
        double x = Math.random() * tiles.length * Game.TILE_SIZE;
        double y = Math.random() * tiles[0].length * Game.TILE_SIZE;
        int gridX = (int) (x / Game.TILE_SIZE);
        int gridY = (int) (y / Game.TILE_SIZE);

        if(!tileIsAvailable(gridX, gridY)){
            return getRandomAvailablePosition();
        }
        return new Position(gridX * Game.TILE_SIZE + Game.TILE_SIZE / 2, gridY * Game.TILE_SIZE  + Game.TILE_SIZE / 2);
    }

    private boolean tileHasUnwalkableScenery(int gridX, int gridY) {
        CollisionBox gridCollisionBox = getGridCollisionBox(gridX, gridY);
        return sceneryList.stream()
                .filter(scenery -> !scenery.isWalkable())
                .anyMatch(scenery -> scenery.getCollisionBox().collidesWith(gridCollisionBox));
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
                Math.max(0, camera.getPosition().getX() / Game.TILE_SIZE - SAFETY_SPACE),
                Math.max(0, camera.getPosition().getY() / Game.TILE_SIZE - SAFETY_SPACE)
        );
    }

    public Position getViewableEndingGridPosition(Camera camera) {
        return new Position(
                Math.min(tiles.length, camera.getPosition().getX() / Game.TILE_SIZE + camera.getSize().getWidth() / Game.TILE_SIZE + SAFETY_SPACE),
                Math.min(tiles[0].length, camera.getPosition().getY() / Game.TILE_SIZE + camera.getSize().getHeight() / Game.TILE_SIZE + SAFETY_SPACE)
        );
    }

    public boolean gridWithinBounds(int gridX, int gridY) {
        return gridX >= 0 && gridX < tiles.length
                && gridY >= 0 && gridY < tiles[0].length;
    }

    public void setTile(int gridX, int gridY, Tile tile) {
        tiles[gridX][gridY] = tile;
    }

    public List<CollisionBox> getCollidingUnWalkableTileBoxes(CollisionBox collisionBox){
        int gridX = (int) (collisionBox.getBounds().getX() / Game.TILE_SIZE);
        int gridY = (int) (collisionBox.getBounds().getY() / Game.TILE_SIZE);

        List<CollisionBox> collidingUnwalkableTileBoxes = new ArrayList<>();

        for(int x = gridX - 1; x < gridX + 2; x++){
            for(int y = gridY - 1; y < gridY + 2; y++){
                if(gridWithinBounds(x, y) && !getTile(x, y).isWalkable()){
                    CollisionBox gridCollisionBox = getGridCollisionBox(x, y);
                    if(collisionBox.collidesWith(gridCollisionBox)){
                        collidingUnwalkableTileBoxes.add(gridCollisionBox);
                    }
                }
            }
        }
        return collidingUnwalkableTileBoxes;
    }

    private CollisionBox getGridCollisionBox(int x, int y) {
        return new CollisionBox(new Rectangle(x * Game.TILE_SIZE, y * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE));
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
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
                boolean interactive = Boolean.parseBoolean(serializedScenery.split(DELIMITER)[1]);
                Scenery scenery;
                if(interactive){
                    scenery = loadInteractableScenery(serializedScenery.split(DELIMITER)[2]);
                }
                else if(serializedScenery.split(DELIMITER)[2].equals("lampPost")){
                    scenery = new LampPost();
                }
                else {
                    scenery = new Scenery();
                }
                scenery.applySerialisedData(serializedScenery);
                sceneryList.add(scenery);
            }
        }
    }

    private Scenery loadInteractableScenery(String sceneryName) {
        Scenery scenery;
        switch (sceneryName){
            case "bench":
                scenery = new Bench();
                break;
            case "bin":
                scenery = new Bin();
                break;
            default:
                scenery = new Building();
                break;
        }
        return scenery;
    }

    public boolean tileIsAvailable(int x, int y) {
        return getTile(x, y).isWalkable() && !tileHasUnwalkableScenery(x, y);
    }
}
