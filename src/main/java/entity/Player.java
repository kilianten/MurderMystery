package entity;

import controller.Controller;
import graphics.SpriteLibrary;


public class Player extends MovingEntity {


    public Player(Controller controller, SpriteLibrary spriteLibrary){
        super(controller, spriteLibrary);
    }

    @Override
    protected void handleCollision(GameObject other) {

    }
}
