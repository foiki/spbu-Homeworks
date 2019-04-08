package group144.kireev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** Controller for Main FXML file*/
public class Controller {

    @FXML
    private TextField result;

    private double firstNumber = 0;
    private String operator = "";
    private boolean resultOutputted = false;

    /**
     * Action when number button is pressed
     * @param event event with information about source
     */
    public void processNumbers(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        if (result.getText().equals("0.0") && !resultOutputted) {
            if (!value.equals("0")) {
                result.setText(value);
            }
        } else if (result.getText().equals("Error!") || resultOutputted){
            result.setText(value);
            resultOutputted = false;
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
        if (!value.equals("=")) {
            if (!operator.isEmpty()) {
                return;
            }
            operator = value;
            firstNumber = Double.parseDouble(result.getText());
            result.setText("0.0");
        } else {
            if (operator.isEmpty()) {
                return;
            }
            double secondNumber = Double.parseDouble(result.getText());
            result.setText(Calculator.calculate(firstNumber, secondNumber, operator));
            operator = "";
            firstNumber = 0;
            resultOutputted = true;
        }
    }

    /**
     * Press Clear(CE) button.
     * To write a new current value
     * Sets textField ass "0"
     */
    public void pressClear() {
        result.setText("0.0");
        resultOutputted = false;
    }

    /**
     * Press ClearAll(C) button
     * To start calculations from the beginning
     */
    public void pressClearAll() {
        result.setText("0.0");
        operator = "";
        firstNumber = 0;
        resultOutputted = false;
    }

    public void changeSign() {
        double currentNumber = Double.parseDouble(result.getText());
        if (currentNumber != 0) {
            result.setText(Double.toString(-1 * currentNumber));
        }
    }

    public void initialize() {
        result.textProperty().setValue("0.0");
    }
}
