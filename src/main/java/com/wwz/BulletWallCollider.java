package com.wwz;

import com.wwz.model.GameObject;

public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2, CollideChain chain) {
        // TODO
        return chain.collide(o1, o2);
    }

    @Override
    public void collided(GameObject o1, GameObject o2, CollideChain chain) {
        //TODO
    }
}