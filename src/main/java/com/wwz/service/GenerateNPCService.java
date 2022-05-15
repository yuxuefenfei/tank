package com.wwz.service;

import com.wwz.Client;
import com.wwz.common.PropertiesUtil;
import com.wwz.model.Group;
import com.wwz.model.Tank;

/**
 * 产生NPC
 */
public class GenerateNPCService extends Thread {

    public static final GenerateNPCService INSTANCE = new GenerateNPCService();

    private GenerateNPCService() {
    }

    @Override
    public void run() {
        for (int i = 0; i < PropertiesUtil.getInt("npc.number"); i++) {
            Client.INSTANCE.objects.add(new Tank(Client.r.nextInt(Client.FRAME_WIDTH), Client.r.nextInt(Client.FRAME_HEIGHT), Group.BAD));
        }
    }
}
