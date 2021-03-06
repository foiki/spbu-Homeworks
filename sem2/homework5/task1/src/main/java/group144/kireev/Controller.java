package group144.kireev;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

public class Controller {

    @FXML
    private Slider slider;

    @FXML
    private ProgressBar progressBar;

    /** Sets the value of progress bar is equal to the value of the slider*/
    public void initialize() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> progressBar.progressProperty().setValue(newValue));
    }
}
