package group144.kireev;

import java.util.Scanner;

public class OperandNode implements Node {
    private int value;

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

    @Override
    public int calculate() {
        return value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}
