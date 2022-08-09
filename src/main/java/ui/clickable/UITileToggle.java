package ui.clickable;

import graphics.ImageUtils;
import input.mouse.action.TilePlacer;
import map.Tile;
import state.State;
import ui.UIImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UITileToggle extends UIClickable {

    private UIImage image;
    private BufferedImage activeSprite;
    private TilePlacer tilePlacer;
    private boolean active;

    public UITileToggle(Tile tile) {
        image = new UIImage(tile.getSprite());
        tilePlacer = new TilePlacer(tile);
        size = image.getSize();
        generateActiveSprite();
    }

    @Override
    public void update(State state){
        super.update(state);
        active = state.getMouseHandler().getPrimaryButtonAction().equals(tilePlacer);
    }

    private void generateActiveSprite() {
        activeSprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = activeSprite.createGraphics();

        graphics.drawImage(image.getSprite(), 0, 0, null);
        graphics.setColor(new Color(255, 255, 255, 75));

        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawRect(1, 1, size.getWidth() - 2, size.getHeight() - 2);
        graphics.dispose();
    }

    @Override
    public void onClick(State state) {
        state.getMouseHandler().setPrimaryButtonAction(tilePlacer);
    }

    @Override
    public void onDrag(State state) {

    }

    @Override
    public Image getSprite() {
        return active ? activeSprite : image.getSprite();
    }

    @Override
    protected void onFocus(State state) {

    }
}
