package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;

public class EnemyTwo extends Entity {

    public final int ENEMY2_VELOCITY = 9;
    private String state;

    GamePanel gp;
    EnemyOne enemy_one;

    public EnemyTwo(GamePanel gp) {
        
        this.gp = gp;
        setDefaultValues();
    }
    
    private void setDefaultValues() {
        
        x = gp.SCREEN_WIDTH + 12 * gp.TILE_SIZE;
        y = (int)(6.5 * gp.TILE_SIZE);
        width = gp.TILE_SIZE / 4;
        height = gp.TILE_SIZE / 4;
        velocity = ENEMY2_VELOCITY;
        gravity = 0;
        state = "diving";
    }
    public void update() {

        x -= velocity;

        if (x <= -1 * gp.TILE_SIZE) {
            Random rand = new Random();
            // x = rand.nextInt(3*gp.SCREEN_WIDTH - enemy_one.x - 4*gp.TILE_SIZE) + enemy_one.x + 4*gp.TILE_SIZE;
            x = rand.nextInt(2*gp.SCREEN_WIDTH - gp.SCREEN_WIDTH - gp.TILE_SIZE) + gp.SCREEN_WIDTH + gp.TILE_SIZE;
        }
        if (state == "diving") {
            y += (velocity/3);
        } else {
            y -= (velocity/3);
        }
        if ( y >= (int)(8.5 * gp.TILE_SIZE)) {
            state = "rising";
        }
        if (y <= 5 * gp.TILE_SIZE) {
            state = "diving";
        }

    }
    public void draw(Graphics2D g2) {
        
        g2.setColor(Color.magenta);
        g2.fillRect(this.x, this.y, width, height);
    }
}
