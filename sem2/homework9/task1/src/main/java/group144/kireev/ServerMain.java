package group144.kireev;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/** Class starts server app */
public class ServerMain {
    public static final int PORT = 23456;
    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe");
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Address of your server: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Waiting for second player...");
            Game.main(TicTacToe.Player.X, serverSocket.accept(), null);

        } catch (IOException e) {
            System.out.println("Error with creating socket!");
            e.printStackTrace();
        }
    }
}
