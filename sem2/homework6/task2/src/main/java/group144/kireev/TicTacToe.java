package group144.kireev;

import javafx.scene.control.Button;

public class TicTacToe {

    /**
     * @param field of buttons TicTacToe game
     * @return if there is a winner on the field
     */
    public static boolean hasWinner(Button[][] field) {
        boolean result = false;
        for (int row = 0; row < 3; row++) {
            result |= field[row][0].getText().equals(field[row][1].getText()) &&
                    field[row][1].getText().equals(field[row][2].getText()) &&
                    !field[row][0].getText().equals("");
        }

        for (int column = 0; column < 3; column++) {
            result |= field[0][column].getText().equals(field[1][column].getText()) &&
                    field[1][column].getText().equals(field[2][column].getText()) &&
                    !field[0][column].getText().equals("");
        }

        result |= field[0][0].getText().equals(field[1][1].getText()) &&
                field[1][1].getText().equals(field[2][2].getText()) &&
                !field[0][0].getText().equals("");

        result |= field[0][2].getText().equals(field[1][1].getText()) &&
                field[1][1].getText().equals(field[2][0].getText()) &&
                !field[0][2].getText().equals("");

        return result;
    }
}
