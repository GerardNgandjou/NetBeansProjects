package org.example;

import java.awt.*;

public class Mino {

    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    int autoDropCounter;
    public int direction = 1; // There are 4 directions (1/2/3/4)
    public boolean leftCollision;
    public boolean rightCollision;
    public boolean bottomCollision;
    public boolean active = true;

    public void create(Color co) {
        b[0] = new Block(co);
        b[1] = new Block(co);
        b[2] = new Block(co);
        b[3] = new Block(co);
        tempB[0] = new Block(co);
        tempB[1] = new Block(co);
        tempB[2] = new Block(co);
        tempB[3] = new Block(co);
    }

    public void setXY(int x, int y) {}

    public void updateXY(int direction) {

        checkRotationCollision();
        if (leftCollision == false && rightCollision == false && bottomCollision == false) {
            this.direction = direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }
    }

    public void getDirection1() {}
    public void getDirection2() {}
    public void getDirection3() {}
    public void getDirection4() {}

    public void checkMovementCollision() {

        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        // Check frame collision

        for (int i = 0; i < b.length; i++) {
            // Left Wall
            if (b[i].x == PlayManger.left_x) {
                leftCollision = true;
            }

            // Right Wall
            if (b[i].x +Block.SIZE == PlayManger.right_x) {
                rightCollision = true;
            }

            // down Wall
            if (b[i].y + Block.SIZE == PlayManger.bottom_y) {
                bottomCollision = true;
            }
        }
    }
    public void checkRotationCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        // Check frame collision

        for (int i = 0; i < b.length; i++) {
            // Left Wall
            if (tempB[i].x < PlayManger.left_x) {
                leftCollision = true;
            }

            // Right Wall
            if (tempB[i].x +Block.SIZE > PlayManger.right_x) {
                rightCollision = true;
            }

            // down Wall
            if (tempB[i].y + Block.SIZE > PlayManger.bottom_y) {
                bottomCollision = true;
            }
        }
    }


    public void update() {

        //Move the Mino
        if (Mouvment.upPressed) {
            switch (direction) {
                case 1: getDirection2(); break;
                case 2: getDirection3(); break;
                case 3: getDirection4(); break;
                case 4: getDirection1(); break;
            }
            Mouvment.upPressed = false;
        }

        checkMovementCollision();

        if (Mouvment.downPressed) {
            // If teh mino's bottom is not hitting, it can go down
            if(bottomCollision == false) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;

                //When moved down, reset the autoDropCounter
                autoDropCounter = 0;
            }
            Mouvment.downPressed = false;
        }

        if (Mouvment.leftPressed) {
            if (leftCollision == false) {
                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;
            }
            Mouvment.leftPressed = false;
        }

        if (Mouvment.rightPressed) {
            if (rightCollision == false) {
                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;

            }
            Mouvment.rightPressed = false;
        }

        if (bottomCollision) {
            active = false;
        }
        else {
            autoDropCounter++;  //the counter increase in every frames
            if (autoDropCounter == PlayManger.dropInterval) {
                // the mino goes down
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        int margin = 2;
        g2.setColor(b[0].co);
        g2.fill3DRect(b[0].x, b[0].y, Block.SIZE, Block.SIZE, true);
        g2.fill3DRect(b[1].x, b[1].y, Block.SIZE, Block.SIZE, true);
        g2.fill3DRect(b[2].x, b[2].y, Block.SIZE, Block.SIZE, true);
        g2.fill3DRect(b[3].x, b[3].y, Block.SIZE, Block.SIZE, true);
        //Rect(b[0].x, b[0].y, Block.SIZE, Block.SIZE);
    }

}
