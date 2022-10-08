package map;

import game.Game;
import graphics.SpriteLibrary;
import io.Persistable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile implements Persistable {

    public transient Image sprite;
    private String tileName;
    private int tileIndex;
    private boolean walkable;

    public Tile() {
        walkable = true;
    }

    public Tile(SpriteLibrary spriteLibrary, String tileName){
        this(spriteLibrary, tileName, true);
    }

    public Tile(SpriteLibrary spriteLibrary, String tileName, boolean walkable){
        this.sprite = spriteLibrary.getTile(tileName);
        this.tileName = tileName;
        this.walkable = walkable;
    }

    public Tile(Image image, int tileIndex, String tileName, boolean walkable){
        this.sprite = image;
        this.tileName = tileName;
        this.tileIndex = tileIndex;
        this.walkable = walkable;
    }

    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getSprite(), tile.getTileIndex(), tile.getTileName(), tile.isWalkable());
    }

    public boolean isWalkable() {
        return walkable;
    }

    public Image getSprite(){
        return sprite;
    }

    public void reloadGraphics(SpriteLibrary spriteLibrary) {
        sprite = spriteLibrary.getTile(tileName);
        generateSprite();
    }

    private void generateSprite() {
        sprite = ((BufferedImage) sprite).getSubimage(
                (tileIndex / (sprite.getWidth(null) / Game.SPRITE_SIZE)) * Game.SPRITE_SIZE,
                (tileIndex % (sprite.getWidth(null) / Game.SPRITE_SIZE)) * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    @Override
    public String serialise() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(tileName);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(tileIndex);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(walkable);
        return stringBuilder.toString();
    }

    @Override
    public void applySerialisedData(String serializedData) {
        String[] tokens = serializedData.split(DELIMITER);

        tileName = tokens[1];
        tileIndex = Integer.parseInt(tokens[2]);
        walkable = Boolean.parseBoolean(tokens[3]);
    }

    public String getTileName() {
        return tileName;
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    public int getMoveCost() {
        int moveCost = switch (tileName){
            case "pathcenter" -> 10;
            default -> 40;
        };
        return moveCost;
    }
}
