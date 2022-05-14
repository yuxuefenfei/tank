package com.wwz.model;

import com.wwz.Client;
import com.wwz.common.ResourceMgr;

import java.awt.*;

public class Bullet extends MovableObject {

    /**
     * 子弹的位置（矩形）
     */
    private final Rectangle r = new Rectangle();
    private final Tank tank;

    public Bullet(Tank tank) {
        super(tank.x + ResourceMgr.bulletWidth() / 2, tank.y + ResourceMgr.bulletHeight() / 2, ResourceMgr.bulletWidth(), ResourceMgr.bulletHeight(), 20, tank.dir);
        this.tank = tank;
    }

    @Override
    public void paint(Graphics g) {
        moving();

        if (throughBorder()) {
            Client.INSTANCE.getObjects().remove(this);
        }

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
}