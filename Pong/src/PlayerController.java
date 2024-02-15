import java.awt.event.KeyEvent;

public class PlayerController {
    public Rectangle rectangle;
    public KL keyListener;
    
    PlayerController(Rectangle rectangle, KL keyListener) {
        this.rectangle = rectangle;
        this.keyListener = keyListener;
    }

    PlayerController(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.keyListener = null;
    }

    public void update (double dt) {
        if (keyListener != null) {
            if (keyListener.isKeyPressed(KeyEvent.VK_S)) {
                moveDown(dt);
            }
            else if (keyListener.isKeyPressed(KeyEvent.VK_W)) {
                moveUp(dt);
            }
        }
    }

    public void moveUp (double dt) {
        if (rectangle.y - Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT)
            this.rectangle.y -= Constants.PADDLE_SPEED * dt;
    }

    public void moveDown (double dt) {
        if ((rectangle.y + Constants.PADDLE_SPEED * dt) + rectangle.height < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM)
            this.rectangle.y += Constants.PADDLE_SPEED * dt;
    }
}
