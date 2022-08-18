package io;

import graphics.SpriteLibrary;
import map.GameMap;

import java.io.*;
import java.net.URL;

public class MapIO {

    public static void save(GameMap map, String filePath){
        PersistableIO.save(map, filePath);
    }

    public static GameMap load(SpriteLibrary spriteLibrary, String filePath) {
        GameMap gameMap = PersistableIO.load(GameMap.class, filePath);
        gameMap.reloadGraphics(spriteLibrary);
        return gameMap;
    }
}
