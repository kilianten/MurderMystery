package map;

import graphics.SpriteLibrary;

import java.awt.*;

public class Tile {

    public Image sprite;

    public Tile(SpriteLibrary spriteLibrary){
        this.sprite = spriteLibrary.getTile("grass");
    }

    public Image getSprite(){
        return sprite;
    }

}
