package entity;

import java.awt.Rectangle;

public class Entity {
    
    public int x, y, velocity, gravity;
    public int width, height;

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
