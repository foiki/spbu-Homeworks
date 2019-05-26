package group144.kireev;

import java.io.IOException;
import java.net.ServerSocket;

/** Class starts server app */
public class ServerMain {
    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe");
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            System.out.println("Port of your server: " + serverSocket.getLocalPort());
            System.out.println("Waiting for second player...");
            Game.main(TicTacToe.Player.X, serverSocket.accept(), null);

        } catch (IOException e) {
            System.out.println("Error with creating socket!");
            e.printStackTrace();
        }
    }
}
