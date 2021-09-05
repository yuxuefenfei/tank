package com.w.tank.object;

import com.w.tank.DirectionEnum;
import com.w.tank.ResourceManager;
import com.w.tank.TankFrame;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject {

    public final static int WIDTH = ResourceManager.tankWidth();
    public final static int HEIGHT = ResourceManager.tankHeight();

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

    /**
     * 存活
     */
    private boolean lived = true;

    private Group group;

    private TankFrame frame;

    private Random random = new Random();

    public Tank() {
        super(0, 0, WIDTH, HEIGHT);
    }

    public Tank(int x, int y, TankFrame frame) {
        super(x, y, WIDTH, HEIGHT);
        this.frame = frame;
    }

    public Tank(int x, int y, TankFrame frame, DirectionEnum direction, Group group) {
        super(x, y, WIDTH, HEIGHT);
        this.direction = direction;
        this.frame = frame;
        this.group = group;
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

    public boolean isLived() {
        return lived;
    }

    public void setLived(boolean lived) {
        this.lived = lived;
    }

    @Override
    public void paint(Graphics g) {
        if (!lived) {
            frame.enemyTank.remove(this);
            return;
        }

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
        switch (direction) {
            case UP:
                g.drawImage(ResourceManager.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR, x, y, null);
                break;
        }

        if (group.equals(Group.BAD) && random.nextInt(10) > 7) {
            fire();
        }
    }

    public void fire() {
        frame.bullets.add(new Bullet(x + width / 2 - Bullet.WIDTH / 2, y + height / 2 - Bullet.HEIGHT / 2, direction, group, frame));
    }
}
