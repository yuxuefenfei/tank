package com.wwz.model;

import com.wwz.Client;
import com.wwz.common.ResourceMgr;

import java.awt.*;

public class Explode extends GameObject {

    private int step;

    public Explode(int x, int y) {
        super(x, y, ResourceMgr.explodeWidth(), ResourceMgr.explodeHeight());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.EXPLODES[step++], x, y, null);
        if (step == ResourceMgr.EXPLODES.length) {
            Client.INSTANCE.objects.remove(this);
        }
    }
}
