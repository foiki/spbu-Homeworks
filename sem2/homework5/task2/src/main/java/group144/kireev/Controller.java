package group144.kireev;

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

    /** Initialization method*/
    public void initialize() {
        operator.getItems().addAll("+", "-", "*", "/");
        operator.setValue("+");
        SpinnerValueFactory<Integer> values = new SpinnerValueFactory.IntegerSpinnerValueFactory(-9, 9, 0);
        firstValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-9, 9, 0));
        secondValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-9, 9, 0));
        firstValue.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
        secondValue.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
        operator.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
        calculate();
    }

    /** Method calculating values from two Spinners and ChoiceBox*/
    private void calculate() {
        double answer = 0.0;
        boolean wrongExpression = false;
        int firstNumber = firstValue.getValue();
        int secondNumber = secondValue.getValue();
        switch(operator.getValue()) {
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
            result.textProperty().setValue(Double.toString(answer));
        } else {
            result.textProperty().setValue("Error!");
        }
    }
}
