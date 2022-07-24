package entity;

import core.CollisionBox;
import core.Size;
import game.state.State;
import graphics.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionCircle  extends GameObject {

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        this.color = Color.PINK;
        this.size = new Size(32, 10);
        initialiseSprite();
    }

    private void initialiseSprite() {
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALHPA_BIT_MASKED);
        Graphics2D graphics = sprite.createGraphics();

        graphics.setColor(color);
        graphics.fillOval(0, 0, size.getWidth(), size.getHeight());
        graphics.dispose();
    }

    @Override
    public CollisionBox getCollisionBox() {
        return CollisionBox.of(
                position, size
        );
    }

    @Override
    public void update(State state) {

    }

    @Override
    public Image getSprite() {
        return sprite;
    }
}
