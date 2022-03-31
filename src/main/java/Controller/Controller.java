package Controller;
import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    private UserManager userManager;
    private User user;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField pfPassword;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfUsername;

    public Controller(){
        userManager = new UserManager();
    }


    /**
     * @author Jakob Hagman
     * This method is called when the sign up button is clicked
     * It retrieves strings from the three textfields
     * It creates a new user and adds it to an ArrayList of users in the UserManager class.
     */
    @FXML
    protected void onSignUpBtnClick() {
        String userName = tfUsername.getText();
        String email = tfEmail.getText();
        String password = pfPassword.getText();
        user = new User(userName,password,email);
        userManager.addToUsers(user);
        newUserAlert();
    }


    /**
     * @author Jakob Hagman
     * This method prints the created userobject to the console.
     */
    public void newUserAlert() {
        System.out.println("NEW USER!\n" + user.toString());
    }
}
