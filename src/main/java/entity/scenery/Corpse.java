package entity.scenery;

import core.CollisionBox;
import core.Position;
import entity.GameObject;
import entity.MovingEntity;
import entity.human.Human;
import entity.human.NPC.NPC;
import state.State;
import state.game.GameState;
import state.game.ui.UIClue;

import java.awt.*;


public class Corpse extends GameObject {

    private Image sprite;
    private UIClue clue;
    private String name;

    public Corpse(NPC entity) {
        position = entity.getPosition();
        sprite = entity.getSprite("corpse");
        this.renderOffset = new Position(size.getWidth() / 4, size.getHeight() - 12);
        renderOrder = 1;
        interactable = true;
        this.name = entity.getFirstName();
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
        ((GameState) state).setPaused(true);
    }

    public String getName() {
        return name;
    }
}
