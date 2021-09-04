package com.w.tank;

import com.w.tank.object.Bullet;
import com.w.tank.object.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    /**
     * 默认的窗口宽度 800px
     */
    private static final int DEFAULT_WIDTH = 800;

    /**
     * 默认的窗口高度 600px
     */
    private static final int DEFAULT_HEIGHT = 600;

    /**
     * 主战坦克
     */
    private Tank mainTank = new Tank(80, 60, this);

    public Bullet bullet = new Bullet(0,0,DirectionEnum.DOWN);

    public TankFrame() {
        super("坦克大战");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        addWindowListener(new TankWindowListener());
        addKeyListener(new TankKeyListener());
    }

    private class TankWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            System.exit(0);
        }
    }

    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    setStop(false);
                    setDirection(DirectionEnum.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    setStop(false);
                    setDirection(DirectionEnum.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    setStop(false);
                    setDirection(DirectionEnum.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    setStop(false);
                    setDirection(DirectionEnum.RIGHT);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_DOWN:
                    setStop(true);
                    break;
                case KeyEvent.VK_CONTROL:
                    mainTank.fire();
                    break;
                default:
                    break;
            }
        }

        private void setDirection(DirectionEnum direction) {
            mainTank.setDirection(direction);
        }

        private void setStop(boolean stop) {
            mainTank.setStop(stop);
        }
    }

    private Image screenImage = null;

    @Override
    public void update(Graphics g) {
        if (screenImage == null) {
            screenImage = createImage(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }

        Graphics graphics = screenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(screenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        mainTank.paint(g);
        bullet.paint(g);
    }
}
