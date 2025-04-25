package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mouvment implements KeyListener {

    public static boolean upPressed = false;
    public static boolean downPressed = false;
    public static boolean leftPressed = false;
    public static boolean rightPressed = false;
    public static boolean pausePressed;

    /**
     * @param keyEvent
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    /**
     * @param keyEvent
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            if (pausePressed) {
                pausePressed = false;
            }
            else {
                pausePressed = true;
            }
        }
    }

    /**
     * @param keyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {}
}
