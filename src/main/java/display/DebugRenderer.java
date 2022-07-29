package display;

import core.CollisionBox;
import entity.NPC;
import state.State;
import ui.UIText;

import java.awt.*;

public class DebugRenderer {
    public void render(State state, Graphics graphics){
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .map(gameObject -> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));
        drawNames(state, graphics);
    }

    public void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera){
        graphics.setColor(Color.RED);
        graphics.drawRect(
                (int) collisionBox.getBounds().getX() - camera.getPosition().getIntX(),
                (int) collisionBox.getBounds().getY() - camera.getPosition().getIntY(),
                (int) collisionBox.getBounds().getWidth(),
                (int) collisionBox.getBounds().getHeight());
    }

    private void drawNames(State state, Graphics graphics){
        Camera camera = state.getCamera();
        state.getGameObjectsOfClass(NPC.class).stream()
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
