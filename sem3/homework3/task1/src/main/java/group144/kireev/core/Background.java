package group144.kireev.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/** Describes game terrain with mountains on it. */
public class Background {
    private Image background = null;
    private static final int[] changePointsCoordinateX = new int[] {0, 130, 420, 628, 1044, 1406, 1552};
    private static final int[] changePointsCoordinateY = new int[] {630, 375, 710, 710, 255, 710, 710};

    public Background() {
        loadImage();
    }

    /** Load image of background. */
    private void loadImage() {
        try {
            background = ImageIO.read(new File("src/main/resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Add background image to the game window. */
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, null);
    }

    /**
     * @param coordinateX coordinate to check for change point.
     * @return if coordinate is top or base mountain.
     */
    public boolean isChangePoint(int coordinateX) {
        for (int changePointX : changePointsCoordinateX) {
            if (coordinateX == changePointX) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param coordinateX coordinate of start point.
     * @return tangent of angle from current point to next change point on the left.
     */
    public double findMountainTangentForLeftMove(int coordinateX) {
        int currentPosition = changePointsCoordinateX.length - 1;
        while (currentPosition > 0 && changePointsCoordinateX[currentPosition] >= coordinateX) {
            --currentPosition;
        }
        double deltaY = changePointsCoordinateY[currentPosition + 1] - changePointsCoordinateY[currentPosition];
        double deltaX = changePointsCoordinateX[currentPosition + 1] - changePointsCoordinateX[currentPosition];
        return deltaY / deltaX;
    }

    /**
     * @param coordinateX coordinate of start point.
     * @return tangent of angle from current point to next change point on the right.
     */
    public double findMountainTangentForRightMove(int coordinateX) {
        int currentPosition = 0;
        while (currentPosition < changePointsCoordinateX.length && changePointsCoordinateX[currentPosition] <= coordinateX) {
            ++currentPosition;
        }
        double deltaY = changePointsCoordinateY[currentPosition] - changePointsCoordinateY[currentPosition - 1];
        double deltaX = changePointsCoordinateX[currentPosition] - changePointsCoordinateX[currentPosition - 1];
        return deltaY / deltaX;
    }
}
