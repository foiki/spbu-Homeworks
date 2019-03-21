package group144.kireev;

import java.util.Scanner;

public class ExpressionTree {
    private Node root;

    public void add(Scanner in) throws WrongExpressionException {
        if (in.hasNextInt()) {
            root = new OperandNode(in);
        } else {
            root = new OperatorNode(in);
        }
    }

    public int calculate() {
        return root.calculate();
    }

    public void print() {
        root.print();
        System.out.println();
    }
}
