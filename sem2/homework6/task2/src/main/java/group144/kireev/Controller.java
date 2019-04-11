package group144.kireev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/** Controller class for Main FXML file */
public class Controller {

    private int buttonPressed = 0;

    private boolean wasGameOver = false;

    private Button[][] field;

    private boolean isXTurn = true;

    /**
     * Method for action when user clicks field buttons
     * @param event action event
     */
    public void processButton(ActionEvent event) {
        if (!wasGameOver && ((Button)event.getSource()).getText().equals("")) {
            ++buttonPressed;
            if (isXTurn) {
                ((Button)event.getSource()).setText("X");
                text.setText("Now is 'O' turn");
                isXTurn = false;
            } else {
                ((Button)event.getSource()).setText("0");
                text.setText("Now is 'X' turn");
                isXTurn = true;
            }
        }
        if (TicTacToe.hasWinner(field)) {
            if (isXTurn) {
                text.setText("The winner is : 0");
                wasGameOver = true;
            } else {
                text.setText("The winner is : X");
                wasGameOver = true;
            }
        }
        if (buttonPressed == 9) {
            text.setText("Draw!");
            wasGameOver = true;
        }
    }

    /** Method for action when user clicks New Game Button */
    public void startNewGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j].setText("");
            }
        }
        text.setText("Now is 'X' turn");
        isXTurn = true;
        buttonPressed = 0;
        wasGameOver = false;
    }

    /** Initialization method */
    public void initialize() {
        text.setText("Now is 'X' turn");
        field = new Button[][]{{button20, button21, button22},
                                {button10, button11, button12},
                                {button00, button01, button02}};
    }

    @FXML
    private Label text;

    @FXML
    private Button button00;

    @FXML
    private Button button01;

    @FXML
    private Button button02;

    @FXML
    private Button button10;

    @FXML
    private Button button11;

    @FXML
    private Button button12;

    @FXML
    private Button button20;

    @FXML
    private Button button21;

    @FXML
    private Button button22;
}
