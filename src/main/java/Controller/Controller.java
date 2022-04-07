package Controller;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Scene scene;
    private Stage newStage;
    private Stage sigOut;
    private Parent lroot;
    private Parent root2;
    private Parent troot;
    private Parent sroot;
    private Parent seroot;
    private Parent mroot;

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
     * It prints the new user in the console
     * It starts the main window
     */

    @FXML
    //Creates SigUp view
    protected void onSignOutBtnClick(ActionEvent event) throws IOException{
        lroot = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        newStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Main Menu");
        scene = new Scene(lroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();
    }
    @FXML
    //switch scene to ticket view
    public void switchToTicket(ActionEvent event) throws IOException {
        troot = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        newStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Tickets");
        scene = new Scene(troot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();
    }


    @FXML
    //switch to Settings view
    public void switchToSettings(ActionEvent event) throws IOException {
        seroot = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        newStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Settings");
        scene = new Scene(seroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();

    }
    //switch scene to Statistics view
    public void switchStatistics(ActionEvent event) throws IOException {
        sroot = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
        newStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Statistics");
        scene = new Scene(sroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();

    }


    /**
     * This method opens the main window
     * @throws IOException
     */

    public void openMainWindow(ActionEvent event) throws IOException {
        String userName = tfUsername.getText();
        String email = tfEmail.getText();
        String password = pfPassword.getText();
        user = new User(userName,password,email);
        userManager.addToUsers(user);
        newUserAlert();

        // Switch scene to StartView from SignUp View
        lroot = FXMLLoader.load(getClass().getResource("StartView.fxml"));
        newStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Main Menu");
        scene = new Scene(lroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();

    }

    /**
     * @author Jakob Hagman
     * This method prints the created userobject to the console.
     */
    public void newUserAlert() {
        System.out.println("NEW USER!\n" + user.toString());
    }

}
