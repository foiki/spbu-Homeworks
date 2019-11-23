package group144.kireev.ui;

import group144.kireev.core.Background;
import group144.kireev.core.Cannon;
import static group144.kireev.ui.Config.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/** Panel for cannon application. */
class ApplicationPanel extends JPanel implements ActionListener {
    private Background background;
    private Cannon cannon;

    ApplicationPanel() {
        background = new Background();
        cannon = new Cannon();
        addKeyListener(new KeyListener());
        Timer timer = new Timer(TIME_DELAY, this);
        timer.start();
        setFocusable(true);
    }

    /** {@inheritDoc} */
    @Override
    public void paintComponent(Graphics graphics) {
        background.paintComponent(graphics);
        cannon.paintComponent(graphics);
    }

    /** {@inheritDoc} */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class KeyListener extends KeyAdapter {
        /** {@inheritDoc} */
        @Override
        public void keyPressed(KeyEvent e) {
            int event = e.getKeyCode();
            if (event == KeyEvent.VK_ENTER) {
                cannon.shoot();
            } else if (event == KeyEvent.VK_LEFT) {
                cannon.moveLeft(background);
            } else if (event == KeyEvent.VK_RIGHT) {
                cannon.moveRight(background);
            } else if (event == KeyEvent.VK_UP) {
                cannon.moveGunDown();
            } else if (event == KeyEvent.VK_DOWN) {
                cannon.moveGunUp();
            }
        }
    }
}
