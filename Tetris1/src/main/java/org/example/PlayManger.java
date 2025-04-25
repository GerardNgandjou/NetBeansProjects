package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayManger {

    //Main Play Area
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int top_y;
    public static int right_x;
    public static int bottom_y;

    //Mino
    Mino currentMino;
    final int minoStartX;
    final int minoStartY;
    Mino nextMino;
    final int nextMinoStartX;
    final int nextMinoStartY;
    public static ArrayList<Block> staticBlocks = new ArrayList<>() ;

    //Others
    public static int dropInterval = 60; //mino drops in every 60 frames

    PlayManger() {
        // Main play Area Frame
        left_x =(GamePanel.WIDTH / 2) - (WIDTH / 2);// 1280/2 - 360/2 = 460
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        minoStartX = left_x + (WIDTH / 2) - Block.SIZE;
        minoStartY = top_y + Block.SIZE;

        nextMinoStartX = right_x + 170;
        nextMinoStartY = top_y + 500;

        //Set the starting Mino
        currentMino = pickMino();
        currentMino.setXY(minoStartX, minoStartY);
        nextMino = pickMino();
        nextMino.setXY(nextMinoStartX, nextMinoStartY);
    }

    private Mino pickMino() {
        // Pick a random mino
        Mino mino = null;
        int i = new Random().nextInt(7);

        switch (i) {
            case 0: mino = new MinoL1(); break;
            case 1: mino = new MinoL2(); break;
            case 2: mino = new MinoSquare(); break;
            case 3: mino = new MinoBar(); break;
            case 4: mino = new MinoZ1(); break;
            case 5: mino = new MinoZ2(); break;
            case 6: mino = new MinoT(); break;
        }

        return mino;
    }

    public void Update() {
        // Check if the currentMino is active
        if (currentMino.active == false) {
            //If the currentMino is not active, put it into the staticBlock
            staticBlocks.add(currentMino.b[0]);
            staticBlocks.add(currentMino.b[1]);
            staticBlocks.add(currentMino.b[2]);
            staticBlocks.add(currentMino.b[3]);

            //replace the currentMino with the nextMino
            currentMino = nextMino;
            currentMino.setXY(nextMinoStartX, nextMinoStartY);
            nextMino = pickMino();
            nextMino.setXY(nextMinoStartX, nextMinoStartY);

        }   else {
            currentMino.update();
        }

    }

    public void draw(Graphics2D g2) {

        // Draw play area frame
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);

        //Draw new mino Frame
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Next", x + 60, y + 60);

        // Draw the currentMino
        if (currentMino != null) {
            currentMino.draw(g2);
        }

        // Draw the next mino
        nextMino.draw(g2);

        // Draw the staticBlocks
        for (int i = 0; i < staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(g2);
        }

        // Draw pause
        g2.setFont(g2.getFont().deriveFont((float) (60f)));
        g2.setColor(Color.yellow);
        if (Mouvment.pausePressed) {
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("Pause", x, y);
        }
    }
}
