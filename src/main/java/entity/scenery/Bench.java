package entity.scenery;

import core.Direction;
import core.Position;
import core.Size;
import entity.human.Player;
import entity.human.action.Sit;
import graphics.SpriteLibrary;
import state.State;

public class Bench extends InteractableScenery {

    boolean inUse;

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
    public void interact(State state, Player player){
        if(!inUse){
            player.setDirection(Direction.S);
            player.perform(new Sit(player, this));
            player.setPosition(new Position(position.getX(), position.getY() + 20));
            inUse = true;
        }
    }

    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }
}
