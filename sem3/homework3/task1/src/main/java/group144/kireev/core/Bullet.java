package group144.kireev.core;

import static group144.kireev.ui.Config.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/** Implements entity cannon bullet. */
public class Bullet {
    private Image bullet = null;
    private boolean isBulletInFly = false;
    private int startPointX = 0;
    private int startPointY = 0;
    private int currentPointX = 0;
    private int currentPointY = 0;
    private int currentAngle;
    private int time = 0;


    protected Bullet(int startPointX, int startPointY, int angle) {
        loadImage();
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        isBulletInFly = true;
        currentAngle = -angle + CANNON_START_ANGLE;
    }

    /** Load image of bullet. */
    private void loadImage() {
        try {
            bullet = ImageIO.read(new File("src/main/resources/bullet.png"));
        } catch (IOException e) {
            System.out.println("Cannot load bullet image!");
        }
    }

    /** Add bullet image to the game window. */
    public void paintComponent(Graphics graphics) {
        if (!isBulletInFly) {
            return;
        }
        calculateCoordinatesOfBullet();
        if (!isBulletOnTheScreen() || Background.isPointInMountain(currentPointX + startPointX + BULLET_WIDTH, currentPointY + startPointY + BULLET_HEIGHT)) {
            isBulletInFly = false;
            return;
        }
        if (currentAngle > 130) {
            graphics.drawImage(bullet, currentPointX + startPointX, currentPointY + startPointY - 40, null);
        } else {
            graphics.drawImage(bullet, currentPointX + startPointX, currentPointY + startPointY, null);
        }
    }

    /** Calculate the coordinates of a bullet that files at an angle to the horizon. */
    private void calculateCoordinatesOfBullet() {
        currentPointX = (int) (START_BULLET_SPEED * time * Math.cos(Math.toRadians(currentAngle))); // x = V0*t*cos(a)
        currentPointY = (int) (-START_BULLET_SPEED * time * Math.sin(Math.toRadians(currentAngle)) + ACCELERATION_OF_FREE_FALL * time * time); // y = V0*t*sin(a) - g*t^2/2
        ++time;
    }

    /**
     * @return if the bullet visible on the screen. */
    private boolean isBulletOnTheScreen() {
        return currentPointX + startPointX > 0 && currentPointX + startPointX < GAME_WINDOW_WIDTH && currentPointY + startPointY < 810;
    }
}