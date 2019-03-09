package group144.kireev;

public class Calculator {
    public int calculate(String string) throws WrongExpressionException {
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
                } catch (NullPointerException | WrongExpressionException e) {
                    throw new WrongExpressionException();
                }

            }
        }
        return stack.pop();
    }

    public boolean isNumber(String element) {
        for (int i = 1; i < element.length(); ++i) {
            if (!Character.isDigit(element.charAt(i))) {
                return false;
            }
        }
        return element.charAt(0) == '-' && element.length() > 1 || Character.isDigit(element.charAt(0));
    }

    public int operations (int firstOperand, int secondOperand, String operation) throws WrongExpressionException {
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
