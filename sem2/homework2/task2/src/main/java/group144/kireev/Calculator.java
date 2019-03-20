package group144.kireev;

/** Class that releases calculator*/
public class Calculator {
    public static int calculate(String string) throws WrongExpressionException {
        Stack stack = new LinkedStack();
        String[] array = string.split(" ");
        for (String element : array) {
            if (isNumber(element)) {
                stack.push(Integer.parseInt(element));
            } else {
                try {
                    int secondOperand = stack.pop();
                    int firstOperand = stack.pop();
                    stack.push(operations(firstOperand, secondOperand, element));
                } catch (EmptyStackException | WrongExpressionException e) {
                    throw new WrongExpressionException();
                }

            }
        }
        try {
            return stack.pop();
        } catch (EmptyStackException e) {
            throw new WrongExpressionException();
        }
    }

    /** Method that checks whether the String object is a number*/
    public static boolean isNumber(String element) {
        for (int i = 1; i < element.length(); ++i) {
            if (!Character.isDigit(element.charAt(i))) {
                return false;
            }
        }
        return element.charAt(0) == '-' && element.length() > 1 || Character.isDigit(element.charAt(0));
    }

    /** Method that reads two numbers and a sign
      * of the operation and returns the result
      * of this operation on the read numbers*/
    public static int operations (int firstOperand, int secondOperand, String operation) throws WrongExpressionException {
        switch (operation) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                return firstOperand / secondOperand;
            default:
                throw new WrongExpressionException();
        }
    }
}
