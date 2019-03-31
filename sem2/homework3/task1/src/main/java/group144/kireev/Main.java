package group144.kireev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashTable table = new HashTable(chooseHashFunction(in));
        System.out.println("Enter the command if you now how it works or type '6' to see help menu");
        int newRequest = -1;
        String element;
        while (newRequest != 0) {
            System.out.print("Enter new request: ");
            newRequest = in.nextInt();
            switch (newRequest) {
                case 0:
                    System.out.println("Bye!");
                    return;
                case 1:
                    System.out.print("Enter new element to add: ");
                    table.add(in.next());
                    break;
                case 2:
                    System.out.print("Enter the element to remove: ");
                    try {
                        table.remove(in.next());
                        System.out.println("Deleted");
                    } catch (ElementDoesNotExist e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter the element to find it in the HashTable: ");
                    element = in.next();
                    if (table.exist(element)) {
                        System.out.println("Element " + element + " belongs to HashTable");
                    } else {
                        System.out.println("Element " + element + " does not belong to HashTable");
                    }
                    break;
                case 4:
                    table.printStat();
                    break;
                case 5:
                    fillTableFromFile(table);
                    break;
                case 6:
                    printHelpMenu();
                    break;
            }
        }
    }

    private static HashFunction chooseHashFunction(Scanner in) {
        System.out.println("Choose the hash function: ");
        System.out.println("Enter '1' to choose polynomial HashFunction");
        System.out.println("Enter '2' to choose sum HashFunction" );
        while (true) {
            switch (in.nextInt()) {
                case 1:
                    return new PolynomialHash();
                case 2:
                    return new SumHash();
            }
        }
    }

    private static void printHelpMenu() {
        System.out.println("Enter '0' to exit");
        System.out.println("Enter '1' to add new element to HashTable");
        System.out.println("Enter '2' to remove an element from HashTable");
        System.out.println("Enter '3' to find element in the HashTable");
        System.out.println("Enter '4' to get HashTable statistic");
        System.out.println("Enter '5' to fill the table from file");
        System.out.println("Enter '5' to see help menu");
    }

    private static void fillTableFromFile(HashTable table) {
        Scanner in;
        try {
            in = new Scanner(new FileInputStream("File.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File 'File.txt' not found");
            return;
        }
        while (in.hasNext()) {
            table.add(in.next());
        }
    }
}
