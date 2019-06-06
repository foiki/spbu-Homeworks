package group144.kireev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            primaryStage.setTitle("Slider and Progress Bar");
            primaryStage.setMinWidth(200);
            primaryStage.setMinHeight(200);
            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}