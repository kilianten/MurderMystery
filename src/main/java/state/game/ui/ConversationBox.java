package state.game.ui;

import core.Size;
import entity.GameObject;
import entity.human.NPC.NPC;
import entity.human.NPC.NPCSpeechHandler;
import graphics.ImageUtils;
import speech.SpeechManager;
import state.State;
import state.game.GameState;
import ui.*;
import ui.clickable.UIClickableText;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConversationBox extends VerticalContainer {

    public static final int SPACE_BETWEEN_RECTS = 10;
    public static final int ARC_WIDTH = 30;
    public static final int STROKE_WIDTH = 4;
    private int startIndex = 0;
    private int endIndex = 5;
    protected NPC conversant;

    public ConversationBox(Size windowSize) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.START));
        setFixedSize(new Size(600, 200));
        setPadding();
        setDefault();
    }

    public void setDefault() {
        clearUIComponents();
        addUIComponent((new UIClickableText("Small Talk", (state) -> resetOptions(NPCSpeechHandler.getCategoryOptions("Small Talk")))));
        addUIComponent(new UIClickableText("Friendly", (state) -> System.out.println("Friendly")));
        addUIComponent(new UIClickableText("Gossip", (state) -> System.out.println("Gossip")));
        addUIComponent(new UIClickableText("Interrogation", (state) -> System.out.println("Question")));
        addUIComponent(new UIClickableText("Help", (state) -> System.out.println("Help")));
    }

    private void resetOptions(String[] interrogations) {
        clearUIComponents();
        for(String option: interrogations){
            addUIComponent((new UIClickableText(option, (state) -> System.out.println(NPCSpeechHandler.getSpeech(option, conversant)))));
        }
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

    public void setConversant(NPC conversant) {
        this.conversant = conversant;
    }
}
