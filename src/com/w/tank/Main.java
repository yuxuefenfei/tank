package com.w.tank;

public class Main {

    public static void main(String[] args) {
        TankFrame frame = new TankFrame();
        frame.setVisible(true);

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
