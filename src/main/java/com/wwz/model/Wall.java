package com.wwz.model;

import com.wwz.common.ResourceMgr;

import java.awt.*;

public class Wall extends GameObject {

    /**
     * 墙的类型：横、竖
     */
    private final int type;
    public final Rectangle r = new Rectangle();

    public Wall(int x, int y, int type) {
        super(x, y, ResourceMgr.wallWidth(type), ResourceMgr.wallHeight(type));
        this.type = type;
        r.x = x;
        r.y = y;
        r.width = w;
        r.height = h;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.WALL[type], x, y, null);

        r.x = x;
        r.y = y;
    }
}
