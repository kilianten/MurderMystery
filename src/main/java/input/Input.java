package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean[] currentlyPressed;
    private boolean[] pressed;

    public Input(){
        pressed = new boolean[256];
        currentlyPressed = new boolean[256];
    }

    public boolean isPressed(int keyCode){

        if(!pressed[keyCode] && currentlyPressed[keyCode]){
            pressed[keyCode] = true;
            return true;
        }
        return false;
    }

    public boolean isCurrentlyPressed(int keycode){
        return currentlyPressed[keycode];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = false;
        pressed[e.getKeyCode()] = false;
    }
}
