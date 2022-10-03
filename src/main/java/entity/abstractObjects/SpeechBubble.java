package entity.abstractObjects;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.GameObject;
import graphics.ImageUtils;
import state.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SpeechBubble extends GameObject {

    public String speech;
    private static InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("fonts/chai.otf");
    private static Font font;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont((float) 10f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);

        graphics.setColor(new Color(255, 255, 255, 200));
        graphics.setStroke(new BasicStroke(2));
        size = new Size(fontMetrics.stringWidth(speech) * 2, size.getHeight());
        graphics.fillRoundRect(
                0,
                0,
                fontMetrics.stringWidth(speech) * 2,
                size.getHeight(),
                10,
                10);
        graphics.setColor(Color.BLACK);
        graphics.drawString(speech,  10, 10);


        graphics.dispose();
        return image;
    }
}
