package com.w.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceManager {

    public static BufferedImage tankL;
    public static BufferedImage tankR;
    public static BufferedImage tankU;
    public static BufferedImage tankD;

    public static BufferedImage bulletL;
    public static BufferedImage bulletR;
    public static BufferedImage bulletU;
    public static BufferedImage bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        ClassLoader loader = ResourceManager.class.getClassLoader();
        try {
            tankL = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/tankL.gif")));
            tankR = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/tankR.gif")));
            tankU = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/tankU.gif")));
            tankD = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/tankD.gif")));

            bulletL = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/bulletL.gif")));
            bulletR = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/bulletR.gif")));
            bulletU = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/bulletU.gif")));
            bulletD = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/bulletD.gif")));

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(Objects.requireNonNull(loader.getResourceAsStream("images/e" + (i + 1) + ".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int bulletWidth() {
        return bulletL.getWidth();
    }

    public static int bulletHeight() {
        return bulletL.getHeight();
    }

    public static int tankWidth() {
        return tankL.getWidth();
    }

    public static int tankHeight() {
        return tankL.getHeight();
    }
}
