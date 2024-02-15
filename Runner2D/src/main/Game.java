package main;
import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) throws Exception {

        JFrame window = new JFrame();
        GamePanel game_panel = new GamePanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(game_panel);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);

        game_panel.startGameThread();

    }
}
