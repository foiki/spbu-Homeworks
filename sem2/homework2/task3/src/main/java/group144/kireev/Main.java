package group144.kireev;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] array = readArray(in);
        System.out.println("Enter 'F' if you want print array to file or 'C' if you want print to console: ");
        String request = in.next();
        while (!request.equals("F") && !request.equals("C")) {
            request = in.next();
            System.out.println("Wrong request, try again!");
        }
        SpiralWriter spiralWriter;
        if (request.equals("F")) {
            spiralWriter = new FileSpiralWriter();
        }
        else {
            spiralWriter = new ConsoleSpiralWriter();
        }
        try {
            spiralWriter.printSpiral(array);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /** the method reads an array from the console */
    public static int[][] readArray(Scanner in) {
        System.out.println("Enter the size of array: ");
        int number = in.nextInt();
        System.out.println("Enter the array elements: ");
        int[][] newArray = new int[number][number];
        for (int i = 0; i < number; ++i) {
            for (int j = 0; j < number; ++j) {
                newArray[i][j] = in.nextInt();
            }
        }
        return newArray;
    }
}
