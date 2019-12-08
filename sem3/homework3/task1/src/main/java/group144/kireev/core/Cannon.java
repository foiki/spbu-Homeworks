package group144.kireev.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static group144.kireev.ui.Config.*;
import static java.lang.Math.abs;
import static java.lang.Math.ceil;

/** Implements entity cannon. */
public class Cannon {
    private Image cannon = null;
    private int coordinateX = SERVER_CANNON_START_POINT_X;
    private int coordinateY = SERVER_CANNON_START_POINT_Y;
    private double currentTangent = 0;
    private int currentAngle = CANNON_START_ANGLE;
    private Bullet bullet = null;

    public Cannon() {
        loadImage();
    }

    /** Load image of cannon. */
    private void loadImage() {
        try {
            cannon = ImageIO.read(new File("src/main/resources/cannon"));
        } catch (IOException e) {
            System.out.println("Cannot load cannon image!");
        }
    }

    /** Add cannon image to the game window. */
    public void paintComponent(Graphics graphics) {
        if (bullet != null) {
            bullet.paintComponent(graphics);
        }
        double centerOfWheelOnImageX = 0.45 * CANNON_WIDTH;
        double centerOfWheelOnImageY = 0.6 * CANNON_HEIGHT;
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(currentAngle), centerOfWheelOnImageX, centerOfWheelOnImageY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        graphics.drawImage(op.filter((BufferedImage) cannon, null), coordinateX, coordinateY, null);
    }

    /**
     * Perform left move for the cannon on the given background.
     * @param background on which cannon moves.
     */
    public void moveLeft(Background background) {
        if (coordinateX + CANNON_WIDTH / 3 <= 0) {
            return;
        }
        if (background.isChangePoint(coordinateX)) {
            currentTangent = background.findMountainTangentForLeftMove(coordinateX);
        }
        coordinateX -= CANNON_SPEED;
        if (currentTangent > 0) {
            moveUpOnMountain();
        } else if (currentTangent < 0) {
            moveDownOnMountain();
        }
    }

    /**
     * Perform right move for the cannon on the given background.
     * @param background on which cannon moves.
     */
    public void moveRight(Background background) {
        if (coordinateX >= GAME_WINDOW_WIDTH - CANNON_WIDTH) {
            return;
        }
        if (background.isChangePoint(coordinateX)) {
            currentTangent = background.findMountainTangentForRightMove(coordinateX);
        }
        coordinateX += CANNON_SPEED;
        if (currentTangent > 0) {
            moveDownOnMountain();
        } else if (currentTangent < 0) {
            moveUpOnMountain();
        }
    }

    /** Updates Y coordinate when cannon move up on the mountain. */
    private void moveUpOnMountain() {
        coordinateY -= (int) ceil(abs(currentTangent));
    }

    /** Updates Y coordinate when cannon move down on the mountain. */
    private void moveDownOnMountain() {
        coordinateY += (int) ceil(abs(currentTangent));
    }

    /** Updates angle on cannon gun lifting up. */
    public void moveGunUp() {
        if (currentAngle < CANNON_MAX_ANGLE) {
            ++currentAngle;
        }
    }

    /** Updates angle on cannon gun lifting down. */
    public void moveGunDown() {
        if (currentAngle > CANNON_MIN_ANGLE) {
            --currentAngle;
        }
    }

    /** Perform shoot from the gun. */
    public void shoot() {
        bullet = new Bullet(coordinateX, coordinateY, currentAngle);
    }
}
