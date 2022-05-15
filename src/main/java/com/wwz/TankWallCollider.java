package com.wwz;

import com.wwz.model.GameObject;
import com.wwz.model.Tank;
import com.wwz.model.Wall;

public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2, CollideChain chain) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;

            if (tank.r.intersects(wall.r)) {
                return true;
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            collide(o2, o1, chain);
        }
        return chain.collide(o1, o2);
    }

    @Override
    public void collided(GameObject o1, GameObject o2, CollideChain chain) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;

            tank.setCollide(true);
            return;

        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            collide(o2, o1, chain);
        }
        chain.collided(o1, o2);
    }
}