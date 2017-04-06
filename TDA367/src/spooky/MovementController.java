package spooky; //funkar inte just nu


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static spooky.Direction.*;


public class MovementController implements KeyListener{
    Character player;

    public MovementController(Character player){
        this.player = player;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
       int key = e.getKeyCode();
       switch(key){
           case KeyEvent.VK_LEFT:
               player.moveXPosition(LEFT);
               break;
           case KeyEvent.VK_RIGHT:
               player.moveXPosition((RIGHT));
               break;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
