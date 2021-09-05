package com.w.tank.object;

import java.awt.*;

/**
 * 所有对象父类，任何出现在界面上的物体都要继承此对象
 */
public abstract class GameObject {

    /**
     * 横坐标
     */
    protected int x;

    /**
     * 纵坐标
     */
    protected int y;

    /**
     * 宽度
     */
    protected int width;

    /**
     * 高度
     */
    protected int height;

    protected GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void paint(Graphics g);
}
