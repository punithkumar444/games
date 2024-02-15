package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import entity.EnemyOne;
import entity.EnemyTwo;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {

    KeyHandler keyH = new KeyHandler();
    Thread game_thread;
    Player player = new Player(this, keyH);
    EnemyOne enemy_one = new EnemyOne(this);
    EnemyTwo enemy_two = new EnemyTwo(this);
    Rectangle player_rect;
    Rectangle enemy_one_rect;
    Rectangle enemy_two_rect;

    public final int ORIGINAL_TILE_SIZE = 16;
    public final int SCALE = 3;
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public final int LAND_Y = 8 * TILE_SIZE;
    public final int HORIZONTAL_TILES = 16;
    public final int VERTICAL_TILES = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * HORIZONTAL_TILES;
    public final int SCREEN_HEIGHT = TILE_SIZE * VERTICAL_TILES;

    final int FPS = 60;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }

    public void startGameThread() {

        game_thread = new Thread(this);
        game_thread.start();
    }

    @Override
    public void run() {

        double draw_interval = 1000000000/FPS;
        double next_draw_time = System.nanoTime() + draw_interval;

        while (game_thread != null) {
            
            update();
            repaint();

            try {
                double remaining_time = next_draw_time - System.nanoTime();
                remaining_time = remaining_time/1000000;

                if (remaining_time < 0) {
                    remaining_time = 0;
                }
                Thread.sleep((long) remaining_time);
                next_draw_time += draw_interval;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        player.update();
        enemy_one.update();
        enemy_two.update();

        player_rect = player.getBounds();
        enemy_one_rect = enemy_one.getBounds();
        enemy_two_rect = enemy_two.getBounds();

        if (player_rect.intersects(enemy_one_rect)) {
            game_thread = null;
        }
        if (player_rect.intersects(enemy_two_rect)) {
            game_thread = null;
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        player.draw(g2);
        enemy_one.draw(g2);
        enemy_two.draw(g2);

        g2.dispose();

    }

}
