package entity.human;

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


public class AcquiredItemText extends GameObject {

    public String text;
    private int currentFont;
    private int counter;
    private final int UPDATE_FONT_RATE = 2;
    private final int DESPAWN_AFTER = 100;

    public AcquiredItemText(String text, Position position){
        this.text = text;
        this.position = position;
        position.setY(position.getY() - 60);
        renderOrder = 10;
        renderOffset = new Position(0, 0);
        currentFont = 20;
        size = new Size(text.length() * 20, 50);
    }

    @Override
    public CollisionBox getCollisionBox() {
        return null;
    }

    @Override
    public void update(State state) {
        position.setY(position.getY() - 1);
        if(currentFont < 40){
            if(counter <= UPDATE_FONT_RATE){
                counter++;
            } else {
                currentFont++;
                counter = 0;
            }
        } else {
            if(counter <= DESPAWN_AFTER){
                counter++;
            } else {
                shouldDelete = getLocation();
            }
        }
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BLEND);
        Graphics2D graphics = image.createGraphics();

        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("fonts/chai.otf");
            graphics.setFont(Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont((float) currentFont));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        graphics.setColor(new Color(50, 255, 50));
        graphics.drawString(text,  20, 50);

        graphics.dispose();
        return image;
    }

}
