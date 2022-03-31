package View;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button btnSignUp;

    @FXML
    private TextField pfPassword;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSignUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //do somthing when btn pressed
            }
        });

    }
}
