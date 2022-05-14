package com.wwz;

import com.wwz.model.GameObject;

/**
 * 碰撞器
 */
public interface Collider {

    /**
     * 碰撞检查
     *
     * @param o1    游戏物体
     * @param o2    游戏物体
     * @param chain 碰撞器链
     * @return 发生碰撞返回 true
     */
    boolean collide(GameObject o1, GameObject o2, CollideChain chain);

    /**
     * 碰撞完毕执行的操作
     *
     * @param o1    游戏物体
     * @param o2    游戏物体
     * @param chain 碰撞器链
     */
    void collided(GameObject o1, GameObject o2, CollideChain chain);
}
