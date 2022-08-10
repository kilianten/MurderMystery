package graphics;

import ai.state.Stand;
import entity.ColourHandler;
import game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {

    public static final int DEFAULT_HAIR_COLOUR = 0xFF6E382B;
    public static final int DEFAULT_SHIRT_COLOUR = 0xFFB33831;
    public static final int DEFAULT_SHIRT_DARK_COLOUR = 0xFF6E2727;
    public static final int DEFAULT_SHIRT_LIGHT_COLOUR = 0xFFEA4F36;
    public static final int DEFAULT_LEG_COLOUR = 0xFF4D65B4;
    public static final int DEFAULT_SHOE_COLOUR = 0xFF625565;
    public static final int DEFAULT_LEG_DARK_COLOUR = 0xFF484A77;
    public static final int DEFAULT_SHOE_SHADOW = 0xFF3E3546;
    public static final int DEFAULT_EXTRA_COLOUR = 0xFFff009c;

    private Map<String, SpriteSet> units;
    private Map<String, Image> sceneryObjects;
    private Map<String, Image> tiles;

    public SpriteLibrary(){
        units = new HashMap<>();
        tiles = new HashMap<>();
        sceneryObjects = new HashMap<>();
        loadSpritesFromDisk();
    }

    private void loadSpritesFromDisk() {
        loadUnits("/sprites/units");
        loadImages("/sprites/tiles", tiles);
        loadImages("/sprites/scenery", sceneryObjects);
    }

    private void loadImages(String path, Map<String, Image> images) {
        String[] imagesInFolder = getImagesInFolder(path);

        for(String fileName: imagesInFolder){
            images.put(
                    fileName.substring(0, fileName.length() - 4),
                    ImageUtils.loadImage(path + "/" + fileName)
            );
        }
    }

    private void loadUnits(String path){
        String[] folderNames = getFolderNames(path);
        for(String folder: folderNames){
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = path + "/" + folder;
            String[] sheetsInFolder = getImagesInFolder(pathToFolder);

            for(String sheetName: sheetsInFolder){
                spriteSet.addSheet(
                        sheetName.substring(0, sheetName.length() - 4),
                        ImageUtils.loadImage(pathToFolder + "/" + sheetName)
                );
            }
            units.put(folder, spriteSet);
        }
    }

    private String[] getImagesInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

    private String[] getFolderNames(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }


    public SpriteSet getUnit(String name, ColourHandler colourHandler) {

        if(colourHandler != null){
            return convertColours(units.get(name), colourHandler);
        }
        return units.get(name);
    }

    public Image getTile(String name){
        return tiles.get(name);
    }

    public SpriteSet convertColours(SpriteSet spriteSet, ColourHandler colourHandler){
        SpriteSet convertedSpriteSet = new SpriteSet();
        Map<String, Image> originalAnimationSheets = spriteSet.getAnimationSheets();

        originalAnimationSheets.forEach((key, value) -> {
            BufferedImage img = (BufferedImage) value;
            BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), 2);

            for(int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    int currentPixel = img.getRGB(i, j);
                    if (currentPixel == DEFAULT_SHIRT_COLOUR){
                        newImage.setRGB(i, j, colourHandler.shirtColour);
                    } else if(currentPixel == DEFAULT_HAIR_COLOUR){
                        newImage.setRGB(i, j, colourHandler.hairColour);
                    } else if(currentPixel == DEFAULT_LEG_COLOUR){
                        newImage.setRGB(i, j, colourHandler.legColor);
                    } else if(currentPixel == DEFAULT_LEG_DARK_COLOUR){
                        newImage.setRGB(i, j, colourHandler.legColorDark);
                    } else if(currentPixel == DEFAULT_SHIRT_DARK_COLOUR){
                        newImage.setRGB(i, j, colourHandler.shirtColourDark);
                    } else if(currentPixel == DEFAULT_SHIRT_LIGHT_COLOUR){
                        newImage.setRGB(i, j, colourHandler.shirtColourLight);
                    } else if(currentPixel == DEFAULT_SHOE_COLOUR){
                        newImage.setRGB(i, j, colourHandler.shoeColour);
                    } else if(currentPixel == DEFAULT_EXTRA_COLOUR){
                        newImage.setRGB(i, j, colourHandler.primaryColour);
                    } else if(currentPixel == DEFAULT_SHOE_SHADOW){
                        newImage.setRGB(i, j, colourHandler.shoeShadow);
                    }else {
                        newImage.setRGB(i, j, currentPixel);
                    }
                }
            }
            convertedSpriteSet.addSheet(key, newImage);
        });
        return convertedSpriteSet;
    }

    public Map<String, Image> getAllTiles() {
        return tiles;
    }

    public Image getSceneryImage(String name) {
        return sceneryObjects.get(name);
    }

}
