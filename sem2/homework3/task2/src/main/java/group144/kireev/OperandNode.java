package group144.kireev;

import java.util.Scanner;

/** Class realizing Operand Node of Expression Tree */
public class OperandNode implements Node {
    private int value;

    /**
     * Constructor for Operand Node
     * @param in where to read
     */
    OperandNode(Scanner in) {
        String operandAndBracket = in.next();
        int bracketIndex = operandAndBracket.length() - 1;
        if (operandAndBracket.charAt(bracketIndex) == ')') {
            String substring = operandAndBracket.substring(0, operandAndBracket.indexOf(')'));
            value = Integer.parseInt(substring);
        } else {
            value = Integer.parseInt(operandAndBracket);
        }
    }

    /**
     * Method calculates result of Node
     * @return value of Node
     */
    @Override
    public int calculate() {
        return value;
    }

    /** Prints Node to console */
    @Override
    public void print() {
        System.out.print(value);
    }
}
