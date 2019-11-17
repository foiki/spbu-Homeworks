package group144.kireev.core;

import group144.kireev.ui.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;

public class Cannon implements Config {
    private Image cannon;
    private int coordinateX = START_POINT_X;
    private int coordinateY = START_POINT_Y;
    private double currentTangent = 0;

    public Cannon() {
        loadImage();
    }

    private void loadImage() {
        try {
            cannon = ImageIO.read(new File("src/main/resources/cannon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(cannon, coordinateX, coordinateY, null);
    }

    public void moveLeft(Background background) {
        if (coordinateX <= 0) {
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

    private void moveUpOnMountain() {
        coordinateY -= (int) ceil(abs(currentTangent));
    }

    private void moveDownOnMountain() {
        coordinateY += (int) ceil(abs(currentTangent));
    }

    public void moveGunUp() {

    }

    public void moveGunDown() {

    }

    public void shoot() {

    }
}
