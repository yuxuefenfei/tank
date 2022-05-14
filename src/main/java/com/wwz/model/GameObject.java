package com.wwz.model;

import java.awt.*;

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
     * 高度
     */
    protected int h;
    /**
     * 宽度
     */
    protected int w;

    public GameObject(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void paint(Graphics g);
}
