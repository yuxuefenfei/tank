package com.wwz;

import com.wwz.model.GameObject;
import com.wwz.model.Tank;

public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2, CollideChain chain) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.r.intersects(t2.r)) {
                return true;
            }
            t1.setCollide(false);
            t1.setOldDir(t1.getDir());
            t2.setCollide(false);
            t2.setOldDir(t2.getDir());
        }
        return chain.collide(o1, o2);
    }

    @Override
    public void collided(GameObject o1, GameObject o2, CollideChain chain) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            t1.setCollide(true);
            t2.setCollide(true);
            return;
        }
        chain.collided(o1, o2);
    }
}
