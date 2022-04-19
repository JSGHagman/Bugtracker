package Controller;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

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
        private String firstName;
        private String lastName;
        private String role;
        private String password;
        private String errorMessage = "";
        @FXML
        private Button btnSignUp;
        @FXML
        private PasswordField pfPassword;
        @FXML
        private TextField tfEmail;
        @FXML
        private TextField tfFirstname;
        @FXML
        private TextField tfLastname;
        @FXML
        private Label pUsernameText;
        @FXML
        private Label pEmailText;
        @FXML
        private ComboBox roleComb;
        @FXML
        private Button btnSignIn;
        @FXML
        private TextField tfEmailSignIn;
        @FXML
        private PasswordField pfPasswordSignIn;
        @FXML
        private Label errorMessageLabel;
        @FXML
        private RadioButton userBtn;
        @FXML
        private RadioButton adminBtn;
        @FXML
        private RadioButton agentBtn;


        public Controller() {
            userManager = new UserManager();
            ticketManager = new TicketManager();
            try {
                dbController = new DatabaseController(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getAllUsers();
        }

        @FXML
        //Creates SignUp view
        public void onSignOutBtnClick(ActionEvent event) throws IOException {
            lroot = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.setTitle("Bugtracker Main Menu");
            scene = new Scene(lroot);
            Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
            newStage.getIcons().add(icon);
            newStage.setScene(scene);
            newStage.show();
        }

        @FXML
        public void onSignUpBtnClick(ActionEvent event) throws SQLException {
            if(isFieldFilledSignUp()){
                firstName = tfFirstname.getText();
                lastName = tfLastname.getText();
                email = tfEmail.getText();
                password = pfPassword.getText();

                if(adminBtn.isSelected()){
                    role = "Admin";
                }if(userBtn.isSelected()){
                    role = "User";
                }if(agentBtn.isSelected()){
                    role = "Agent";
                }else{
                    role = "User";
                }

                user = new User(firstName,lastName,email,password, role);
                dbController.addNormalUser(user);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("New user created successfully, now sign in");

                tfFirstname.clear();
                tfLastname.clear();
                tfEmail.clear();
                pfPassword.clear();
            }else{
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("ERROR");
                error.setHeaderText(null);
                error.setContentText("Not all fields were filled in, try again");
                error.show();
            }
        }

        @FXML
        public void onSignInBtnClick(ActionEvent event) {
            if(isFieldFilledLogin()){
                tryLogin();
            }
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
            // scene1Controller.pUsernameText.setText("Username: Användarnamn ska komma in här");
            //scene1Controller.pEmailText.setText("Email: Email ska komma in här");
            newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.setTitle("Bugtracker MainMenu");
            scene = new Scene(lroot);
            Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
            newStage.getIcons().add(icon);
            newStage.setScene(scene);
            newStage.show();
        }

        @FXML
        //switch to Settings view
        public void switchToSettings(ActionEvent event) throws IOException {
            seroot = FXMLLoader.load(getClass().getResource("Settings.fxml"));
            newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.setTitle("Bugtracker Statistics");
            scene = new Scene(sroot);
            Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
            newStage.getIcons().add(icon);
            newStage.setScene(scene);
            newStage.show();
        }

        /**
         * This method opens the main window
         *
         * @throws IOException
         */
        public void openMainWindow(ActionEvent event) throws IOException {
            // Switch scene to StartView from SignUp View
            lroot = FXMLLoader.load(getClass().getResource("StartView.fxml"));
            newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.setTitle("Bugtracker Main Menu");
            scene = new Scene(lroot);
            Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
            newStage.getIcons().add(icon);
            newStage.setScene(scene);
            newStage.show();
        }

        public void newUser(User u) {
            userManager.addToUsers(u);
        }

        public boolean tryLogin(){
            boolean success = false;
            String loginMail = tfEmailSignIn.getText();
            String password = pfPasswordSignIn.getText();
            success = userManager.checkPassword(loginMail, password);
            System.out.println(success);
            return success;
        }


        /**
         * @throws Exception
         * @author Patrik Brandell
         * Creates new ticket with current user, creates DB entry and adds id to ticket object
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
            if (user != null) {
                ArrayList myTickets = new ArrayList(ticketManager.getMyTickets(user.getEmail()));
            }
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
        public void getAllTickets() {
            GetAllTickets getAllTicketsThread = new GetAllTickets();
            getAllTicketsThread.start();
        }

        /**
         * @author Jakob Hagman
         * Creates object of private class and starts its thread.
         */
        public void getAllUsers() {
            GetAllUsers getAllUsers = new GetAllUsers();
            getAllUsers.start();
        }


        /**
         * @param id from GUI list
         * @return marked ticket
         * @author Patrik Brandell
         */
        public Ticket getMarkedTicket(int id) {
            return ticketManager.getTicket(id);
        }

        /**
         * @param event onAction
         * select a role from the Combo-box
         */
        @FXML
        public void selectRole(ActionEvent event) {
            role = roleComb.getSelectionModel().getSelectedItem().toString();
        }

        /**
         * @return IsFilled == false when email or password input is Empty
         */
        private boolean isFieldFilledLogin() {
            boolean isFilled = true;
            if (tfEmailSignIn.getText().isEmpty()) {
                isFilled = false;
                errorMessage = "Please enter the right e-mail!";
            }
            if (pfPasswordSignIn.getText().isEmpty()) {
                isFilled = false;
                if (errorMessage.isEmpty()) {
                    errorMessage = "Please enter the right password!";
                } else {
                    errorMessage = "Password is Empty!";
                }
            }
            errorMessageLabel.setText(errorMessage);
            return isFilled;
        }

        /**
         * Checks if any of the fields in the sign up fields is empty
         * @author Jakob Hagman
         * @return boolean
         */
        private boolean isFieldFilledSignUp() {
                boolean isFilled = true;
                if (tfFirstname.getText().isEmpty()
                        || tfLastname.getText().isEmpty()
                        || tfEmail.getText().isEmpty()
                        || pfPassword.getText().isEmpty()) {
                    isFilled = false;
                }
                return isFilled;
            }

        /**
         * @Author Patrik Brandell
         * Separate thread to get all tickets from DB and add to TicketManager
         */
        private class GetAllTickets extends Thread {
            public void run() {
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
        /**
         * Class extends thread.
         * Uses thread to retrieve all users from the database.
         */
        private class GetAllUsers extends Thread {
            @Override
            public void run() {
                try {
                    dbController.getAllUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}

