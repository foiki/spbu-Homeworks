package group144.kireev.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background {
    private static final int[] changePointsCoordinateX = new int[] {0, 180, 470, 676, 1092, 1450, 1600};
    private static final int[] changePointsCoordinateY = new int[] {700, 400, 735, 735, 280, 735, 735};

    private Image background;

    public Background() {
        loadImage();
    }

    private void loadImage() {
        try {
            background = ImageIO.read(new File("src/main/resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, null);
    }

    public boolean isChangePoint(int coordinateX) {
        for (int changePointX : changePointsCoordinateX) {
            if (coordinateX == changePointX) {
                return true;
            }
        }
        return false;
    }

    public double findMountainTangentForLeftMove(int coordinateX) {
        int currentPosition = changePointsCoordinateX.length - 1;
        while (currentPosition > 0 && changePointsCoordinateX[currentPosition] >= coordinateX) {
            --currentPosition;
        }
        double deltaY = changePointsCoordinateY[currentPosition + 1] - changePointsCoordinateY[currentPosition];
        double deltaX = changePointsCoordinateX[currentPosition + 1] - changePointsCoordinateX[currentPosition];
        return deltaY / deltaX;
    }

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
