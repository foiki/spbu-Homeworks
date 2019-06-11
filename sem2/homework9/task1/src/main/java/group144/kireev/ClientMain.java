package group144.kireev;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/** Class starts client app */
public class ClientMain {
    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe");
        System.out.println("Enter the port: ");
        try {
            String ipAddress = (new Scanner(System.in).next());
            Socket socket = new Socket(InetAddress.getByName(ipAddress), ServerMain.PORT);
            Game.main(TicTacToe.Player.O, socket, null);
        } catch (IOException e) {
            System.out.println("Current port is unavailable");
        }
    }
}
