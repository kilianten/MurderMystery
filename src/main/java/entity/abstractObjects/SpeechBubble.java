package entity.abstractObjects;

import core.CollisionBox;
import core.Position;
import entity.GameObject;
import graphics.ImageUtils;
import state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpeechBubble extends GameObject {

    public String speech;

    public SpeechBubble(String speech, Position position){
        this.speech = speech;
        renderOrder = 7;
        this.position = position;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return null;
    }

    @Override
    public void update(State state) {

    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BLEND);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawRoundRect(
                0,
                0,
                size.getWidth(),
                size.getHeight(),
                10,
                10);

        graphics.dispose();
        return image;
    }
}
