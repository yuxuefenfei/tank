package com.w.tank.object;

import com.w.tank.DirectionEnum;
import com.w.tank.TankFrame;

import java.awt.*;

public class Bullet extends GameObject {

    private final static int DEFAULT_WIDTH = 10;
    private final static int DEFAULT_HEIGHT = 10;
    /**
     * 子弹的初始速度
     */
    private int speed = 2;
    /**
     * 子弹的方向
     */
    private DirectionEnum direction;
    /**
     * 存活
     */
    private boolean lived = true;

    private TankFrame frame;

    public Bullet(int x, int y, DirectionEnum direction, TankFrame frame) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.direction = direction;
        this.frame = frame;
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

        if (x < 0 || y < 0 || x > TankFrame.DEFAULT_WIDTH || y > TankFrame.DEFAULT_HEIGHT) {
            frame.bullets.remove(this);
        }
    }
}
