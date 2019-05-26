package group144.kireev;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

/** Controller class for Main FXML file */
public class NetworkController extends Controller {
    public enum Status {MY_TURN, OPPONENT_TURN, EXIT}
    public enum Event {NEW_GAME, EXIT, FIELD_BUTTON}
    private Status status;
    private TicTacToe.Player player;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private TicTacToe game;

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

    public void disconnect() {
        sendAction(new GameEvent(Event.EXIT));
    }

    public void newGameAction() {
        sendAction(new GameEvent(Event.NEW_GAME));
        newGame();
    }

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
        if (isActionAvailable(event)) {
            ((Button)event.getSource()).setText(player.name());
            status = Status.OPPONENT_TURN;
            text.setText("Opponent turn...");
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

    private void endGame(String result) {
        if (result.equals("Draw!")) {
            text.setText(result);
            return;
        }
        if (result.equals(player.name())) {
            text.setText("You win!");
        } else {
            text.setText("You lose!");
        }

    }

    private void waitForTheOpponentAction() {
        new Thread(() -> {
            GameEvent event = new GameEvent(input);
            Platform.runLater(() -> processOpponentAction(event));
        }).start();
    }

    public void processOpponentAction(GameEvent action) {
        if (action.event != null) {
            switch (action.event) {
                case FIELD_BUTTON:
                    status = Status.MY_TURN;
                    game.increaseNumberOfButtonPressed();
                    game.updateNextPlayerTurn();
                    text.setText("Your turn!");
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
    }

    private void setUpTheField(String[][] field) {
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field.length; ++j) {
                this.field[i][j].setText(field[i][j]);
            }
        }
    }

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

    private boolean isActionAvailable(ActionEvent event) {
        return !game.wasGameOver() && status.equals(Status.MY_TURN) && ((Button)event.getSource()).getText().equals("");
    }

    private static class GameEvent implements Serializable {
        private NetworkController.Event event;
        private String[][] field;

        private GameEvent() {}

        private GameEvent(NetworkController.Event event) {
            this.event = event;
        }

        private GameEvent(NetworkController.Event event, String[][] field) {
            this.event = event;
            this.field = field;
        }

        private GameEvent(ObjectInputStream input) {
            GameEvent action = read(input);
            this.event = action.event;
            this.field = action.field;
        }

        private GameEvent read(ObjectInputStream input) {
            try {
                return (GameEvent)input.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("New game was started");
            } catch (IOException e) {
                System.out.println("Connection lost");
            }
            return new GameEvent();
        }
    }
}
