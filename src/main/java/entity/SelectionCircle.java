package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;
import graphics.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionCircle  extends GameObject {

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        this.color = new Color(255, 255, 255, 150);
        this.size = new Size(32, 10);
        renderOffset = new Position(size.getWidth() / 2, size.getHeight());
        initialiseSprite();
        renderOrder = 4;
    }

    private void initialiseSprite() {
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BLEND);
        Graphics2D graphics = sprite.createGraphics();

        graphics.setColor(color);
        graphics.fillOval(0, 0, size.getWidth(), size.getHeight());
        graphics.dispose();
    }

    @Override
    public CollisionBox getCollisionBox() {
        return CollisionBox.of(
                getPosition(), getSize()
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
