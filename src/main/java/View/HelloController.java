package View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label goodbyeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Bugtracker!");
    }
    @FXML
    protected void goodbyeButtonClick() {
        goodbyeText.setText("Have a good day!");
    }
}