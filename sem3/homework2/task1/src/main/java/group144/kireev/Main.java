package group144.kireev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int computerNumber;
    private static Scanner in;

    public static void main(String[] args) {
        System.out.println("LocalNetwork model with virus");
        in = new Scanner(System.in);
        Network network = getNetwork();
        System.out.println(network.getStatus());
        String result = "Wait";
        System.out.println("Enter N to model next step");
        result = in.next();
        while ("N".equals(result)) {
            network.modelStep();
            System.out.println(network.getStatus());
            System.out.println("Enter N to model next step");
            result = in.next();
        }
    }

    /**
     * Reads network information from the console.
     * @return Network created from user data.
     */
    public static Network getNetwork() {
        List<Computer> computers = getComputers();
        getConnections(computers);
        Network network = new Network(computers, new Virus(new Random()));
        infectFirstComputer(network);
        return network;
    }

    /**
     * Reads computers information from the console.
     * @return List of the computers.
     */
    private static List<Computer> getComputers() {
        System.out.println("Enter the number of computers:");
        List<Computer> computers = new ArrayList<>();
        computerNumber = in.nextInt();
        System.out.println("Enter the operation system from the list for each computer");
        printOSList();
        for (int i = 0; i < computerNumber; ++i) {
            System.out.println("Enter the number of " + i + " computer OS: ");
            OS os = getOS(in.nextInt());
            computers.add(new Computer(os));
        }
        return computers;
    }

    /** Reads matrix of connections from the console and updates connected computers. */
    private static void getConnections(List<Computer> computers) {
        boolean[][] connections = new boolean[computerNumber][computerNumber];
        System.out.println("Enter the connections between computers by the pairs");
        int numberOfFirstComputer = 0;
        int numberOfSecondComputer = 0;
        while (true) {
            System.out.println("Enter the number of first computer in the pair(Starting with 0)(-1 to stop)");
            numberOfFirstComputer = in.nextInt();
            while (isNotCorrectComputerNumber(numberOfFirstComputer)) {
                System.out.println("Unknown number of computer, try again");
                numberOfFirstComputer = in.nextInt();
            }
            System.out.println("Enter the number of second computer in the pair(Starting with 0)(-1 to stop)");
            numberOfSecondComputer = in.nextInt();
            while (isNotCorrectComputerNumber(numberOfSecondComputer)) {
                System.out.println("Unknown number of computer, try again");
                numberOfSecondComputer = in.nextInt();
            }
            if (!userWantToContinue(numberOfFirstComputer, numberOfSecondComputer)) {
                Network.addConnections(computers, connections);
                return;
            }
            connections[numberOfFirstComputer][numberOfSecondComputer] = true;
        }
    }

    /**
     * @param numberOfFirstComputer number of the first read computer.
     * @param numberOfSecondComputer number of the second read computer.
     * @return if the user wants to continue entering connections between computers.
     */
    private static boolean userWantToContinue(int numberOfFirstComputer, int numberOfSecondComputer) {
        return numberOfFirstComputer != -1 && numberOfSecondComputer != -1;
    }

    /**
     * @param numberOfComputer number of the read computer.
     * @return if the number is incorrect.
     */
    private static boolean isNotCorrectComputerNumber(int numberOfComputer) {
        return numberOfComputer < -1 || numberOfComputer > computerNumber;
    }

    /**
     * @param number of OS.
     * @return OS appropriated to this number.
     */
    private static OS getOS(int number) {
        while (true) {
            switch (number) {
                case 1:
                    return OS.WINDOWS;
                case 2:
                    return OS.LINUX;
                case 3:
                    return OS.MACOS;
                default:
                    System.out.println("Unexpected number, try again");
                    number = in.nextInt();
                    break;
            }
        }
    }

    /** Prints list with available OSs. */
    private static void printOSList() {
        System.out.println("1 - Windows");
        System.out.println("2 - Linux");
        System.out.println("3 - Mac OS");
    }

    /**
     * Infects first computer in passed network.
     * @param network where to infect firsts computer.
     */
    private static void infectFirstComputer(Network network) {
        System.out.println("Enter the number of first computer to infect(Starting with 0)");
        int computerNumber = in.nextInt();
        while (isNotCorrectComputerNumber(computerNumber)) {
            System.out.println("Unknown number of computer, try again");
            computerNumber = in.nextInt();
        }
        network.infectFirstComputer(computerNumber);
    }
}
