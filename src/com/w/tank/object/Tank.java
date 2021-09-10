package com.w.tank.object;

import com.w.tank.DirectionEnum;
import com.w.tank.ResourceManager;
import com.w.tank.TankFrame;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject {

    private final Group group;
    private final TankFrame frame;
    private final Random random = new Random();
    /**
     * 坦克的初始速度
     */
    private final int speed = 10;
    private final Rectangle r = new Rectangle();
    /**
     * 坦克的方向
     */
    private DirectionEnum direction;
    /**
     * 是否静止，默认静止
     */
    private boolean stop = false;
    /**
     * 存活
     */
    private boolean lived = true;


    public Tank(int x, int y, TankFrame frame, DirectionEnum direction, Group group) {
        super(x, y, ResourceManager.tankWidth(group), ResourceManager.tankHeight(group));
        this.direction = direction;
        this.frame = frame;
        this.group = group;
        r.x = x;
        r.y = y;
        r.width = width;
        r.height = height;
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

    public Rectangle getR() {
        return r;
    }

    @Override
    public void paint(Graphics g) {
        if (!lived) {
            frame.enemyTank.remove(this);
            return;
        }
        moving();
        switch (direction) {
            case UP:
                if (group.equals(Group.GOOD))
                    g.drawImage(ResourceManager.GOOD_TANK[0], x, y, null);
                else
                    g.drawImage(ResourceManager.BAD_TANK[0], x, y, null);
                break;
            case RIGHT:
                if (group.equals(Group.GOOD))
                    g.drawImage(ResourceManager.GOOD_TANK[1], x, y, null);
                else
                    g.drawImage(ResourceManager.BAD_TANK[1], x, y, null);
                break;
            case DOWN:
                if (group.equals(Group.GOOD))
                    g.drawImage(ResourceManager.GOOD_TANK[2], x, y, null);
                else
                    g.drawImage(ResourceManager.BAD_TANK[2], x, y, null);
                break;
            case LEFT:
                if (group.equals(Group.GOOD))
                    g.drawImage(ResourceManager.GOOD_TANK[3], x, y, null);
                else
                    g.drawImage(ResourceManager.BAD_TANK[3], x, y, null);
                break;
        }
        randomFire();
        randomDirection();
        boundsCheck();

        r.x = x;
        r.y = y;
    }

    private void randomDirection() {
        if (group.equals(Group.BAD) && random.nextInt(100) > 95) {
            direction = DirectionEnum.values()[random.nextInt(4)];
        }
    }

    private void randomFire() {
        if (group.equals(Group.BAD) && random.nextInt(10) > 7) {
            fire();
        }
    }

    private void moving() {
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
    }

    private void boundsCheck() {
        int border = TankFrame.DEFAULT_BORDER;
        if (x < border) x = border;
        if (y < border + 30) y = border + 30;
        if (x > TankFrame.DEFAULT_WIDTH - width - border)
            x = TankFrame.DEFAULT_WIDTH - width - border;
        if (y > TankFrame.DEFAULT_HEIGHT - height - border)
            y = TankFrame.DEFAULT_HEIGHT - height - border;
    }

    public void fire() {
        frame.bullets.add(new Bullet(x + width / 2 - Bullet.WIDTH / 2, y + height / 2 - Bullet.HEIGHT / 2, direction, group, frame));
    }
}
