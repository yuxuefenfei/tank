package com.wwz.model;

import com.wwz.Client;
import com.wwz.common.ResourceMgr;

import java.awt.*;

public class Tank extends MovableObject {

    /**
     * 坦克在窗口中的位置（矩形）
     */
    public final Rectangle r = new Rectangle();

    private final Group group;
    private boolean stop = true;
    private Dir oldDir;

    public Tank(int x, int y, Group group) {
        super(x, y, ResourceMgr.tankWidth(group), ResourceMgr.tankHeight(group), 4, Dir.UP);
        this.group = group;

        r.x = x;
        r.y = y;
        r.width = w;
        r.height = h;
    }

    @Override
    public void paint(Graphics g) {
        if (Group.BAD == group && Client.r.nextInt(100) > 95) {
            stop = false;
        }

        if (touchBorder()) {
            stop = true;
        }

        if (!stop) {
            moving();
        }

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

        randomFire();
        randomDir();

        r.x = x;
        r.y = y;
    }

    private void randomDir() {
        if (Group.BAD == group && Client.r.nextInt(100) > 98) {
            this.dir = Dir.values()[Client.r.nextInt(4)];
        }
    }

    private void randomFire() {
        if (Group.BAD == group && Client.r.nextInt(100) > 97) {
            fire();
        }
    }

    public void fire() {
        Bullet bullet = new Bullet(this);
        Client.INSTANCE.objects.add(bullet);
    }

    public void setState(boolean stop, Dir dir) {
        this.stop = stop;
        this.dir = dir;
    }

    @Override
    public void moving() {
        if (!collide || oldDir != dir) {
            super.moving();
        }
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

    public Dir getOldDir() {
        return oldDir;
    }

    public void setOldDir(Dir oldDir) {
        this.oldDir = oldDir;
    }
}