package com.wwz.model;

import com.wwz.common.ResourceMgr;

import java.awt.*;

public class Bullet extends GameObject {

    /**
     * 子弹的初始速度
     */
    private final int speed = 20;

    /**
     * 子弹的位置（矩形）
     */
    private final Rectangle r = new Rectangle();
    private final Tank tank;
    private final Dir dir;

    public Bullet(Tank tank) {
        super(tank.x + ResourceMgr.bulletWidth() >> 2, tank.y + ResourceMgr.bulletHeight() >> 2, ResourceMgr.bulletWidth(), ResourceMgr.bulletHeight());
        this.tank = tank;
        this.dir = tank.getDir();
    }

    @Override
    public void paint(Graphics g) {
        moving();
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.BULLET[0], x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.BULLET[1], x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.BULLET[2], x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.BULLET[3], x, y, null);
                break;
        }

        r.x = x;
        r.y = y;
    }

    private void moving() {
        switch (dir) {
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
