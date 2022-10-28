package entity.scenery;

import core.Position;
import core.Size;
import entity.human.AcquiredItemText;
import entity.human.Human;
import graphics.SpriteLibrary;
import state.State;

public class Bin extends InteractableScenery {

    public Bin(){
        interactable = true;
    }
    public Bin(String name,
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
        state.spawn(state.getCurrentLocationName(), new AcquiredItemText("+$10", human.getPosition()));
    }

}
