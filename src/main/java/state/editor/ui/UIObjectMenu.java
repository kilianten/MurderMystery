package state.editor.ui;

import core.Position;
import core.Size;
import entity.scenery.*;
import entity.scenery.building.Building;
import game.Game;
import graphics.SpriteLibrary;
import input.mouse.action.SceneryPlacer;
import input.mouse.action.TilePlacer;
import map.Tile;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.VerticalContainer;
import ui.clickable.UIButton;
import ui.clickable.UIToolToggle;

import java.awt.*;

public class UIObjectMenu extends HorizontalContainer {

    HorizontalContainer tileContainer;
    HorizontalContainer buildingContainer;
    HorizontalContainer currentContainer;
    HorizontalContainer sceneryContainer;

    public UIObjectMenu(Size windowSize, SpriteLibrary spriteLibrary) {
        super(windowSize);
        setBackgroundColor(Color.DARK_GRAY);
        setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));

        VerticalContainer ObjectTypeMenu = new VerticalContainer(windowSize);
        ObjectTypeMenu.addUIComponent(new UIButton("Tiles", state -> {setCurrentContainer(tileContainer);}, new Size(150, 30)));
        ObjectTypeMenu.addUIComponent(new UIButton("Buildings", state -> {setCurrentContainer(buildingContainer);}, new Size(150, 30)));
        ObjectTypeMenu.addUIComponent(new UIButton("Scenery", state -> {setCurrentContainer(sceneryContainer);}, new Size(150, 30)));
        addUIComponent(ObjectTypeMenu);

        createTileContainer(spriteLibrary);
        createBuildingContainer(spriteLibrary);
        createSceneryContainer(spriteLibrary);
        currentContainer = tileContainer;
        addUIComponent(tileContainer);
    }

    private void setCurrentContainer(HorizontalContainer container){
        children.remove(currentContainer);
        currentContainer = container;
        addUIComponent(container);
    }

    private void createTileContainer(SpriteLibrary spriteLibrary){
        tileContainer = new HorizontalContainer(windowSize);
        for(String tileName: spriteLibrary.getAllTiles().keySet()){
            Tile tile;
            if(tileName.equals("water")){
                tile = new Tile(spriteLibrary, tileName, false);
            } else {
                tile = new Tile(spriteLibrary, tileName, true);
            }
            tileContainer.addUIComponent(new UIToolToggle(tile.getSprite(), new TilePlacer(tile)));
        }
    }

    private void createBuildingContainer(SpriteLibrary spriteLibrary){
        buildingContainer = new HorizontalContainer(windowSize);
        buildingContainer.addUIComponent(new UIToolToggle(spriteLibrary.getBuildingImage("church").getScaledInstance(Game.SPRITE_SIZE, Game.SPRITE_SIZE, Image.SCALE_SMOOTH),
                new SceneryPlacer(new Building(
                        spriteLibrary,
                        "church"
                ))));
        buildingContainer.addUIComponent(new UIToolToggle(spriteLibrary.getBuildingImage("policeStation").getScaledInstance(Game.SPRITE_SIZE, Game.SPRITE_SIZE, Image.SCALE_SMOOTH),
                new SceneryPlacer(new Building(
                        spriteLibrary,
                        "policeStation"
                ))));
    }

    private void createSceneryContainer(SpriteLibrary spriteLibrary){
        sceneryContainer = new HorizontalContainer(windowSize);
        sceneryContainer.addUIComponent(new UIToolToggle(spriteLibrary.getSceneryImage("pinetree").getScaledInstance(Game.SPRITE_SIZE, Game.SPRITE_SIZE, Image.SCALE_SMOOTH),
                new SceneryPlacer(new Scenery(
                        "pinetree",
                        new Size(192, 192),
                        new Position(96, 142),
                        new Size(32, 32),
                        new Position(16, 16),
                        false,
                        spriteLibrary
                ))));
        sceneryContainer.addUIComponent(new UIToolToggle(spriteLibrary.getSceneryImage("bench").getScaledInstance(Game.SPRITE_SIZE, Game.SPRITE_SIZE, Image.SCALE_SMOOTH),
                new SceneryPlacer(new Bench(
                        "bench",
                        new Size(80, 82),
                        new Position(40, 40),
                        new Size(75, 25),
                        new Position(37, 7),
                        false,
                        spriteLibrary,
                        19,
                        new Position(40, -5)
                ))));
        sceneryContainer.addUIComponent(new UIToolToggle(spriteLibrary.getSceneryImage("bin").getScaledInstance(Game.SPRITE_SIZE, Game.SPRITE_SIZE, Image.SCALE_SMOOTH),
                new SceneryPlacer(new Bin(
                        "bin",
                        new Size(32, 32),
                        new Position(16, 16),
                        new Size(16, 16),
                        new Position(8, 2),
                        false,
                        spriteLibrary,
                        15,
                        new Position(16, -12)
                ))));
        sceneryContainer.addUIComponent(new UIToolToggle(spriteLibrary.getSceneryImage("lampPost").getScaledInstance(Game.SPRITE_SIZE, Game.SPRITE_SIZE, Image.SCALE_SMOOTH),
                new SceneryPlacer(new LampPost(
                        "lampPost",
                        new Size(60, 60),
                        new Position(48, 71),
                        new Size(14, 25),
                        new Position(0, -15),
                        false,
                        spriteLibrary
                ))));
    }
}
