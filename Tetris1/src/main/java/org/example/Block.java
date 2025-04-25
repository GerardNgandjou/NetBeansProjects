package org.example;

import java.awt.*;

public class Block extends Rectangle {

    public int x;
    public int y;
    public static final int SIZE = 30; //30x30 par block
    public Color co;

    public Block(Color co) {
        this.co = co;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(co);
        g2.fillRect(x, y, SIZE, SIZE);
    }

}
