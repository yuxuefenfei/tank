package com.wwz;

import com.wwz.model.GameObject;

import java.util.ArrayList;
import java.util.List;

public class CollideChain {

    private static final List<Collider> colliders = new ArrayList<>();
    private int INDEX_FOR_COLLIDE;
    private int INDEX_FOR_COLLIDED;

    public CollideChain add(Collider collider) {
        colliders.add(collider);
        return this;
    }

    public void remove(Collider collider) {
        colliders.remove(collider);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        if (INDEX_FOR_COLLIDE == colliders.size()) {
            INDEX_FOR_COLLIDE = 0;
            return false;
        }

        Collider collider = colliders.get(INDEX_FOR_COLLIDE++);
        return collider.collide(o1, o2, this);
    }

    public void collided(GameObject o1, GameObject o2) {
        if (INDEX_FOR_COLLIDED == colliders.size()) {
            INDEX_FOR_COLLIDED = 0;
            return;
        }

        Collider collider = colliders.get(INDEX_FOR_COLLIDED++);
        collider.collided(o1, o2, this);
    }
}
