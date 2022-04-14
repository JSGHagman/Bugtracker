package Controller;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;

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
    private Parent pRoot;
    private Parent uRoot;

    private UserManager userManager;
    private User user;
    private TicketManager ticketManager;
    private DatabaseController dbController;
    private Ticket ticket;
    private String userName;
    private String email;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField pfPassword;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfUsername;
    @FXML
    Label pUsernameText;
    @FXML
    Label pEmailText;

    public Controller(){
        userManager = new UserManager();
        ticketManager = new TicketManager();
        try {
            dbController = new DatabaseController();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


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
    /**
     switch scene to Profile view
     loads username,  to profileWindow

     */
    public void switchToProfile(ActionEvent event) throws IOException {

       FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        pRoot = loader.load();
        Controller scene1Controller = loader.getController();
        //scene1Controller.pUsernameText.setText("Username: Användarnamn ska komma in här");
        //scene1Controller.pEmailText.setText("Email: Email ska komma in här");

        newStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Profile");
        scene = new Scene(pRoot);
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

    public void switchToMainmenu(ActionEvent event) throws IOException {
        lroot = FXMLLoader.load(getClass().getResource("StartView.fxml"));
        newStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker MainMenu");
        scene = new Scene(lroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();
    }
    /** Mina tickets
    *all tickets
     * assigned
*/

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
        userName = tfUsername.getText();
        email = tfEmail.getText();
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
    /**
     * @author Patrik Brandell
     * Creates new ticket with current user, creates DB entry and adds id to ticket object
     * @throws Exception
     */
    public void newTicket() throws Exception {
        ticket = new Ticket(user);
        ticket.setId(dbController.newTicket());
        ticketManager.addTicketToList(ticket);
    }

    /**
     * Get tickets with current user assigned
     */
    public void getMyTickets() {
        ArrayList myTickets = new ArrayList(ticketManager.getMyTickets(user.getUsername()));
    }

    /**
     * Get unassigned tickets based on null agent or zero size array
     */
    public void getUnassignedTickets() {
        ArrayList unassignedTickets = new ArrayList(ticketManager.getUnassignedTickets());
    }

    /**
     * Create private GetallTickets object and start thread
     */
    public void getAllTickets()  {
       GetAllTickets getAllTicketsThread = new GetAllTickets();
       getAllTicketsThread.start();

    }


    /**
     * @Author Patrik Brandell
     * Separate thread to get all tickets from DB and add to TicketManager
     */
    private class GetAllTickets extends Thread {

        public void run()  {
            ArrayList<Ticket> list = new ArrayList<>();
            try {
                list = dbController.getAllTickets();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Ticket t : list) {
                ticketManager.addTicketToList(t);
                System.out.println(t.getId());
            }
        }
    }
}

