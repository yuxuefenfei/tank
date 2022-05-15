package com.wwz.common;

import com.wwz.model.Group;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceMgr {

    public static final BufferedImage[] GOOD_TANK = new BufferedImage[4];
    public static final BufferedImage[] BAD_TANK = new BufferedImage[4];
    public static final BufferedImage[] BULLET = new BufferedImage[4];
    public static final BufferedImage[] EXPLODES = new BufferedImage[16];
    public static final BufferedImage[] WALL = new BufferedImage[2];

    static {
        ClassLoader loader = ResourceMgr.class.getClassLoader();
        try {
            for (int i = 0; i < GOOD_TANK.length; i++) {
                GOOD_TANK[i] = ImageUtil.rotateImage(ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/GoodTank1.png"))), i * 90);
            }
            for (int i = 0; i < BAD_TANK.length; i++) {
                BAD_TANK[i] = ImageUtil.rotateImage(ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/BadTank1.png"))), i * 90);
            }
            for (int i = 0; i < BULLET.length; i++) {
                BULLET[i] = ImageUtil.rotateImage(ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/bulletU.png"))), i * 90);
            }
            for (int i = 0; i < EXPLODES.length; i++) {
                EXPLODES[i] = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/e" + (i + 1) + ".gif")));
            }

            for (int i = 0; i < WALL.length; i++) {
                WALL[i] = ImageUtil.rotateImage(ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/wall.png"))), i * 90);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ResourceMgr() {
    }

    public static int bulletWidth() {
        return BULLET[0].getWidth();
    }

    public static int bulletHeight() {
        return BULLET[0].getHeight();
    }

    public static int tankWidth(Group group) {
        return group.equals(Group.GOOD) ? GOOD_TANK[0].getWidth() : BAD_TANK[0].getWidth();
    }

    public static int tankHeight(Group group) {
        return group.equals(Group.GOOD) ? GOOD_TANK[0].getWidth() : BAD_TANK[0].getWidth();
    }

    public static int explodeWidth() {
        return EXPLODES[0].getWidth();
    }

    public static int explodeHeight() {
        return EXPLODES[0].getHeight();
    }

    public static int wallWidth(int type) {
        return WALL[type].getWidth();
    }

    public static int wallHeight(int type) {
        return WALL[type].getHeight();
    }
}