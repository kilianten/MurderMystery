package input;

import core.Position;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    private boolean[] currentlyPressed;
    private boolean[] pressed;

    private Position mousePosition;
    private boolean mouseClicked;
    private boolean mousePressed;

    public Input(){
        pressed = new boolean[256];
        currentlyPressed = new boolean[256];
        mousePosition = new Position(0, 0);
    }

    public boolean[] getCurrentlyPressed() {
        return currentlyPressed;
    }

    public boolean[] getPressed() {
        return pressed;
    }

    public Position getMousePosition() {
        return mousePosition;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public boolean isMousePressed() {
        return mousePressed;
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

    public void clearMouseClick(){
        mouseClicked = false;
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

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = true;
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = new Position(e.getPoint().getX(), e.getPoint().getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = new Position(e.getPoint().getX(), e.getPoint().getY());
    }
}
