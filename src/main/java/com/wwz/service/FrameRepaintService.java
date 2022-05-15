package com.wwz.service;

import com.wwz.Client;

/**
 * 重画线程
 */
public class FrameRepaintService extends Thread {

    public static final FrameRepaintService INSTANCE = new FrameRepaintService();

    private FrameRepaintService() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Client.INSTANCE.repaint();
        }
    }
}
