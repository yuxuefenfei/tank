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
    private final int speed = 20;
    /**
     * 子弹的方向
     */
    private final DirectionEnum direction;
    private final Group group;
    private final TankFrame frame;
    private final Rectangle r = new Rectangle();
    /**
     * 存活
     */
    private boolean lived = true;

    public Bullet(int x, int y, DirectionEnum direction, Group group, TankFrame frame) {
        super(x, y, WIDTH, HEIGHT);
        this.direction = direction;
        this.group = group;
        this.frame = frame;

        r.x = x;
        r.y = y;
        r.width = width;
        r.height = height;
    }

    @Override
    public void paint(Graphics g) {
        moving();
        switch (direction) {
            case UP:
                g.drawImage(ResourceManager.BULLET[0], x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.BULLET[1], x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.BULLET[2], x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.BULLET[3], x, y, null);
                break;
        }

        if (!lived || x < 0 || y < 0 || x > TankFrame.DEFAULT_WIDTH || y > TankFrame.DEFAULT_HEIGHT) {
            frame.bullets.remove(this);
        }

        r.x = x;
        r.y = y;
    }

    private void moving() {
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

    public void collide(Tank tank) {
        if (group.equals(Group.BAD)) return;
        if (r.intersects(tank.getR())) {
            this.lived = false;
            tank.setLived(false);
            frame.explodes.add(new Explode(tank.x + tank.width / 2 - Explode.WIDTH / 2, tank.y + tank.height / 2 - Explode.HEIGHT / 2, 0, 0, frame));
        }
    }
}
