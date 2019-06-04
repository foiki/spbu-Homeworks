package group144.kireev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.Socket;

public class Game extends Application {
    private static TicTacToe.Player player;
    private static Socket socket;
    private NetworkController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.updateController(player, socket);
        primaryStage.setTitle("Tic Tac Toe. Player " + player.name());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        if (!controller.getStatus().equals(NetworkController.Status.EXIT)) {
            controller.disconnect();
        } else {
            socket.close();
        }
        super.stop();
    }

    public static void main(TicTacToe.Player player, Socket socket, String[] args) {
        Game.player = player;
        Game.socket = socket;
        Application.launch(args);
    }
}
