package com.w.tank.object;


import com.w.tank.ResourceManager;
import com.w.tank.TankFrame;

import java.awt.*;

public class Explode extends GameObject {

    private int step;

    private TankFrame frame;

    public Explode(int x, int y, int width, int height, TankFrame frame) {
        super(x, y, width, height);
        this.frame = frame;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++], x, y, null);
        if (step == ResourceManager.explodes.length) {
            frame.explodes.remove(this);
        }
    }
}
