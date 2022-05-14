package com.wwz;

import com.wwz.common.PropertiesUtil;
import com.wwz.model.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client extends Frame {

    public static final int FRAME_BORDER = 32;
    public static final int FRAME_WIDTH = PropertiesUtil.getInt("frame.width");
    public static final int FRAME_HEIGHT = PropertiesUtil.getInt("frame.height");

    public static final Client INSTANCE = new Client();
    public static final Random r = new Random();
    public final List<GameObject> objects = new ArrayList<>();
    private Image screenImage;
    private Tank mainTank;

    private Client() {
        setTitle("坦克大战");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public static void main(String[] args) {
        Client.INSTANCE.start();
        System.out.println("start...");
    }

    @Override
    public void paint(Graphics g) {
        showInfo(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
    }

    private void showInfo(Graphics g) {
        // 显示子弹数量
        Color color = g.getColor();
        g.setColor(Color.RED);

        g.drawString("子弹的数量：" + objects.stream().filter(o -> o instanceof Bullet).count(), 50, 50);
        g.drawString("敌人的数量：" + (objects.stream().filter(o -> o instanceof Tank).count() - 1), 150, 50);
        g.setColor(color);
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

        new Thread(() -> {
            for (int i = 0; i < PropertiesUtil.getInt("npc.number"); i++) {
                objects.add(new Tank(r.nextInt(FRAME_WIDTH), r.nextInt(FRAME_HEIGHT), Group.BAD));
            }
        }).start();
    }


    public List<GameObject> getObjects() {
        return objects;
    }

    @Override
    public void update(Graphics g) {
        if (screenImage == null) {
            screenImage = createImage(FRAME_WIDTH, FRAME_HEIGHT);
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
}
