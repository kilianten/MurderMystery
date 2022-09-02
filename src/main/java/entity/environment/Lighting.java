package entity.environment;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.GameObject;
import graphics.ImageUtils;
import state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting extends GameObject {

    private BufferedImage sprite;
    private int lightWidth = 500;


    public Lighting(Size windowSize, State state){
        size = windowSize;
        renderOrder = 9;
        drawSprite(windowSize, state);
    }

    private void drawSprite(Size windowSize, State state) {
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BLEND);
        Graphics2D graphics = sprite.createGraphics();

        Color color[] = new Color[12];
        float fraction[] = new float[12];

        color[0] = new Color(0,0,0,0.1f);
        color[1] = new Color(0,0,0,0.42f);
        color[2] = new Color(0,0,0,0.52f);
        color[3] = new Color(0,0,0,0.61f);
        color[4] = new Color(0,0,0,0.69f);
        color[5] = new Color(0,0,0,0.76f);
        color[6] = new Color(0,0,0,0.82f);
        color[7] = new Color(0,0,0,0.87f);
        color[8] = new Color(0,0,0,0.91f);
        color[9] = new Color(0,0,0,0.94f);
        color[10] = new Color(0,0,0,0.96f);
        color[11] = new Color(0,0,0,0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;

        RadialGradientPaint gPaint = new RadialGradientPaint(
                state.getCamera().getPosition().getIntX() + windowSize.getWidth() / 2,
                state.getCamera().getPosition().getIntY() + windowSize.getHeight() / 2,
                (lightWidth/2),
                fraction,
                color);

        graphics.setPaint(gPaint);

        graphics.fillRect(0, 0, windowSize.getWidth(), windowSize.getHeight());

        graphics.dispose();
    }

    @Override
    public CollisionBox getCollisionBox() {
        return null;
    }

    @Override
    public void update(State state) {
        position = Position.copyOf(state.getCamera().getPosition());
    }

    @Override
    public Image getSprite() {
        return sprite;
    }
}
