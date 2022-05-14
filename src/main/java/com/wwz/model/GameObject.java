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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}
