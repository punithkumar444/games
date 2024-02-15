public class AIController {
    private PlayerController playerController;
    private Rectangle ball;

    AIController(PlayerController playerController, Rectangle ball) {
        this.ball = ball;
        this.playerController = playerController;
    }

    public void update(double dt) {
        playerController.update(dt);

        if (ball.y < playerController.rectangle.y) {
            playerController.moveUp(dt);
        }
        else if (ball.y + ball.height > playerController.rectangle.y + playerController.rectangle.height) {
            playerController.moveDown(dt);
        }
    }
}
