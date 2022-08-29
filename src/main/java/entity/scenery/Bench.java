package entity.scenery;

import core.Direction;
import core.Position;
import core.Size;
import entity.human.Human;
import entity.human.Player;
import entity.human.action.Sit;
import graphics.SpriteLibrary;
import state.State;

import java.util.Arrays;
import java.util.List;

public class Bench extends InteractableScenery {

    public boolean inUse;

    public Bench(){
        interactable = true;
        inUse = false;
    }
    public Bench(String name,
                               Size size,
                               Position renderOffset,
                               Size collisionBoxSize,
                               Position collisionBoxOffset,
                               boolean walkable,
                               SpriteLibrary spriteLibrary,
                               int renderLevelOffset,
                               Position selectionCircleOffset){
        super(name, size, renderOffset, collisionBoxSize, collisionBoxOffset, walkable, spriteLibrary, renderLevelOffset, selectionCircleOffset);
    }

    @Override
    public void interact(State state, Human human){
        if(!inUse){
            human.setDirection(Direction.S);
            human.perform(new Sit(human, this));
            human.setPosition(new Position(position.getX(), position.getY() + 20));
            inUse = true;
        }
    }

    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }

    public List<String> getAssociatedActions() {
        return Arrays.asList("sit");
    }

}
