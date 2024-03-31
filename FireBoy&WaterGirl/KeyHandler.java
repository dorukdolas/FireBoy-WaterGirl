

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, leftPressed, rightPressed;
    public Color color = Color.red;
    public boolean blue , red, magenta;

    public boolean shooting;
    public boolean left, right = true;
    public boolean pause = false;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W && !upPressed && !pause) {
            upPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A && !pause) {
            leftPressed = true; right = false; left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D && !pause) {
            rightPressed = true; right = true; left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_1 && !pause) {
            color = Color.blue;
            blue = true; red = false; magenta = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_2 && !pause) {
            color = Color.red;
            blue = false; red = true; magenta = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_3 && !pause) {
            color = Color.magenta;
            blue = false; red = false; magenta = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !pause) {
            shooting = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_P && !pause) {
            //System.out.println("paused " + pause);
            pause = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_P && pause) {
            //System.out.println("resume " + pause);
            pause = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shooting = false;
        }
    }
}




