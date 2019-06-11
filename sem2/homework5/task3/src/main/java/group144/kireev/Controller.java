package group144.kireev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** Controller for Main FXML file*/
public class Controller {

    @FXML
    private TextField result;

    private long firstNumber = 0;
    private String operator = "";
    private boolean waitingForSecondNumber = false;

    /**
     * Action when number button is pressed
     * @param event event with information about source
     */
    public void processNumbers(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        if (result.getText().equals("0") || waitingForSecondNumber) {
            if (!value.equals("0") || waitingForSecondNumber) {
                result.setText(value);
                waitingForSecondNumber = false;
            }
        } else if (result.getText().equals("Error!")){
            result.setText(value);
        } else {
            result.setText(result.getText() + value);
        }
    }

    /**
     * Action when operator button is pressed
     * @param event event with information about source
     */
    public void processOperators(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        if (value.equals("=") || !operator.equals("")) {
            if (operator.isEmpty()) {
                return;
            }
            long secondNumber = Long.parseLong(result.getText());
            long calculatedExpression = 0;
            try {
                calculatedExpression = Calculator.calculate(firstNumber, secondNumber, operator);
            } catch (IncorrectExpression e) {
                result.setText("Error!");
            }
            if (value.equals("=")) {
                result.setText(Long.toString(calculatedExpression));
                operator = "";
                firstNumber = 0;
                return;
            }
            operator = value;
            firstNumber = calculatedExpression;
            result.setText(Long.toString(calculatedExpression));
            waitingForSecondNumber = true;
        } else {
            operator = value;
            firstNumber = Long.parseLong(result.getText());
            waitingForSecondNumber = true;
        }
    }

    /**
     * Press Clear(CE) button.
     * To write a new current value
     * Sets textField ass "0"
     */
    public void pressClear() {
        result.setText("0");
    }

    /**
     * Press ClearAll(C) button
     * To start calculations from the beginning
     */
    public void pressClearAll() {
        result.setText("0");
        operator = "";
        firstNumber = 0;
        waitingForSecondNumber = false;
    }

    /**
     * Changes sign of current number in the result field
     */
    public void changeSign() {
        long currentNumber = Long.parseLong(result.getText());
        if (currentNumber != 0) {
            result.setText(Long.toString(-1 * currentNumber));
        }
    }

    /** Initialization method */
    public void initialize() {
        result.textProperty().setValue("0");
    }
}