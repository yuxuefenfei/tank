package com.wwz;

import com.wwz.common.PropertiesUtil;
import com.wwz.model.*;
import com.wwz.service.FrameRepaintService;
import com.wwz.service.GenerateNPCService;
import com.wwz.service.GenerateWallService;

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
    private final CollideChain collideChain = new CollideChain();
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

        // 碰撞检测
        for (int i = 0; i < objects.size() - 1; i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                boolean collide = collideChain.collide(objects.get(i), objects.get(j));
                if (collide) {
                    collideChain.collided(objects.get(i), objects.get(j));
                }
            }
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
                        mainTank.setState(false, Dir.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        mainTank.setState(false, Dir.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        mainTank.setState(false, Dir.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        mainTank.setState(false, Dir.RIGHT);
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
                        mainTank.setState(true, mainTank.getDir());
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

        collideChain
                .add(new TankTankCollider())
                .add(new TankBulletCollider())
                .add(new TankWallCollider());
                // .add(new BulletWallCollider());

        FrameRepaintService.INSTANCE.start();
        GenerateNPCService.INSTANCE.start();
        GenerateWallService.INSTANCE.start();
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