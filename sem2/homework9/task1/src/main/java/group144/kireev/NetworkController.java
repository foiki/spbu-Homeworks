package group144.kireev;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/** Controller class for Main FXML file */
public class NetworkController extends Controller {
    public enum Status {MY_TURN, OPPONENT_TURN, EXIT}
    public enum Event {NEW_GAME, EXIT, FIELD_BUTTON}
    private Status status;
    private TicTacToe.Player player;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    /**
     * Updates settings of controller
     * @param player on which to change
     * @param socket on which to change
     * @throws IOException if there is some problems with connection
     */
    public void updateController(TicTacToe.Player player, Socket socket) throws IOException {
        this.player = player;
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());
        if (player == TicTacToe.Player.X) {
            game = new TicTacToe();
            try {
                output.writeObject(game);
            } catch (IOException e) {
                System.out.println("Problems with connection");
            }
        } else {
            try {
                game = (TicTacToe)input.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        newGame();
    }

    public Status getStatus() {
        return status;
    }

    /** Method sends event that the player has left*/
    public void disconnect() {
        sendAction(new GameEvent(Event.EXIT));
    }

    /** Method starts new game when newGame button was pressed*/
    public void newGameAction() {
        sendAction(new GameEvent(Event.NEW_GAME));
        newGame();
    }

    /** Method starts new game for current player*/
    public void newGame() {
        updateField();
        if (player == TicTacToe.Player.X) {
            status = Status.MY_TURN;
            text.setText("Your turn!");
        } else {
            status = Status.OPPONENT_TURN;
            text.setText("Opponent turn...");
            waitForTheOpponentAction();
        }
        game = new TicTacToe();
    }

    /**
     * Method for action when user clicks field buttons
     * @param event action event
     */
    public void processButton(ActionEvent event) {
        if (status.equals(Status.MY_TURN) && ((Button)event.getSource()).getText().equals("")) {
            ((Button)event.getSource()).setText(player.name());
            text.setText("Opponent turn...");
            status = Status.OPPONENT_TURN;
            newGameButton.setDisable(true);
            sendAction(new GameEvent(Event.FIELD_BUTTON, getTextOnButtons(field)));
            game.increaseNumberOfButtonPressed();
            game.updateNextPlayerTurn();
            String currentResult = game.getResultOfGame(getTextOnButtons(field));
            if (currentResult.equals("continue")) {
                waitForTheOpponentAction();
            } else {
                endGame(currentResult);
            }
        }
    }

    /**
     * Method ends game with some result
     * @param result of the game
     */
    private void endGame(String result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game over!");
        if (result.equals("Draw!")) {
            alert.setContentText(result);
        } else {
            alert.setContentText("The winner is: " + (result.equals("X") ? "X" : "O"));
        }
        alert.showAndWait();
        newGame();
    }

    /** Method creates new thread waiting for opponents action */
    private void waitForTheOpponentAction() {
        new Thread(() -> {
            GameEvent event = new GameEvent(input);
            Platform.runLater(() -> processOpponentAction(event));
        }).start();
    }

    /**
     * Method process action made by opponent
     * @param action to process
     */
    public void processOpponentAction(GameEvent action) {
        switch (action.event) {
            case FIELD_BUTTON:
                game.increaseNumberOfButtonPressed();
                game.updateNextPlayerTurn();
                text.setText("Your turn!");
                newGameButton.setDisable(false);
                status = Status.MY_TURN;
                setUpTheField(action.field);
                String currentResult = game.getResultOfGame(getTextOnButtons(field));
                if (!currentResult.equals("continue")) {
                    endGame(currentResult);
                }
                break;
            case EXIT:
                Alert exit = new Alert(Alert.AlertType.INFORMATION);
                exit.setTitle("Connection lost");
                exit.setContentText("Your opponent left the game!\nPress 'OK' to close the game");
                exit.showAndWait();
                status = Status.EXIT;
                Platform.exit();
                break;
            case NEW_GAME:
                Alert newGame = new Alert(Alert.AlertType.INFORMATION);
                newGame.setTitle("New Game");
                newGame.setContentText("Your opponent stated new game.");
                newGame.showAndWait();
                newGame();
                break;
        }
    }

    /**
     * Methods replaces the current field with a new one
     * @param field go get information
     */
    private void setUpTheField(String[][] field) {
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field.length; ++j) {
                this.field[i][j].setText(field[i][j]);
            }
        }
    }

    /**
     * Method sends the event to opponent
     * @param event to send
     */
    private void sendAction(GameEvent event) {
        try {
            if (input.available() == 0) {
                output.writeObject(event);
                output.flush();
                return;
            }
            processOpponentAction((GameEvent)input.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Connection lost");
        }
    }

    /** Class describes events can happen in the game */
    private static class GameEvent implements Serializable {
        private NetworkController.Event event;
        private String[][] field;

        private GameEvent(NetworkController.Event event) {
            this.event = event;
        }

        private GameEvent(NetworkController.Event event, String[][] field) {
            this.event = event;
            this.field = field;
        }

        private GameEvent(ObjectInputStream input) {
            GameEvent action = read(input);
            while (action == null) {
                action = read(input);
            }
            this.event = action.event;
            this.field = action.field;
        }

        /**
         * @param input Stream to read the event
         * @return GameEvent read from the input
         */
        private GameEvent read(ObjectInputStream input) {
            try {
                return (GameEvent)input.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Connection lost");
                e.printStackTrace();
            }
            return null;
        }
    }

    @FXML
    private Button newGameButton;
}
