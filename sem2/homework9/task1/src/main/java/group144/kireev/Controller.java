package group144.kireev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/** Controller class for Main FXML file */
public class Controller {
    protected Button[][] field;
    protected TicTacToe game;

    /**
     * Method for action when user clicks field buttons
     * @param event action event
     */
    public void processButton(ActionEvent event) {
        if (!game.wasGameOver() && ((Button)event.getSource()).getText().equals("")) {
            game.increaseNumberOfButtonPressed();
            if (game.isXTurn()) {
                ((Button)event.getSource()).setText("X");
                text.setText("Now is 'O' turn");
            } else {
                ((Button)event.getSource()).setText("0");
                text.setText("Now is 'X' turn");
            }
            game.updateNextPlayerTurn();
        }
        String currentResult = game.getResultOfGame(getTextOnButtons(field));
        if (!currentResult.equals("")) {
            text.setText(currentResult);
        }
    }

    /**
     * @param field of buttons to get information
     * @return array of strings with text of buttons
     */
    protected static String[][] getTextOnButtons(Button[][] field) {
        return new String[][]{{field[0][0].getText(), field[0][1].getText(), field[0][2].getText()},
                {field[1][0].getText(), field[1][1].getText(), field[1][2].getText()},
                {field[2][0].getText(), field[2][1].getText(), field[2][2].getText()}};
    }

    /** Method for action when user clicks New Game Button */
    public void updateField() {
        for (Button[] buttons : field) {
            for (int j = 0; j < field.length; ++j) {
                buttons[j].setText("");
            }
        }
    }

    /** Initialization method */
    public void initialize() {
        field = new Button[][]{{button00, button01, button02},
                {button10, button11, button12},
                {button20, button21, button22}};
    }

    @FXML
    protected Label text;

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