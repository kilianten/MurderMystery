package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.scenery.Scenery;
import state.State;
import graphics.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionCircle  extends GameObject {

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        this(1);
    }

    public SelectionCircle(Scenery scenery) {
        this.color = new Color(255, 255, 255, 150);
        this.size = new Size(scenery.getSprite().getWidth(null), (int) scenery.getSprite().getHeight(null) / 6);
        renderOffset = scenery.getSelectionCircleOffset();
        collisionBoxOffset = renderOffset;
        renderOrder = 4;
        initialiseSprite();
    }

    public SelectionCircle(double scale) {
        this.color = new Color(255, 255, 255, 150);
        this.size = new Size((int) (32 * scale), (int) (10 * scale));
        renderOffset = new Position(size.getWidth() / 2, size.getHeight() / 2);
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

    public void resize(Size size, Position renderOffet){
        this.size = new Size(size.getWidth(), size.getHeight());
        this.renderOffset = renderOffet;
        initialiseSprite();
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
        return sprite;
    }
}
