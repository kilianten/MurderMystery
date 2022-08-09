package display;

import core.Position;
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
                .forEach(gameObject -> graphics.drawImage(
                        gameObject.getSprite(),
                        gameObject.getRenderPosition(camera).getIntX(),
                        gameObject.getRenderPosition(camera).getIntY(),
                        null
                ));
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
}
