package com.wwz.model;

import com.wwz.common.ResourceMgr;

import java.awt.*;

public class Explode extends GameObject {

    public Explode(int x, int y) {
        super(x, y, ResourceMgr.explodeWidth(), ResourceMgr.explodeHeight());
    }

    @Override
    public void paint(Graphics g) {

    }
}
