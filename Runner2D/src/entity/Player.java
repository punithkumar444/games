package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    public final int GRAVITY = -17;
    
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }
    private void setDefaultValues() {
        
        x = 2 * gp.TILE_SIZE;
        y = gp.LAND_Y;
        width = gp.TILE_SIZE;
        height = gp.TILE_SIZE;
        velocity = 0;
        gravity = 0;
    }
    public void update() {

        if (keyH.space_pressed == true && y == gp.LAND_Y) {
            gravity = GRAVITY;
        }
        gravity++;
        y += gravity;
        if (y >= gp.LAND_Y) {
            y = gp.LAND_Y;
        }

    }
    
    public void draw(Graphics2D g2) {
        
        g2.setColor(Color.white);
        g2.fillRect(x, y, width, height);
    }
}
