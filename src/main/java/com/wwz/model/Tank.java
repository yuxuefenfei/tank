package com.wwz.model;

import com.wwz.Client;
import com.wwz.common.ResourceMgr;

import java.awt.*;

public class Tank extends GameObject {


    private static final int speed = 4;
    /**
     * 坦克在窗口中的位置（矩形）
     */
    private static final Rectangle r = new Rectangle();

    private final Group group;
    private Dir dir;
    private boolean stop = true;

    public Tank(int x, int y, Group group) {
        super(x, y, ResourceMgr.tankWidth(group), ResourceMgr.tankHeight(group));
        this.group = group;
        this.dir = Dir.UP;
    }

    public Group getGroup() {
        return group;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void paint(Graphics g) {
        moving();
        switch (dir) {
            case UP:
                if (group.equals(Group.GOOD))
                    g.drawImage(ResourceMgr.GOOD_TANK[0], x, y, null);
                else
                    g.drawImage(ResourceMgr.BAD_TANK[0], x, y, null);
                break;
            case RIGHT:
                if (group.equals(Group.GOOD))
                    g.drawImage(ResourceMgr.GOOD_TANK[1], x, y, null);
                else
                    g.drawImage(ResourceMgr.BAD_TANK[1], x, y, null);
                break;
            case DOWN:
                if (group.equals(Group.GOOD))
                    g.drawImage(ResourceMgr.GOOD_TANK[2], x, y, null);
                else
                    g.drawImage(ResourceMgr.BAD_TANK[2], x, y, null);
                break;
            case LEFT:
                if (group.equals(Group.GOOD))
                    g.drawImage(ResourceMgr.GOOD_TANK[3], x, y, null);
                else
                    g.drawImage(ResourceMgr.BAD_TANK[3], x, y, null);
                break;
        }
        // randomFire();
        // randomDirection();
        // boundsCheck();

        r.x = x;
        r.y = y;
    }

    private void moving() {
        if (stop) {
            return;
        }

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

    public void fire() {
        Bullet bullet = new Bullet(this);
        Client.INSTANCE.getObjects().add(bullet);
    }
}
