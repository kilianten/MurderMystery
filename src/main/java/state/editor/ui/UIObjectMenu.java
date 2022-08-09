package state.editor.ui;

import core.Size;
import graphics.SpriteLibrary;
import map.Tile;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.UIComponent;
import ui.VerticalContainer;
import ui.clickable.UIButton;
import ui.clickable.UITileToggle;

import java.awt.*;

public class UIObjectMenu extends HorizontalContainer {

    HorizontalContainer tileContainer;
    HorizontalContainer buildingContainer;
    HorizontalContainer currentContainer;

    public UIObjectMenu(Size windowSize, SpriteLibrary spriteLibrary) {
        super(windowSize);
        setBackgroundColor(Color.DARK_GRAY);
        setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));

        VerticalContainer ObjectTypeMenu = new VerticalContainer(windowSize);
        ObjectTypeMenu.addUIComponent(new UIButton("Tiles", state -> {setCurrentContainer(tileContainer);}, new Size(150, 30)));
        ObjectTypeMenu.addUIComponent(new UIButton("Buildings", state -> {setCurrentContainer(buildingContainer);}, new Size(150, 30)));
        addUIComponent(ObjectTypeMenu);


        buildingContainer = new HorizontalContainer(windowSize);
        createTileContainer(spriteLibrary);
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
        for(String tile: spriteLibrary.getAllTiles().keySet()){
            tileContainer.addUIComponent(new UITileToggle(new Tile(spriteLibrary, tile)));
        }
    }
}
