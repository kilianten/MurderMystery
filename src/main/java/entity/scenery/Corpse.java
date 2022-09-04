package entity.scenery;

import core.CollisionBox;
import core.Position;
import entity.GameObject;
import entity.human.Human;
import state.State;
import state.game.ui.UIClue;

import java.awt.*;


public class Corpse extends GameObject {

    private Image sprite;
    private UIClue clue;

    public Corpse(Position position, Image sprite) {
        this.position = position;
        position.subtract(new Position(-16, -16));
        this.sprite = sprite;
        renderOrder = 1;
        interactable = true;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return null;
    }

    @Override
    public void update(State state) {
    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    public void setClue(UIClue clue) {
        this.clue = clue;
    }

    @Override
    public void interact(State state, Human human){
        state.addUIComponent(clue);
    }

}
