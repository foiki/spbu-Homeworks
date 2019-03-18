package group144.kireev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("File.txt");
            Scanner scanner = new Scanner(inputFile);
            ExpressionTree tree = new ExpressionTree();
            tree.add(scanner);
            tree.print();
            System.out.println("Result: " + tree.calculate());
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Input file 'File.txt' not found!");
        } catch (WrongExpressionException wrongExpression) {
            wrongExpression.getMessage();
        }
    }
}
