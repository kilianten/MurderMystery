package map;

import graphics.SpriteLibrary;

import java.awt.*;

public class Tile {

    public Image sprite;

    public Tile(SpriteLibrary spriteLibrary){
        this(spriteLibrary, "grass");
    }

    public Tile(SpriteLibrary spriteLibrary, String tileName){
        this.sprite = spriteLibrary.getTile(tileName);
    }

    private Tile(Image sprite){
        this.sprite = sprite;
    }

    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getSprite());
    }

    public Image getSprite(){
        return sprite;
    }

}
