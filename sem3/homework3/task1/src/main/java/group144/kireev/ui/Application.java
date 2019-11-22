package group144.kireev.ui;

import javax.swing.*;
import static group144.kireev.ui.Config.*;

/** Represents Cannon Application. */
public class Application {

    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Cannon Game");
        gameFrame.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.add(new ApplicationPanel());
        gameFrame.setVisible(true);
    }
}
