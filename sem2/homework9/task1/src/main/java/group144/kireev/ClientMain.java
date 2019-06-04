package group144.kireev;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/** Class starts client app */
public class ClientMain {
    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe");
        System.out.println("Enter the port: ");
        try {
            Socket socket = new Socket("localhost", (new Scanner(System.in).nextInt()));
            Game.main(TicTacToe.Player.O, socket, null);
        } catch (IOException e) {
            System.out.println("Current port is unavailable");
        }
    }
}
