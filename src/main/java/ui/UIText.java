package ui;

import core.Size;
import game.state.State;
import graphics.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketOption;

public class UIText extends UIComponent {

    private String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color colour;

    private boolean dropShadow;
    private int dropShadowOffset;
    private Color shadowColour;

    private Font font;

    int displaySize;

    public UIText(String text) {
        this(text, 40, 25);
    }

    public UIText(String text, int fontSize, int displaySize) {
        this.text = text;
        this.fontSize = fontSize;
        this.fontStyle = Font.PLAIN;
        this.fontFamily = "chai";
        this.colour = Color.WHITE;


        this.dropShadow = true;
        this.dropShadowOffset = 2;
        this.shadowColour = new Color(49, 42, 141);
        this.displaySize = displaySize;
        createFont();

    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALHPA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(font);

        if(dropShadow) {
            graphics.setColor(shadowColour);
            graphics.drawString(text, padding.getLeft() + dropShadowOffset, displaySize + padding.getTop() + dropShadowOffset);
        }

        graphics.setColor(colour);
        graphics.drawString(text, padding.getLeft(), displaySize + padding.getTop());

        graphics.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        calculateSize();
    }

    private void calculateSize() {
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);

        int width = fontMetrics.stringWidth(text) + padding.getHorizontal();

        int height = displaySize + padding.getVertical();

        if(dropShadow){
            width += dropShadowOffset;
        }

        size = new Size(width, height);
    }

    private void createFont() {
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("fonts/" + fontFamily + ".otf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont((float)fontSize);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setText(String text) {
        this.text = text;
    }
}
