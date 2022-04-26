package Controller;
import Model.*;
import View.LogInView.LogInGUI;
import View.OldTicketGui.MainFrameTicket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private UserManager userManager;
    private User user, signedInUser;
    private TicketManager ticketManager;
    private DatabaseController dbController;
    private Ticket ticket;
    private String userName, email, firstName, lastName, role, password, errorMessage;
    private MainFrameTicket view;
    private LogInGUI logInView;

    public Controller() {
        userManager = UserManager.getInstance();
        ticketManager = TicketManager.getInstance();
        dbController = new DatabaseController(this);
        getAllUsers();
        logInView = new LogInGUI(this);
    }


    //Creates SignUp view
    public void onSignOutBtnClick() {
        /*
        lroot = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Main Menu");
        scene = new Scene(lroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();*/
    }


    /**
     * Method is used for creating a user, adds the to the database and the UserManager object.
     * @param event
     * @throws SQLException
     * @author Jakob Hagman
     */
    public void onSignUpBtnClick() {
        if (isFieldFilledSignUp()) {
            trySignUp();
        } else {
            showMessage("Not all fields were filled in, try again");
        }
    }

    /**
     * @author Jakob Hagman
     * Resets the textfields after a user is created
     */
    private void resetFields() {
        /*tfFirstname.clear();
        tfLastname.clear();
        tfEmail.clear();
        pfPassword.clear();*/
    }

    /**
     * @param str - The message to be displayed.
     * @author Jakob Hagman
     */
    public void showMessage(String str) {
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MESSAGE");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.show();*/
    }

    /**
     * Retrieves what role the user has selected.
     *
     * @return string Role
     */
    public String getSelectedRole() {
        /*if (adminBtn.isSelected()) {
            role = "Admin";
        }
        if (agentBtn.isSelected()) {
            role = "Agent";
        } else {
            role = "User";
        }*/
        return role;
    }

    /**
     * Attempts to login, and opens the main window of the application if ok.
     *
     * @author Jakob Hagman
     */

    public void onSignInBtnClick(){
        if (isFieldFilledLogin()) {
            if (tryLogin()) {
            }
        }
    }

    /**
     switch scene to Profile view
     loads username,  to profileWindow
     */
    public void switchToProfile() {
        /*
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
        newStage.show();*/
    }


    //switch scene to ticket view
    public void switchToTicket(){
        /*
        troot = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Tickets");
        scene = new Scene(troot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();*/
    }


    public void switchToMainmenu() {
        /*
        lroot = FXMLLoader.load(getClass().getResource("StartView.fxml"));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker MainMenu");
        scene = new Scene(lroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();*/
    }


    //switch to Settings view
    public void switchToSettings(){
        /*
        seroot = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Settings");
        scene = new Scene(seroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();*/
    }

    //switch scene to Statistics view
    public void switchToStatistics(){
        /*
        sroot = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Statistics");
        scene = new Scene(sroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();*/
    }

    /**
     * This method opens the main window
     *
     * @throws IOException
     */
    public void openMainWindow(){
        // Switch scene to StartView from SignUp View
        /*
        lroot = FXMLLoader.load(getClass().getResource("StartView.fxml"));
        newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setTitle("Bugtracker Main Menu");
        scene = new Scene(lroot);
        Image icon = new Image("https://cdn.discordapp.com/attachments/952875366005997628/960798092582588446/bugTrackerIcon.png");
        newStage.getIcons().add(icon);
        newStage.setScene(scene);
        newStage.show();*/
    }

    /**
     * Adds a user to the list of users in UserManager.
     *
     * @param u
     */
    public void newUser(User u) {
        userManager.addToUsers(u);
    }

    /**
     * @return true if login is successful.
     * @author Jakob Hagman
     * Attempts to login
     */
    public boolean tryLogin() {
        boolean success = false;
        /*
        String loginMail = tfEmailSignIn.getText();
        String password = pfPasswordSignIn.getText();
        success = userManager.checkPassword(loginMail, password);
        if (!success) {
            errorMessage = "Wrong e-mail or password!";
            errorMessageLabel.setText(errorMessage);
        }
        //System.out.println(success);
        signedInUser = userManager.getSignedInUser();

         */
        return success;
    }

    /**
     * Attempts to sign up
     * takes the values in the sign up textfields and the selcted role
     *
     * @author Jakob Hagman
     */
    public void trySignUp() {
        /*
        firstName = tfFirstname.getText();
        lastName = tfLastname.getText();
        email = tfEmail.getText();
        password = pfPassword.getText();
        role = getSelectedRole();
        if (userManager.checkIfUserExists(email)) {
            showMessage("A user with this e-mail already exists, try another one. ");
        }
        if (!validateEmail(email)) {
            showMessage("Please enter a correct email address");
        } else {
            user = new User(firstName, lastName, email, password, role);
            userManager.addToUsers(user);
            showMessage("New user created successfully, now sign in");
            resetFields();
            try {
                dbController.addNormalUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
    }

    /**
     * @throws Exception
     * @author Patrik Brandell
     * Creates new ticket with current user, creates DB entry and adds id to ticket object
     */
    public void newTicket(User u, String topic, String comment) throws Exception {
        int id = dbController.newTicket();
        ticket = new Ticket(id, u, topic, comment);
        ticketManager.addTicketToList(ticket);
        dbController.updateTicket(ticket);
    }


    public void updateTicket() throws Exception {
        if (ticket == null) {
            //newTicket();
        }
        dbController.updateTicket(ticket);
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
     * Method is used for checking if email is a correct email.
     * @param String email
     * @return boolean true if all requirements for an email is met.
     * @author Jakob Hagman
     */
    public Boolean validateEmail(String email) {
        boolean correct = false;
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if (mat.matches()) {
            correct = true;
        }
        return correct;
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
     * @return IsFilled == false when email or password input is Empty
     */
    private boolean isFieldFilledLogin() {
        boolean isFilled = true;
        /*
        if (tfEmailSignIn.getText().isEmpty() || pfPasswordSignIn.getText().isEmpty()) {
            isFilled = false;
            errorMessage = "Missing e-mail or password";
        }
        errorMessageLabel.setText(errorMessage);*/
        return isFilled;
    }

    /**
     * Checks if any of the fields in the sign up fields is empty
     *
     * @return boolean
     * @author Jakob Hagman
     */
    private boolean isFieldFilledSignUp() {
        boolean isFilled = true;
        /*
        if (tfFirstname.getText().isEmpty()
                || tfLastname.getText().isEmpty()
                || tfEmail.getText().isEmpty()
                || pfPassword.getText().isEmpty()) {
            isFilled = false;
        }*/
        return isFilled;
    }

    /**
     * Opens the makeshift gui for creating a ticket
     */
    public void newTicketGUI() {
        //view = new MainFrame(this);
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
                //System.out.println(t.getId());
            }
        }
    }

    /**
     * Class extends thread.
     * Uses thread to retrieve all users from the database.
     *
     * @author Jakob Hagman
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

