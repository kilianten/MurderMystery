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
        collisionBoxOffset = renderOffset;
        renderOrder = 4;
        initialiseSprite();
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
        Position position = getPosition();
        position.subtract(collisionBoxOffset);

        return CollisionBox.of(position, getSize());
    }

    @Override
    public void update(State state) {

    }

    @Override
    public Image getSprite() {
        return parent != null ? sprite : null;
    }
}
