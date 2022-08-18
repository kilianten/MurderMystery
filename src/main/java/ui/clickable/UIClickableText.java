package ui.clickable;

import core.Size;
import graphics.ImageUtils;
import state.State;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UIClickableText extends UIClickable {

    private String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color colour;
    private ClickAction clickAction;
    private int dropShadowOffset;
    private Color shadowColour;
    private Font font;
    int displaySize;


    public UIClickableText(String text, ClickAction clickAction) {
        this(text, 40, 25, clickAction);
    }

    public UIClickableText(String text, int fontSize, int displaySize, ClickAction clickAction) {
        this.text = text;
        this.fontSize = fontSize;
        this.fontStyle = Font.PLAIN;
        this.fontFamily = "chai";
        this.colour = Color.WHITE;
        this.dropShadowOffset = 2;
        this.shadowColour = new Color(29, 25, 78);
        this.displaySize = displaySize;
        this.clickAction = clickAction;
        createFont();
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALHPA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(font);

        graphics.setColor(colour);
        graphics.drawString(text, padding.getLeft(), displaySize + padding.getTop());

        graphics.dispose();
        return image;
    }

    private void calculateSize() {
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);

        int width = fontMetrics.stringWidth(text) + padding.getHorizontal();

        int height = displaySize + padding.getVertical();

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

    @Override
    public void update(State state) {
        super.update(state);
        calculateSize();

        colour = Color.WHITE;

        if(hasFocus) {
            colour = new Color(80, 180, 50);
        }

        if(isPressed) {
            colour = Color.LIGHT_GRAY;
        }
    }

    @Override
    protected void onFocus(State state) {
        state.getAudioPlayer().playSound("buttonSound.wav");
    }

    @Override
    public void onDrag(State state) {

    }

    @Override
    public void onRelease(State state) {

    }

    @Override
    public void onClick(State state) {
        if(hasFocus){
            clickAction.execute(state);
        }
    }
}
