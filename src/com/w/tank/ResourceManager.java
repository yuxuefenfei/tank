package com.w.tank;

import com.w.tank.object.Group;
import com.w.tank.utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceManager {

    public static BufferedImage[] GOOD_TANK = new BufferedImage[4];
    public static BufferedImage[] BAD_TANK = new BufferedImage[4];
    public static BufferedImage[] BULLET = new BufferedImage[4];
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        ClassLoader loader = ResourceManager.class.getClassLoader();
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
            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/e" + (i + 1) + ".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return explodes[0].getWidth();
    }

    public static int explodeHeight() {
        return explodes[0].getHeight();
    }
}
