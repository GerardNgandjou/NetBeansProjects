package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final int FPS = 60;
    Thread gameThread;
    PlayManger pm;

    public GamePanel() {
        //Panel setting
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        // Implement keyListener
        this.addKeyListener(new Mouvment());
        this.setFocusable(true);

        pm = new PlayManger();
    }

    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void Update() {
        if (Mouvment.pausePressed == false) {
            pm.Update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        pm.draw(g2d);
    }

    /**
     *
     */
    @Override
    public void run() {
        //game loop
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                Update();
                repaint();
                delta--;
            }
        }

    }

}
