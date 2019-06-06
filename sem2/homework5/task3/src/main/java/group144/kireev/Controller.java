package group144.kireev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** Controller for Main FXML file*/
public class Controller {

    @FXML
    private TextField result;
    private boolean isOperatorEnteredLast = false;

    /**
     * Action when number button is pressed
     * @param event event with information about source
     */
    public void processNumbers(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        if (result.getText().equals("0")) {
            result.setText(value);
            return;
        }
        result.setText(result.getText() + value);
        isOperatorEnteredLast = false;
    }

    /**
     * Action when operator button is pressed
     * @param event event with information about source
     */
    public void processOperators(ActionEvent event) {
        if (!isOperatorEnteredLast) {
            String value = ((Button)event.getSource()).getText();
            if (value.equals("=")) {
                String expression = result.getText();
                int calculatedExpression = Calculator.calculate(expression);
                result.setText(Integer.toString(calculatedExpression));
            } else {
                isOperatorEnteredLast = true;
                String operator = ((Button)event.getSource()).getText();
                result.setText(result.getText() + " " + operator + " ");
            }
        }
    }

    /**
     * Press Clear(CE) button.
     * Removes last entered element
     */
    public void pressClear() {
        String currentExpression = result.getText();
        if (currentExpression.length() == 1) {
            result.setText("0");
            isOperatorEnteredLast = false;
            return;
        }
        String newExpression = currentExpression.substring(0, currentExpression.length() - 1);
        String lastElement = currentExpression.substring(newExpression.length() - 1, newExpression.length());
        if (Calculator.isNumber(lastElement)) {
            isOperatorEnteredLast = false;
        } else {
            newExpression = newExpression.substring(0, currentExpression.length() - 3);
            isOperatorEnteredLast = true;
        }
        result.setText(newExpression);
    }

    /**
     * Press ClearAll(C) button
     * To start calculations from the beginning
     */
    public void pressClearAll() {
        result.setText("0");
        isOperatorEnteredLast = false;
    }

    /**
     * Changes sign of current number in the result field
     */
    public void changeSign() {
        int currentNumber = Integer.parseInt(result.getText());
        if (currentNumber != 0) {
            result.setText(Integer.toString(-1 * currentNumber));
        }
    }

    /** Initialization method */
    public void initialize() {
        result.textProperty().setValue("0");
    }
}
