package com.wwz.service;

import com.wwz.Client;
import com.wwz.model.Wall;

/**
 * 产生墙
 */
public class GenerateWallService extends Thread {

    public static final GenerateWallService INSTANCE = new GenerateWallService();

    private GenerateWallService() {
    }

    @Override
    public void run() {
        Wall wall;
        for (int i = 0; i < 5; i++) {
            wall = new Wall(300, 300, 0);
            wall.setY(wall.getY() + i * wall.getH());
            Client.INSTANCE.objects.add(wall);
        }

        for (int i = 1; i < 8; i++) {
            wall = new Wall(300, 300, 1);
            wall.setX(wall.getX() + i * wall.getW());
            Client.INSTANCE.objects.add(wall);
        }
    }
}
