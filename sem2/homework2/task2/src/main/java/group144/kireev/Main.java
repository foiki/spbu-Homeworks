package group144.kireev;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter an expression in postfix form(Don't forget about spaces!) to calculate it: ");
        String string = in.nextLine();
        try {
            System.out.println("Answer: " + (new Calculator()).calculate(string));
        } catch (WrongExpressionException e) {
            System.out.println("Wrong Expression!");
        }
    }
}
