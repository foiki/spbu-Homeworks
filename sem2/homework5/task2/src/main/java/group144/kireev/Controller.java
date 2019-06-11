package group144.kireev;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/** Controller for Main FXML file*/
public class Controller {

    @FXML
    private TextField result;

    @FXML
    private Spinner<Integer> firstValue;

    @FXML
    private Spinner<Integer> secondValue;

    @FXML
    private ChoiceBox<String> operator;

    private void updateValues() {
        result.setText(calculate(firstValue.getValue(), secondValue.getValue(), operator.getValue()));
    }

    /** Initialization method */
    @SuppressWarnings("unchecked")
    public void initialize() {
        operator.getItems().addAll("+", "-", "*", "/");
        operator.setValue("+");
        firstValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-9, 9, 0));
        secondValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-9, 9, 0));
        ChangeListener changeListener = (observable, oldValue, newValue) -> updateValues();
        firstValue.valueProperty().addListener(changeListener);
        secondValue.valueProperty().addListener(changeListener);
        operator.valueProperty().addListener(changeListener);
    }

    /** Method performs operation in operator to two numbers */
    public static String calculate(int firstNumber, int secondNumber, String operator) {
        double answer = 0.0;
        boolean wrongExpression = false;
        switch(operator) {
            case "+":
                answer = firstNumber + secondNumber;
                break;
            case "-":
                answer = firstNumber - secondNumber;
                break;
            case "*":
                answer = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    answer = (double) firstNumber / secondNumber;
                    break;
                }
                wrongExpression = true;
                break;
        }
        if (!wrongExpression) {
            return Double.toString(answer);
        }
        return "Error!";
    }
}
