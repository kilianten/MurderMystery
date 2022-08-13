package display;

import entity.human.NPC.NPC;
import state.State;
import ui.UIText;

import java.awt.*;

public class DebugRenderer {
    public void render(State state, Graphics graphics){
        drawNames(state, graphics);
    }

    private void drawNames(State state, Graphics graphics){

        Camera camera = state.getCamera();
        state.getGameObjectsOfClass(NPC.class).stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .forEach(npc ->
                {   UIText nameText = new UIText(npc.getFirstName(), 30, 25, false);
                    nameText.update(state);
                    graphics.drawImage(
                            nameText.getSprite(),
                            npc.getPosition().getIntX() - camera.getPosition().getIntX(),
                            npc.getPosition().getIntY() - camera.getPosition().getIntY(),
                            null);
                });

    }
}
