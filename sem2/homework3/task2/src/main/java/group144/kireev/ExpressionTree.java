package group144.kireev;

import java.util.Scanner;

/** Class represents the expression tree structure */
public class ExpressionTree {
    private Node root;

    /**
     * Method builds the expression tree
     * @param in Scanner to read the tree
     * @throws WrongExpressionException if the expression is incorrect
     */
    public void add(Scanner in) throws WrongExpressionException {
        if (in.hasNextInt()) {
            root = new OperandNode(in);
        } else {
            root = new OperatorNode(in);
        }
    }

    /**
     * Method calculates result of tree
     * @return calculated value
     */
    public int calculate() {
        return root.calculate();
    }

    /** Prints Node to console */
    public void print() {
        root.print();
        System.out.println();
    }
}
