package state.game.ui;

import core.Size;
import graphics.ImageUtils;
import ui.*;
import ui.clickable.UIClickableText;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConversationBox extends VerticalContainer {

    public static final int SPACE_BETWEEN_RECTS = 10;
    public static final int ARC_WIDTH = 30;
    public static final int STROKE_WIDTH = 4;

    public ConversationBox(Size windowSize) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.START));
        setFixedSize(new Size(600, 200));
        addUIComponent((new UIClickableText("Interrogation", (state) -> System.out.println("Interrogate"))));
        addUIComponent(new UIClickableText("Friendly", (state) -> System.out.println("Interrogate")));
        addUIComponent(new UIClickableText("Gossip", (state) -> System.out.println("Interrogate")));
        addUIComponent(new UIClickableText("Question", (state) -> System.out.println("Interrogate")));
        addUIComponent(new UIClickableText("Help", (state) -> System.out.println("Interrogate")));

        setPadding();
    }

    @Override
    public Image getSprite() {
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

        for(UIComponent uiComponent : children) {
            graphics.drawImage(
                    uiComponent.getSprite(),
                    uiComponent.getRelativePosition().getIntX(),
                    uiComponent.getRelativePosition().getIntY(),
                    null
            );
        }

        graphics.dispose();
        return image;
    }

    private void setPadding(){
        for(UIComponent uiText: children){
            uiText.setPadding(new Spacing(4, 0, 0, 10));
        }
    }


}
