package state.game.ui;

import core.Size;
import entity.human.NPC.NPC;
import entity.human.NPC.NPCSpeechHandler;
import graphics.ImageUtils;
import ui.*;
import ui.clickable.UIClickableText;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ConversationBox extends VerticalContainer {

    public static final int SPACE_BETWEEN_RECTS = 10;
    public static final int ARC_WIDTH = 30;
    public static final int STROKE_WIDTH = 4;
    private int startIndex = 0;
    private int endIndex = 5;
    private final int MAX_CHARACTER_PER_LINE = 55;
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
        addUIComponent((new UIClickableText("Small Talk", (state) -> setOptions(NPCSpeechHandler.getCategoryOptions("Small Talk", conversant)))));
        addUIComponent(new UIClickableText("Friendly", (state) -> System.out.println("Friendly")));
        addUIComponent(new UIClickableText("Gossip", (state) -> System.out.println("Gossip")));
        addUIComponent(new UIClickableText("Interrogation", (state) -> setOptions(NPCSpeechHandler.getCategoryOptions("Interrogation", conversant))));
        addUIComponent(new UIClickableText("Help", (state) -> System.out.println("Help")));
    }

    private void setOptions(ArrayList<String> interrogations) {
        clearUIComponents();
        for(String option: interrogations){
            addUIComponent(new UIClickableText(option, (state) -> setResponse(NPCSpeechHandler.getSpeech(option, conversant))));
        }
    }

    private void setResponse(String response) {
        clearUIComponents();

        while(response.length() > 0){
            StringBuilder currentLine = new StringBuilder();
            if(response.length() <= MAX_CHARACTER_PER_LINE){
                addUIComponent(new UIText(response + " ", 32, 16, false, new Spacing(5, 0, 0, 10)));
                response = "";
            } else {
                String nextWord = "";
                do {
                    currentLine.append(nextWord);
                    response = response.substring(nextWord.length());

                    int nextSpace = response.indexOf(" ");
                    if(nextSpace == -1){
                        currentLine.append(response + " ");
                        response = "";
                    } else {
                        nextWord = response.substring(0, nextSpace + 1);
                    }
                } while (currentLine.length() + nextWord.length() < MAX_CHARACTER_PER_LINE);
                addUIComponent(new UIText(currentLine.toString(), 32, 16, false, new Spacing(5, 0, 0, 10)));
            }
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
