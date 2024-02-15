import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Window extends JFrame implements Runnable{

    public Graphics2D g2;
    public KL keyListener = new KL();
    public Rectangle player1, playerAI, ballRectangle;
    public PlayerController playerController;
    public AIController aiController;
    public Ball ball;
    public Text playerScoreText;
    public Text AIScoreText;
    public int playerScore;
    public int AIScore;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(keyListener);

        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;

        g2 = (Graphics2D)this.getGraphics();

        playerScore = 0;
        playerScoreText = new Text(playerScore, new Font("Times New Roman", Font.BOLD, Constants.TEXT_SIZE), Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT/4);
        AIScore = 0;
        AIScoreText = new Text(AIScore, new Font("Times New Roman", Font.BOLD, Constants.TEXT_SIZE), 3*Constants.SCREEN_WIDTH/4 - Constants.HZ_PADDING, Constants.SCREEN_HEIGHT/4);

        player1 = new Rectangle(Constants.HZ_PADDING , Constants.SCREEN_HEIGHT/2 - Constants.PADDLE_HEIGHT/2, Constants.PADDLE_HEIGHT, Constants.PADDLE_WIDTH, Constants.PADDLE_COLOR);
        playerAI = new Rectangle(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.HZ_PADDING, Constants.SCREEN_HEIGHT/2 - Constants.PADDLE_HEIGHT/2, Constants.PADDLE_HEIGHT, Constants.PADDLE_WIDTH, Constants.PADDLE_COLOR);
        ballRectangle = new Rectangle(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, Constants.BALL_DIAMETER, Constants.BALL_DIAMETER, Constants.BALL_COLOR);

        ball = new Ball(ballRectangle, player1, playerAI, playerScoreText, AIScoreText);

        playerController = new PlayerController(player1, keyListener);
        aiController = new AIController(new PlayerController(playerAI), ballRectangle);
        
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);
        
        playerController.update(dt);
        ball.update(dt);
        aiController.update(dt); 

    }
    
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        playerScoreText.draw(g2);
        AIScoreText.draw(g2);
        
        player1.draw(g2);
        playerAI.draw(g2);
        ballRectangle.draw(g2);
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            try {
                Thread.sleep(1000/200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
