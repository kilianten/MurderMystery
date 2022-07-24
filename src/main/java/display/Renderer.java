package display;

import core.Position;
import game.Game;
import game.state.State;
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
        state.getUiContainers().forEach(uiContainer -> graphics.drawImage(
                uiContainer.getSprite(),
                uiContainer.getPosition().getIntX(),
                uiContainer.getPosition().getIntY(),
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
            }
        }
    }
}
