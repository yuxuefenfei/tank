package com.w.tank.object;

import com.w.tank.DirectionEnum;
import com.w.tank.ResourceManager;
import com.w.tank.TankFrame;

import java.awt.*;

public class Bullet extends GameObject {

    public final static int WIDTH = ResourceManager.bulletWidth();
    public final static int HEIGHT = ResourceManager.bulletHeight();

    /**
     * 子弹的初始速度
     */
    private int speed = 20;
    /**
     * 子弹的方向
     */
    private DirectionEnum direction;
    /**
     * 存活
     */
    private boolean lived = true;

    private Group group;

    private TankFrame frame;

    public Bullet(int x, int y, DirectionEnum direction, Group group, TankFrame frame) {
        super(x, y, WIDTH, HEIGHT);
        this.direction = direction;
        this.group = group;
        this.frame = frame;
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

        switch (direction) {
            case UP:
                g.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR, x, y, null);
                break;
        }

        if (!lived || x < 0 || y < 0 || x > TankFrame.DEFAULT_WIDTH || y > TankFrame.DEFAULT_HEIGHT) {
            frame.bullets.remove(this);
        }
    }

    public void collide(Tank tank) {

        if (group.equals(Group.BAD)) {
            return;
        }

        Rectangle r1 = new Rectangle(x, y, width, height);
        Rectangle r2 = new Rectangle(tank.x, tank.y, tank.width, tank.height);

        if (r1.intersects(r2)) {
            this.lived = false;
            tank.setLived(false);
            frame.explodes.add(new Explode(tank.x - tank.width / 2, tank.y - tank.height / 2, 0, 0, frame));
        }
    }
}
