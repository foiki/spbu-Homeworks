package group144.kireev;

public class Calculator {

    /**
     * Method perform operation from operator to two values
     * @param firstValue first number
     * @param secondValue second number
     * @param operator to perform
     * @return result of operation in String type
     */
    public static String calculate(double firstValue, double secondValue, String operator) {
        switch (operator) {
            case "+":
                return Double.toString(firstValue + secondValue);
            case "-":
                return Double.toString(firstValue - secondValue);
            case "*":
                return Double.toString(firstValue * secondValue);
            default:
                if (secondValue != 0) {
                    return Double.toString(firstValue / secondValue);
                }
                return "Error!";
        }
    }
}
