package com.wwz;

import com.wwz.model.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Client extends Frame {

    public static final Client INSTANCE = new Client();
    private final List<GameObject> objects = new ArrayList<>();
    private Image screenImage;
    private Tank mainTank;

    private Client() {
        setTitle("坦克大战");
        setSize(800, 600);
    }

    private void showInfo(Graphics g) {
        // 显示子弹数量
        Color color = g.getColor();
        g.setColor(Color.RED);

        g.drawString("子弹的数量：" + objects.stream().filter(o -> o instanceof Bullet).count(), 50, 50);
        g.drawString("敌人的数量：" + objects.stream().filter(o -> o instanceof Tank).count(), 150, 50);
        g.setColor(color);
    }

    @Override
    public void paint(Graphics g) {
        showInfo(g);
        for (GameObject object : objects) {
            object.paint(g);
        }
    }

    @Override
    public void update(Graphics g) {
        if (screenImage == null) {
            screenImage = createImage(800, 600);
        }

        Graphics graphics = screenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, screenImage.getWidth(null), screenImage.getHeight(null));
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(screenImage, 0, 0, null);
        print(graphics);
    }

    public void start() {
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        mainTank.setStop(false);
                        mainTank.setDir(Dir.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        mainTank.setStop(false);
                        mainTank.setDir(Dir.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        mainTank.setStop(false);
                        mainTank.setDir(Dir.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        mainTank.setStop(false);
                        mainTank.setDir(Dir.RIGHT);
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
                        mainTank.setStop(true);
                        break;
                    case KeyEvent.VK_CONTROL:
                        mainTank.fire();
                        break;
                    default:
                        break;
                }
            }
        });

        mainTank = new Tank(400, 400, Group.GOOD);
        objects.add(mainTank);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.repaint();
            }
        }).start();
    }


    public List<GameObject> getObjects() {
        return objects;
    }
}
