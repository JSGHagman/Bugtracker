package Controller;
import Model.*;
import View.LogInView.LogInGUI;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
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
    private MainFrame view;
    private LogInGUI logInView;

    public Controller() {
        System.out.println("Constructor called");
        userManager = UserManager.getInstance();
        ticketManager = TicketManager.getInstance();
        dbController = new DatabaseController(this);
        getAllUsersFromDatabase();
        getAllTickets();
        logInView = new LogInGUI(this);
        //openMainWindow();
    }


    /**
     * Creates sign up view when you log out
     */
    public void onSignOutBtnClick() {
        view.dispose();
        logInView = new LogInGUI(this);
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
        logInView.getEmailTextField().setText("Enter e-mail here");
        logInView.getFirstNameTextField().setText("Enter last name here");
        logInView.getLastNameTextField().setText("Enter first name here");
        logInView.getPasswordField().setText("password");
    }

    /**
     * @param str - The message to be displayed.
     * @author Jakob Hagman
     */
    public void showMessage(String str) {
        JOptionPane.showMessageDialog(null, str);
    }

    /**
     * Retrieves what role the user has selected.
     *
     * @return string Role
     */
    public String getSelectedRole() {
        role = logInView.getRoleBox().getSelectedItem().toString();
        return role;
    }

    /**
     * Attempts to login, and opens the main window of the application if ok.
     *
     * @author Jakob Hagman
     */

    public void onSignInBtnClick(){
        if (isFieldFilledLogIn()) {
            if (tryLogin()) {
                openMainWindow();
                logInView.getFrame().dispose();
            }
        }
    }

    /**
     switch  to Profile view
     loads username,  to profileWindow
     */
    public void switchToProfile() {

    }


    /**
     * switch scene to ticket view
     */
    public void switchToTicket(){

    }

    /**
     * Will be used to switch to main menu
     */
    public void switchToMainMenu() {

    }


    /**
     * Will be used to switch to settings
     */
    public void switchToSettings(){
    }

    /**
     * Will be used to switch to statistics
     */
    public void switchToStatistics(){

    }

    /**
     * This method opens the main window
     */
    public void openMainWindow(){
        view = new MainFrame(this);
    }

    public void switchToUserAdmin() {
        String[] userList = new String[userManager.infoStrings().size()];
        userList = userManager.infoStrings().toArray(userList);
        view.userAdminView(userList);
    }

    public void selectUserinList(int index) {
        User markedUser = userManager.getUserAtIndex(index);
        view.setUsertxtUserAdmin(markedUser.getFirstName(), markedUser.getLastName(), markedUser.getEmail(), markedUser.getPassword(), markedUser.getRole());

    }

    public void updateUserDB(String firstName, String lastName, String email, String password, String role) {
        User changedUser = new User(firstName, lastName, email, password, role);

        try {
            dbController.updateUser(changedUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserManager(User user) {
        for (User u : userManager.getAllUsers()) {
            if (user.getEmail().equals(u.getEmail())) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setPassword(user.getPassword());
                u.setRole(user.getRole());
            }
            switchToUserAdmin();
        }
    }

    public void deleteUser(String email){
        if (!signedInUser.getEmail().equals(email)) {
            for (User u : userManager.getAllUsers()) {
                if (u.getEmail().equals(email)) {
                    if (userManager.deleteUser(u)) {
                        try {
                            dbController.deleteUser(u);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("error");
                    }
                }
            }
        }
        else {
            showMessage("Can't delete current logged in user");
        }
        switchToUserAdmin();
    }

    /**
     * Adds a user to the list of users in UserManager
     * @param u
     * @author Jakob Hagman
     */
    public void newUser(User u) {
        userManager.addToUsers(u);
    }

    /**
     *  Attempts to login
     * @return true if login is successful.
     * @author Jakob Hagman
     */
    public boolean tryLogin() {
        boolean success = false;
        String loginMail = logInView.getEmailTextField().getText();
        String password = logInView.getPasswordField().getText();
        success = userManager.checkPassword(loginMail, password);
        if (!success) {
            errorMessage = "Wrong e-mail or password!";
            showMessage(errorMessage);
        }
        //System.out.println(success);
        signedInUser = userManager.getSignedInUser();
        return success;
    }

    /**
     * Attempts to sign up
     * takes the values in the sign up textfields and the selcted role
     *
     * @author Jakob Hagman
     */
    public void trySignUp() {
        firstName = logInView.getFirstNameTextField().getText();
        lastName = logInView.getLastNameTextField().getText();
        email = logInView.getEmailTextField().getText();
        password = logInView.getPasswordField().getText();
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
            logInView.setLogInPanel();
            try {
                dbController.addNormalUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public void newTicketfromDB(Ticket ticket) {
        ticketManager.addTicketToList(ticket);
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
     * Gets all tickets from the TicketManager Object
     * @return
     */
    public ArrayList getAllTicketsFromManager(){
        ArrayList <Ticket> ticketList = new ArrayList<>();
        ticketList = ticketManager.getAllTickets();
        return ticketList;
    }

    /**
     * Get unassigned tickets based on null agent or zero size array
     */
    public void getUnassignedTickets() {
        ArrayList unassignedTickets = new ArrayList(ticketManager.getUnassignedTickets());
    }

    /**
     * @return User - the user that is currently signed in.
     */
    public User getSignedInUser(){
        return signedInUser;
    }

    /**
     * Gets all users from UserManager Object
     * @return
     */
    public ArrayList getAllUsersFromManager(){
        ArrayList <User> userList = new ArrayList<>();
        userList = userManager.getAllUsers();
        return userList;
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
    public void getAllUsersFromDatabase() {
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
    private boolean isFieldFilledLogIn() {
        boolean isFilled = true;
        if (logInView.getEmailTextField().getText().isEmpty() || logInView.getPasswordField().getText().isEmpty()){
            isFilled = false;
            errorMessage = "Missing e-mail or password";
            showMessage(errorMessage);
        }
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
        if (logInView.getFirstNameTextField().getText().isEmpty()
                || logInView.getLastNameTextField().getText().isEmpty()
                || logInView.getEmailTextField().getText().isEmpty()
                || logInView.getPasswordField().getText().isEmpty()){
            isFilled = false;
        }
        return isFilled;
    }

    public void populateCollaboratorsBox(JComboBox box) {
        ArrayList <User> userList = getAllUsersFromManager();
        for(User u : userList){
            String user = String.format("%s %s", u.getFirstName(), u.getLastName());
            box.addItem(user);
        }
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

