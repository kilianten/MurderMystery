package state.game.ui;

import core.Size;
import graphics.ImageUtils;
import state.State;
import ui.UIComponent;
import ui.UIContainer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConversationBox extends UIComponent {

    public static final int SPACE_BETWEEN_RECTS = 10;
    public static final int ARC_WIDTH = 30;
    public static final int STROKE_WIDTH = 5;

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BLEND);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(new Color(0, 0, 0, 150));
        graphics.fillRoundRect(absolutePosition.getIntX(), absolutePosition.getIntY(), size.getWidth(), size.getHeight(), ARC_WIDTH, ARC_WIDTH);

        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(STROKE_WIDTH));
        graphics.drawRoundRect(
                absolutePosition.getIntX() + SPACE_BETWEEN_RECTS/ 2,
                absolutePosition.getIntY() + SPACE_BETWEEN_RECTS/ 2,
                size.getWidth() - SPACE_BETWEEN_RECTS,
                size.getHeight() - SPACE_BETWEEN_RECTS,
                ARC_WIDTH,
                ARC_WIDTH);

        graphics.dispose();
        return image;
    }

    @Override
    public void update(State state) {

    }
}
