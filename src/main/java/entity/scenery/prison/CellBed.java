package entity.scenery.prison;

import core.Direction;
import core.Position;
import core.Size;
import entity.human.AcquiredItemText;
import entity.human.Human;
import entity.human.action.Sit;
import entity.human.action.Sleep;
import entity.scenery.InteractableScenery;
import graphics.SpriteLibrary;
import state.State;
import state.game.GameState;

import java.util.Arrays;
import java.util.List;

public class CellBed extends InteractableScenery {

    public boolean inUse;

    public CellBed(){
        interactable = true;
    }

    public CellBed(SpriteLibrary spriteLibrary,
               Position position){
        super("CellBed", spriteLibrary, position);
    }

    @Override
    public void interact(State state, Human human){
        if(!inUse){
            human.setDirection(Direction.S);
            human.perform(new Sleep(human, this));
            human.setPosition(new Position(position.getX(), position.getY() + 35));
            inUse = true;
        }
    }

    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }

    public List<String> getAssociatedActions() {
        return Arrays.asList("sleep");
    }

}
