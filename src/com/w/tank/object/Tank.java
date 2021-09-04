package com.w.tank.object;

import com.w.tank.DirectionEnum;
import com.w.tank.TankFrame;

import java.awt.*;

public class Tank extends GameObject {

    private final static int DEFAULT_WIDTH = 50;
    private final static int DEFAULT_HEIGHT = 50;

    /**
     * 坦克的初始速度
     */
    private int speed = 10;

    /**
     * 坦克的方向
     */
    private DirectionEnum direction;

    /**
     * 是否静止，默认静止
     */
    private boolean stop = true;

    private TankFrame frame;

    public Tank() {
        super(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

    }

    public Tank(int x, int y, TankFrame frame) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.frame = frame;
    }

    public Tank(int x, int y, DirectionEnum direction) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.direction = direction;
    }

    public Tank(int x, int y, int speed, DirectionEnum direction) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.speed = speed;
        this.direction = direction;
    }

    public Tank(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void paint(Graphics g) {
        if (!stop) {
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
        }
        g.fillRect(x, y, width, height);
    }

    public void fire() {
        frame.bullet = new Bullet(x, y, direction);
    }
}
