package ui;

import core.Position;
import core.Size;
import game.state.State;
import graphics.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIContainer extends UIComponent {

    private Color backgroundColor;

    public UIContainer() {
        super();
        backgroundColor = Color.RED;

        calculatePosition();
        calculateSize();
    }

    private void calculateSize(){
        size = new Size(padding.getHorizontal(), padding.getVertical());
    }

    private void calculatePosition(){
        position = new Position(margin.getLeft(), margin.getTop());
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALHPA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
        graphics.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        calculatePosition();
        calculateSize();
    }
}
