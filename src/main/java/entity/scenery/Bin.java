package entity.scenery;

import core.Direction;
import core.Position;
import core.Size;
import entity.human.Player;
import entity.human.action.Sit;
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
    public void interact(State state, Player player){
        System.out.println(size.getWidth());
        System.out.println("searchbin");
    }

}
