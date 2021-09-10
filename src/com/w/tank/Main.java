package com.w.tank;

import com.w.tank.object.Group;
import com.w.tank.object.Tank;

public class Main {

    public static void main(String[] args) {
        TankFrame frame = new TankFrame();
        frame.setVisible(true);

        int tankNum = Integer.parseInt(ConfigManager.get("tankNum"));

        for (int i = 0; i < tankNum; i++) {
            frame.enemyTank.add(new Tank(50 + i * 100, 200, frame, DirectionEnum.DOWN, Group.BAD));
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.repaint();
            }
        }).start();
    }
}
