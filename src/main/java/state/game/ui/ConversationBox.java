package state.game.ui;

import core.Size;
import graphics.ImageUtils;
import state.State;
import ui.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConversationBox extends VerticalContainer {

    public static final int SPACE_BETWEEN_RECTS = 10;
    public static final int ARC_WIDTH = 30;
    public static final int STROKE_WIDTH = 4;

    public ConversationBox(Size windowSize) {
        super(windowSize);
        this.size = new Size(600, 200);
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.START));
        addUIComponent(new UIImage(getConversationBox()));
    }

    public Image getConversationBox() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BLEND);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(new Color(0, 0, 0, 201));
        graphics.fillRoundRect(0, 0, size.getWidth(), size.getHeight(), ARC_WIDTH, ARC_WIDTH);

        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(STROKE_WIDTH));
        graphics.drawRoundRect(
                SPACE_BETWEEN_RECTS/ 2,
                SPACE_BETWEEN_RECTS/ 2,
                size.getWidth() - SPACE_BETWEEN_RECTS,
                size.getHeight() - SPACE_BETWEEN_RECTS,
                ARC_WIDTH,
                ARC_WIDTH);


        graphics.dispose();
        return image;
    }

}
