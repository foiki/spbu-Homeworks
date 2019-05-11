package group144.kireev;

import java.util.Scanner;
import java.util.regex.Pattern;

/** Class realizing Operator Node of Expression Tree */
public class OperatorNode implements Node {
    private char operator;
    private Node left;
    private Node right;

    /**
     * Constructor for Operator Node
     * @param in where to read
     */
    OperatorNode(Scanner in) throws WrongExpressionException {
        if (!in.hasNext()) {
            throw new WrongExpressionException("Wrong expression!");
        }
        String bracketAndOperation = in.next();
        if (bracketAndOperation.equals("(")) {
            bracketAndOperation = "(*";
        }
        operator = bracketAndOperation.charAt(bracketAndOperation.length() - 1);
        if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
            throw new WrongExpressionException("Wrong expression!");
        }
        if (in.hasNextInt()) {
            left = new OperandNode(in);
        } else {
            left = new OperatorNode(in);
        }
        Pattern pattern = Pattern.compile("[-]?[0-9]+[)]*");
        if (in.hasNext(pattern)) {
            right = new OperandNode(in);
        } else {
            right = new OperatorNode(in);
        }
    }

    /**
     * Method calculates result of Node
     * @return calculated value of Node
     */
    @Override
    public int calculate() {
        switch (operator) {
            case '+':
                return left.calculate() + right.calculate();
            case '-':
                return left.calculate() - right.calculate();
            case '*':
                return left.calculate() * right.calculate();
            case '/':
                return left.calculate() / right.calculate();
            default:
                return 0;
        }
    }

    /** Prints Node to console */
    @Override
    public void print() {
        System.out.print("(" + operator + " ");
        left.print();
        System.out.print(" ");
        right.print();
        System.out.print(")");
    }
}
