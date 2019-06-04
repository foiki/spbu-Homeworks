package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
    private String[][] field;
    private TicTacToe game = new TicTacToe();

    @Test
    void rowWinnerTest() {
        field = new String[][]{{"X", "0", ""},
                {"X", "X", "X"},
                {"0", "", "0"}};
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.updateNextPlayerTurn();
        assertEquals("X", game.getResultOfGame(field));
    }

    @Test
    void columnWinnerTest() {
        field = new String[][]{{"0", "X", ""},
                {"0", "X", "X"},
                {"0", "", ""}};
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        assertEquals("O", game.getResultOfGame(field));
    }

    @Test
    void diagonalWinnerTest() {
        field = new String[][]{{"0", "X", "X"},
                {"X", "0", "X"},
                {"0", "", "0"}};
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        assertEquals("O", game.getResultOfGame(field));
    }

    @Test
    void drawTest() {
        field = new String[][]{{"0", "X", "X"},
                {"X", "X", "0"},
                {"0", "0", "X"}};
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.increaseNumberOfButtonPressed();
        game.updateNextPlayerTurn();
        assertEquals("Draw!", game.getResultOfGame(field));
    }
}