package com.w.tank;

import com.w.tank.object.Tank;

public class Main {

    public static void main(String[] args) {
        TankFrame frame = new TankFrame();
        frame.setVisible(true);

        for (int i = 0; i < 6; i++) {
            frame.enemyTank.add(new Tank(50 + i * 60, 200, frame,DirectionEnum.DOWN));
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
