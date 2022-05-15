package com.wwz;

import com.wwz.model.*;

public class TankBulletCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2, CollideChain chain) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            Tank tank = (Tank) o1;
            Bullet bullet = (Bullet) o2;

            if (bullet.getTank() != tank && tank.r.intersects(bullet.r)) {
                return true;
            }
        } else if (o1 instanceof Bullet && o2 instanceof Tank) {
            collide(o2, o1, chain);
        }
        return chain.collide(o1, o2);
    }

    @Override
    public void collided(GameObject o1, GameObject o2, CollideChain chain) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            Tank tank = (Tank) o1;
            Bullet bullet = (Bullet) o2;

            if (tank.getGroup() != bullet.getTank().getGroup()) {
                Explode explode = new Explode(0, 0);
                explode.setX(tank.getX() + tank.getW() / 2 - explode.getW() / 2);
                explode.setY(tank.getY() + tank.getH() / 2 - explode.getH() / 2);
                Client.INSTANCE.objects.add(explode);
                Client.INSTANCE.objects.remove(bullet);
                if (tank.getGroup() == Group.BAD) {
                    Client.INSTANCE.objects.remove(tank);
                }
            }
            return;

        } else if (o1 instanceof Bullet && o2 instanceof Tank) {
            collide(o2, o1, chain);
        }
        chain.collided(o1, o2);
    }
}