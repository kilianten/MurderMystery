package display;

import core.CollisionBox;
import core.Position;
import entity.GameObject;
import game.Game;
import state.State;
import map.GameMap;

import java.awt.*;

public class Renderer {

    public void render(State state, Graphics graphics){
        renderMap(state, graphics);
        Camera camera = state.getCamera();
        renderGameObjects(state, graphics, camera);
        renderUI(state, graphics);
    }

    private void renderUI(State state, Graphics graphics) {
        state.getMouseHandler().getPrimaryButtonUI().ifPresent(uiImage -> graphics.drawImage(
                uiImage.getSprite(),
                uiImage.getAbsolutePosition().getIntX(),
                uiImage.getAbsolutePosition().getIntY(),
                null
        ));
        state.getUiContainers().forEach(uiContainer -> graphics.drawImage(
                uiContainer.getSprite(),
                uiContainer.getRelativePosition().getIntX(),
                uiContainer.getRelativePosition().getIntY(),
                null
        ));
        state.UIElements.forEach(uiElement -> graphics.drawImage(
                uiElement.getSprite(),
                uiElement.getRelativePosition().getIntX(),
                uiElement.getRelativePosition().getIntY(),
                null
        ));
    }

    private void renderGameObjects(State state, Graphics graphics, Camera camera){
        state.getGameObjects().stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .forEach(gameObject -> {
                    renderGameObject(graphics, camera, gameObject);
                    if(state.getSettings().getRenderSettings().getCollisionBox().getValue()){
                        drawCollisionBox(gameObject.getCollisionBox(), graphics, camera);
                        drawRenderLines(gameObject, graphics, camera);
                    }
                });
    }

    private void renderGameObject(Graphics graphics, Camera camera, entity.GameObject gameObject) {
        gameObject.getAttachments().forEach(attachment -> renderGameObject(graphics, camera, attachment));
        graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getRenderPosition(camera).getIntX(),
                gameObject.getRenderPosition(camera).getIntY(),
                null
        );
    }

    private void renderMap(State state, Graphics graphics) {
        GameMap map = state.getGameMap();
        Camera camera = state.getCamera();

        Position start = map.getViewableStartingGridPosition(camera);
        Position end = map.getViewableEndingGridPosition(camera);

        for(int x = start.getIntX(); x < end.getIntX(); x++){
            for(int y = start.getIntY(); y < end.getIntY(); y++){
                graphics.drawImage(map.getTiles()[x][y].getSprite(),
                        x * Game.SPRITE_SIZE - camera.getPosition().getIntX(),
                        y * Game.SPRITE_SIZE - camera.getPosition().getIntY(),
                        null
                        );
                if(state.getSettings().getRenderSettings().getShouldRenderGrid().getValue()){
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(
                            x * Game.SPRITE_SIZE - camera.getPosition().getIntX(),
                            y * Game.SPRITE_SIZE - camera.getPosition().getIntY(),
                            Game.SPRITE_SIZE,
                            Game.SPRITE_SIZE);
                }
            }
        }
    }

    public void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera){
        graphics.setColor(Color.RED);
        graphics.drawRect(
                (int) collisionBox.getBounds().getX() - camera.getPosition().getIntX(),
                (int) collisionBox.getBounds().getY() - camera.getPosition().getIntY(),
                (int) collisionBox.getBounds().getWidth(),
                (int) collisionBox.getBounds().getHeight());
    }

    private void drawRenderLines(GameObject object, Graphics graphics, Camera camera) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(
                object.getPosition().getIntX() - (object.getSize().getWidth() / 2) - camera.getPosition().getIntX(),
                object.getRenderLevel() - camera.getPosition().getIntY(),
                object.getSize().getWidth(),
                1);
    }
}
