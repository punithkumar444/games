package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;

public class EnemyOne extends Entity {

    public final int ENEMY1_VELOCITY = 10;

    GamePanel gp;

    public EnemyOne(GamePanel gp) {
        
        this.gp = gp;
        setDefaultValues();
    }

    private void setDefaultValues() {
        
        x = gp.SCREEN_WIDTH + 4 * gp.TILE_SIZE;
        y = gp.LAND_Y + gp.TILE_SIZE / 4;
        width = 3 * gp.TILE_SIZE / 4;
        height = 3 * gp.TILE_SIZE / 4;
        velocity = ENEMY1_VELOCITY;
        gravity = 0;
    }
    public void update() {

        x -= velocity;

        if (x <= -1 * gp.TILE_SIZE) {
            Random rand = new Random();
            x = rand.nextInt(2*gp.SCREEN_WIDTH - gp.SCREEN_WIDTH + gp.TILE_SIZE) + gp.SCREEN_WIDTH + gp.TILE_SIZE;
        }

    }
    public void draw(Graphics2D g2) {
        
        g2.setColor(Color.red);
        g2.fillRect(this.x, this.y, width, height);
    }
}
