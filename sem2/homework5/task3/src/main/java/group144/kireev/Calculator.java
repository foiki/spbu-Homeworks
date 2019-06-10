package group144.kireev;

import java.util.Stack;

public class Calculator {

    /**
     * Method performs calculation using postfix form
     * @param expression to calculate
     * @return result of expression
     */
    public static int calculate(String expression) throws IncorrectException {
        String[] postfixForm = fromInfixToPostfix(expression);
        Stack<Integer> result = new Stack<>();
        for (String element : postfixForm) {
            if (isNumber(element)) {
                result.push((Integer.parseInt(element)));
            } else {
                int operandSecond = result.pop();
                int operandFirst = result.pop();
                try {
                    result.push(arithmeticOperation(operandFirst, operandSecond, element));
                } catch (IncorrectException e) {
                    throw e;
                }
            }
        }
        return result.pop();
    }

    /**
     * @param element to check
     * @return if the first element of String is a number
     */
    public static boolean isNumber(String element) {
        return element.charAt(0) >= '0' && element.charAt(0) <= '9';
    }

    /**
     * @param element to find priority
     * @return priority value for the sign
     */
    private static int priority(String element) {
        if (element.equals("*") || element.equals("/")) {
            return 2;
        }
        if (element.equals("+") || element.equals("-")) {
            return 1;
        }
        return 0;
    }

    /**
     * @param operators with signs entered before
     * @param element current sign to check
     * @param result postfix form for the expression
     * @param numberOfElements already added to postfix form
     */
    @SuppressWarnings("unchecked")
    private static void handlingTheSign(Stack operators, String element, String[] result, int[] numberOfElements) {
        while (!operators.isEmpty() && priority((String) operators.peek()) >= priority(element)) {
            result[numberOfElements[0]] = (String) operators.pop();
            ++numberOfElements[0];
        }
        operators.push(element);
    }

    /**
     * @param expression to translate
     * @return array of expression in postfix form
     */
    private static String[] fromInfixToPostfix(String expression) {
        Stack<String> operators = new Stack<>();
        String[] expressionInArray = expression.split(" ");
        String[] postfixExpression = new String[expressionInArray.length];
        int[] numberOfAddedElements = new int[] {0}; //Used one-element array to change int value in another method
        for (String element : expressionInArray) {
            if (isNumber(element)) {
                postfixExpression[numberOfAddedElements[0]] = element;
                ++numberOfAddedElements[0];
            } else {
                handlingTheSign(operators, element, postfixExpression, numberOfAddedElements);
            }
        }

        while (!operators.isEmpty()) {
            postfixExpression[numberOfAddedElements[0]] = operators.pop();
            ++numberOfAddedElements[0];
        }
        return postfixExpression;
    }

    /**
     * Performs arithmetic operation with two values
     * @param operandFirst first value to calculate
     * @param operandSecond second to calculate
     * @param operator operation
     * @return result of the operation
     */
    private static int arithmeticOperation(int operandFirst, int operandSecond, String operator) throws IncorrectException {
        switch (operator) {
            case "+":
                return operandFirst + operandSecond;
            case "-":
                return operandFirst - operandSecond;
            case "*":
                return operandFirst * operandSecond;
            default:
                if (operandSecond == 0) {
                    throw new IncorrectException();
                }
                return operandFirst / operandSecond;
            }
        }
}
