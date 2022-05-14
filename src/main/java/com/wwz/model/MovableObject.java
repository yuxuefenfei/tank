package com.wwz.model;

import com.wwz.Client;

public abstract class MovableObject extends GameObject {

    protected int speed;
    protected boolean living;
    protected Dir dir;

    public MovableObject(int x, int y, int w, int h, int speed, Dir dir) {
        super(x, y, w, h);
        this.speed = speed;
        this.dir = dir;
    }

    public final void moving() {
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

    public boolean touchBorder() {
        switch (dir) {
            case UP:
                return y < Client.FRAME_BORDER;
            case DOWN:
                return y > Client.FRAME_HEIGHT - h;
            case LEFT:
                return x < 0;
            case RIGHT:
                return x > Client.FRAME_WIDTH - w;
            default:
                return false;
        }
    }

    public boolean throughBorder() {
        switch (dir) {
            case UP:
                return y < -h + Client.FRAME_BORDER;
            case DOWN:
                return y > Client.FRAME_HEIGHT;
            case LEFT:
                return x < -w;
            case RIGHT:
                return x > Client.FRAME_WIDTH;
            default:
                return false;
        }
    }
}