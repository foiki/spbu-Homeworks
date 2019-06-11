package group144.kireev;

public class TicTacToe {
    private int buttonPressed = 0;
    private boolean wasGameOver = false;
    private boolean isXTurn = true;

    public String getResultOfGame(String[][] field) {
        if (buttonPressed >= 5 && hasWinner(field)) {
            wasGameOver = true;
            if (isXTurn) {
                return "The winner is : 0";
            } else {
                return "The winner is : X";
            }
        }
        if (buttonPressed == field.length * field.length) {
            wasGameOver = true;
            return "Draw!";
        }
        return "";
    }

    public boolean wasGameOver() {
        return wasGameOver;
    }

    public void increaseNumberOfButtonPressed() {
        ++buttonPressed;
    }

    public boolean isXTurn() {
        return isXTurn;
    }

    public void updateNextPlayerTurn() {
        if (isXTurn) {
            isXTurn = false;
            return;
        }
        isXTurn = true;
    }

    /**
     * @param field of buttons TicTacToe game
     * @return if there is a winner on the field
     */
    private static boolean hasWinner(String[][] field) {
        boolean result = false;
        for (String[] strings : field) {
            result |= strings[0].equals(strings[1]) &&
                    strings[1].equals(strings[2]) &&
                    !strings[0].equals("");
        }

        for (int column = 0; column < field.length; column++) {
            result |= field[0][column].equals(field[1][column]) &&
                    field[1][column].equals(field[2][column]) &&
                    !field[0][column].equals("");
        }

        result |= field[0][0].equals(field[1][1]) &&
                field[1][1].equals(field[2][2]) &&
                !field[0][0].equals("");

        result |= field[0][2].equals(field[1][1]) &&
                field[1][1].equals(field[2][0]) &&
                !field[0][2].equals("");

        return result;
    }
}
