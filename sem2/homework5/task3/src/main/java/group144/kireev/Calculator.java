package group144.kireev;

public class Calculator {

    /**
     * Method perform operation from operator to two values
     * @param firstValue first number
     * @param secondValue second number
     * @param operator to perform
     * @return result of operation in String type
     */
    public static long calculate(long firstValue, long secondValue, String operator) throws IncorrectExpression {
        switch (operator) {
            case "+":
                return firstValue + secondValue;
            case "-":
                return firstValue - secondValue;
            case "*":
                return firstValue * secondValue;
            default:
                if (secondValue == 0) {
                    throw new IncorrectExpression();
                }
                return firstValue / secondValue;
        }
    }
}