package com.w.tank.object;

import com.w.tank.DirectionEnum;

import java.awt.*;

public class Bullet extends GameObject {

    /**
     * 子弹的初始速度
     */
    private int speed = 2;

    /**
     * 子弹的方向
     */
    private DirectionEnum direction;

    private final static int DEFAULT_WIDTH = 10;
    private final static int DEFAULT_HEIGHT = 10;

    public Bullet(int x, int y, DirectionEnum direction) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.direction = direction;
    }

    public Bullet(int x, int y, int width, int height, DirectionEnum direction) {
        super(x, y, width, height);
        this.direction = direction;
    }

    @Override
    public void paint(Graphics g) {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
        g.fillOval(x, y, width, height);
    }
}
