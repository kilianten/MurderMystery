package map;

import game.Game;
import graphics.SpriteLibrary;
import io.Persistable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Tile implements Persistable {

    public transient Image sprite;
    private String tileName;
    private int tileIndex;

    public Tile() {}

    public Tile(SpriteLibrary spriteLibrary){
        this(spriteLibrary, "grass");
    }

    public Tile(SpriteLibrary spriteLibrary, String tileName){
        this.sprite = spriteLibrary.getTile(tileName);
        this.tileName = tileName;
    }

    public Tile(Image image, int tileIndex, String tileName){
        this.sprite = image;
        this.tileName = tileName;
        this.tileIndex = tileIndex;
    }

    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getSprite(), tile.getTileIndex(), tile.getTileName());
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
        return stringBuilder.toString();
    }

    @Override
    public void applySerialisedData(String serializedData) {
        String[] tokens = serializedData.split(DELIMITER);

        tileName = tokens[1];
        tileIndex = Integer.parseInt(tokens[2]);
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
}
