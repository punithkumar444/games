import java.util.Random;

public class Ball {
    public Rectangle rect;
    public Rectangle leftPaddle, rightPaddle;
    private Random random = new Random();
    private double randomXDirection = random.nextBoolean() ? 1 : -1;
    private double randomYDirection = random.nextBoolean() ? 1 : -1;
    private Text playerScoreText;
    private Text AIScoreText;

    private double xVelocity = Constants.BALL_XVELOCITY * randomXDirection;
    private double yVelocity = Constants.BALL_YVELOCITY * randomYDirection;

    Ball (Rectangle rect, Rectangle leftPaddle, Rectangle rightPaddle, Text playerScoreText, Text AIScoreText) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.playerScoreText = playerScoreText;
        this.AIScoreText = AIScoreText;
    }

    public void update(double dt) {
        if (xVelocity < 0) {
            if (this.rect.x <= this.leftPaddle.x + this.leftPaddle.width && this.rect.x >= this.leftPaddle.x && this.rect.y + this.rect.height >= this.leftPaddle.y && this.rect.y <= this.leftPaddle.y + (this.leftPaddle.height/2)) {
                this.yVelocity += (this.leftPaddle.y + (this.leftPaddle.height/2) - this.rect.y) * 2;
                this.xVelocity *= -1;
            }
            else if (this.rect.x <= this.leftPaddle.x + this.leftPaddle.width && this.rect.x >= this.leftPaddle.x && this.rect.y + this.rect.height >= this.leftPaddle.y + (this.leftPaddle.height/2) && this.rect.y <= this.leftPaddle.y + this.leftPaddle.height) {
                this.xVelocity *= -1;
                this.yVelocity += (this.rect.y - this.leftPaddle.y + (this.leftPaddle.height/2)) * 2;
            }
            else if (this.rect.x + this.rect.width <= 0) {
                int AIScore = Integer.parseInt((AIScoreText.text));
                AIScore++;
                AIScoreText.text = "" + AIScore;
                this.rect.x = Constants.SCREEN_WIDTH / 2;
                this.rect.y = Constants.SCREEN_HEIGHT / 2;
                double randomYDirec = random.nextBoolean() ? 1 : -1;
                this.xVelocity = -1 * Constants.BALL_XVELOCITY;
                this.yVelocity = Constants.BALL_YVELOCITY * randomYDirec;
            }
        }
        else if (xVelocity > 0) {
            if (this.rect.x + this.rect.width >= this.rightPaddle.x && this.rect.x <= this.rightPaddle.x + this.rightPaddle.width && this.rect.y + this.rect.height >= this.rightPaddle.y && this.rect.y <= this.rightPaddle.y + (this.rightPaddle.height/2)) {
                this.xVelocity *= -1;
                this.yVelocity += (this.rightPaddle.y + (this.rightPaddle.height/2) - this.rect.y) * 2;
            }
            else if (this.rect.x + this.rect.width >= this.rightPaddle.x && this.rect.x <= this.rightPaddle.x + this.rightPaddle.width && this.rect.y + this.rect.height >= this.rightPaddle.y + (this.rightPaddle.height/2) && this.rect.y <= this.rightPaddle.y + this.rightPaddle.height) {
                this.xVelocity *= -1;
                this.yVelocity += (this.rect.y - this.rightPaddle.y + (this.rightPaddle.height/2)) * 2;
            }
            else if (this.rect.x + this.rect.width >= Constants.SCREEN_WIDTH) {
                int playerScore = Integer.parseInt(playerScoreText.text);
                playerScore++;
                playerScoreText.text = "" + playerScore;
                this.rect.x = Constants.SCREEN_WIDTH / 2;
                this.rect.y = (Constants.SCREEN_HEIGHT / 2) + Constants.TOOLBAR_HEIGHT;
                double randomYDirec = random.nextBoolean() ? 1 : -1;
                this.xVelocity = Constants.BALL_XVELOCITY;
                this.yVelocity = Constants.BALL_YVELOCITY * randomYDirec;
            }
        }

        if (yVelocity < 0) {
            if (this.rect.y < Constants.TOOLBAR_HEIGHT) {
                this.yVelocity *= -1;
            }
        }
        else if (yVelocity > 0) {
            if (this.rect.y + this.rect.height > Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) {
                this.yVelocity *= -1;
            }
        }

        this.rect.x += xVelocity * dt;
        this.rect.y += yVelocity * dt;
    }

}
