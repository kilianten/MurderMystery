package display;

import core.Position;
import core.Size;
import entity.GameObject;
import game.Game;
import game.state.State;
import java.awt.*;
import java.util.Optional;

public class Camera {

    private static final int SAFETY_RENDER_SPACE = 2 * Game.SPRITE_SIZE;

    private Position position;
    private Size windowSize;
    private Optional<GameObject> objectWithFocus;
    private Rectangle viewBounds;

    public Camera(Size windowSize) {
        this.windowSize = windowSize;
        this.position = new Position(0, 0);
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new Rectangle(
                position.getIntX(),
                position.getIntY(),
                windowSize.getWidth() + SAFETY_RENDER_SPACE,
                windowSize.getHeight() + SAFETY_RENDER_SPACE);
    }

    public void focusOn(GameObject object){
        this.objectWithFocus = Optional.of(object);
    }

    public Position getPosition() {
        return position;
    }

    public void update(State state){
        if(objectWithFocus.isPresent()){
            Position objectPosition = objectWithFocus.get().getPosition();
            this.position.setX(objectPosition.getX() - windowSize.getWidth() / 2);
            this.position.setY(objectPosition.getY() - windowSize.getHeight() / 2);
        }
        clampWithinBounds(state);
        calculateViewBounds();
    }

    private void clampWithinBounds(State state) {
        if(position.getX() < 0){
            position.setX(0);
        }
        if(position.getY() < 0){
            position.setY(0);
        }
        if(position.getX() + windowSize.getWidth() > state.getGameMap().getWidth()){
            position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
        }
        if(position.getY() + windowSize.getHeight() > state.getGameMap().getHeight()){
            position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
        }
    }

    public boolean isInView(GameObject gameObject) {
        return viewBounds.intersects(
                gameObject.getPosition().getIntX(),
                gameObject.getPosition().getIntY(),
                gameObject.getSize().getWidth(),
                gameObject.getSize().getHeight()
        );
    }

    public Size getSize() {
        return windowSize;
    }
}