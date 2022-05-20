package Controller;

import Model.*;
import View.LogInView.LogInGUI;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Controller {
    private UserManager userManager;
    private User user, signedInUser;
    private TicketManager ticketManager;
    private DatabaseController dbController;
    private Ticket ticket;
    private String userName, email, firstName, lastName, role, password, errorMessage;
    private MainFrame view;
    private LogInGUI logInView;
    private AttachedFiles attachedFiles;

    public Controller()  {
        userManager = UserManager.getInstance();
        ticketManager = TicketManager.getInstance();
        dbController = new DatabaseController(this);
        startThreads();
        logInView = new LogInGUI(this);
        attachedFiles = new AttachedFiles();
        //openMainWindow();
    }

    public void startThreads() {
        getAllUsersFromDatabase();
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
     *
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

    public void onSignInBtnClick() {
        if (isFieldFilledLogIn()) {
            if (tryLogin()) {
                openMainWindow();
                logInView.getFrame().dispose();
            }
        }
    }

    /**
     * switch  to Profile view
     * loads username,  to profileWindow
     */
    public void switchToProfile() {
        view.profileView();
    }


    /**
     * switch scene to ticket view
     */
    public void switchToTicket() {
        view.ticketView();
    }

    /**
     * Will be used to switch to main menu
     */
    public void switchToMainMenu() {

    }


    /**
     * Will be used to switch to settings
     */
    public void switchToSettings() {
    }

    /**
     * Will be used to switch to statistics
     */
    public void switchToStatistics() {
        view.statisticsView();
    }

    /**
     * This method opens the main window
     */
    public void openMainWindow() {
        view = new MainFrame(this);
    }

    public void switchToUserAdmin() {
        ArrayList<String> list = new ArrayList<>();
        for (User u : userManager.getAllUsers()) {
            list.add(u.getFirstName());
            list.add(u.getLastName());
            list.add(u.getEmail());
            list.add(u.getRole());
        }
        view.userAdminView(list);
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

    public void deleteUser(String email) {
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
        } else {
            showMessage("Can't delete current logged in user");
        }
        switchToUserAdmin();
    }

    /**
     * Adds a user to the list of users in UserManager
     *
     * @param u
     * @author Jakob Hagman
     */
    public void newUser(User u) {
        userManager.addToUsers(u);
    }

    /**
     * Attempts to login
     *
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
    public void newTicket(String topic, String description, int priority, String type, String owner, ArrayList<String> assignees) throws Exception {
        User u = userManager.getUserFromString(owner);
        int id = dbController.newTicket();
        ticket = new Ticket(id, u, topic, description);
        new AssigneesThread(ticket, assignees);
        ticket.setPriority(priority);
        ticket.setCategory(type);
        if (owner == "none@email.com") {
            ticket.setStatus("Open");
        } else {
            ticket.setStatus("In progress");
        }
        ticketManager.addTicketToList(ticket);
        dbController.updateTicket(ticket);
    }


    public void newTicketfromDB(Ticket ticket) {
        ticketManager.addTicketToList(ticket);
    }

    public void updateTicket(int id, String topic, String description, int priority, String owner, String type, ArrayList<String> assignees) throws Exception {
        Ticket ticketToUpdate = ticketManager.getTicket(id);
        ticketToUpdate.setTopic(topic);
        ticketToUpdate.setDescription(description);
        ticketToUpdate.setPriority(priority);
        ticketToUpdate.setCategory(type);
        new AssigneesThread(ticketToUpdate, assignees);
        User user = userManager.getUserFromString(owner);
        ticketToUpdate.setOwner(user);
        if (owner == "none@email.com") {
            ticket.setStatus("Open");
        } else {
            ticket.setStatus("In progress");
        }
        dbController.updateTicket(ticketToUpdate);
    }

    public void closeTicket(int id) {
        Ticket ticketToClose = ticketManager.getTicket(id);
        ticketToClose.setEnddate(new Date());
        ticketToClose.setStatus("Closed");
        try {
            dbController.updateTicket(ticketToClose);
        } catch (SQLException ex) {
        }
    }

    public void removeAgent(String user, int id) {
        ticket = ticketManager.getTicket(id);
        User u = userManager.getUserFromString(user);
        ticket.removeAgent(u);
        try {
            dbController.removeAgent(ticket, u);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    /**
     * Get tickets with current user assigned
     */
    public ArrayList<Ticket> getMyTickets() {
        user = getSignedInUser();
        return ticketManager.getMyTickets(user.getEmail());
    }

    /**
     * Gets all tickets from the TicketManager Object
     *
     * @return
     */
    public ArrayList getAllTicketsFromManager() {
        ArrayList<Ticket> ticketList = new ArrayList<>();
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
    public User getSignedInUser() {
        return signedInUser;
    }

    /**
     * Gets all users from UserManager Object
     *
     * @return
     */
    public ArrayList getAllUsersFromManager() {
        ArrayList<User> userList = new ArrayList<>();
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

    public User getUserFromString(String user) {
        User u = userManager.getUserFromString(user);
        return u;
    }

    public String userPassword(String email) {
        String password = "";
        for (User u : userManager.getAllUsers()) {
            if (u.getEmail().equals(email)) {
                password = u.getPassword();
            }
        }
        return password;
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
        if (logInView.getEmailTextField().getText().isEmpty() || logInView.getPasswordField().getText().isEmpty()) {
            isFilled = false;
            errorMessage = "Missing e-mail or password";
            showMessage(errorMessage);
        }
        return isFilled;
    }

    /**
     * Checks if any of the fields in the sign up fields is empty
     * @return boolean
     * @author Jakob Hagman
     */
    private boolean isFieldFilledSignUp() {
        boolean isFilled = true;
        if (logInView.getFirstNameTextField().getText().isEmpty()
                || logInView.getLastNameTextField().getText().isEmpty()
                || logInView.getEmailTextField().getText().isEmpty()
                || logInView.getPasswordField().getText().isEmpty()) {
            isFilled = false;
        }
        return isFilled;
    }

    public void addTicketToManager(Ticket t) {
        ticketManager.addTicketToList(t);
    }

    public void populatePeopleBox(JComboBox box) {
        ArrayList<User> userList = getAllUsersFromManager();
        for (User u : userList) {
            box.addItem(u.toString());
        }
    }

    public Ticket getTicketInfo(int id) {
        Ticket editTicket = ticketManager.getTicket(id);
        return editTicket;
    }

    public String showTicketSummary(int id) {
        Ticket ticketToShow = ticketManager.getTicket(id);
        String assigneesString = ticketToShow.getAgentsAsStrings().stream().map(Object::toString)
                .collect(Collectors.joining(", "));
        String enddate = "";

        if (ticketToShow.getEnddate() != null) {
            enddate = ticketToShow.getEnddate().toString();
        }

        return String.format("ID: %s\nTOPIC: %s\nDESCRIPTION: %s\nTYPE: %s\nPRIORITY: %s\nSTATUS: %s\nOWNER: %s\nASSIGNEES: %s\nDATE OPEN: %s\nDATE CLOSE: %s\n",
                ticketToShow.getId(), ticketToShow.getTopic(), ticketToShow.getDescription(), ticketToShow.getCategory(),
                ticketToShow.getPriorityAsString(), ticketToShow.getStatus(), ticketToShow.getOwner(), assigneesString, ticketToShow.getStartdate(), enddate);
    }

    public ArrayList getTicketComments(int id) {
        Ticket ticketToShow = ticketManager.getTicket(id);
        return ticketToShow.getComments();
    }

    public ArrayList<String> getAgentsOnTicket(int id) {
        Ticket t = ticketManager.getTicket(id);
        return t.getAgentsAsStrings();
    }

    public void addAgentFromDB(String email, Ticket t) {
        User u = userManager.getUserFromString(email);
        t.addAgent(u);
    }

    public boolean editGuard(int id) {
        boolean ok = false;
        ticket = ticketManager.getTicket(id);
        if (signedInUser.getRole().equals("Admin") || ticket.getOwner() == signedInUser || ticket.getOwner().getEmail().equals("none@email.com")) {
            ok = true;
        }
        if (!ok) {
            for (User u : ticket.getAgent()) {
                if (u.equals(signedInUser)) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    public void addAttachedFile(int id, File file) throws GeneralSecurityException, IOException, SQLException {
        String strId = String.valueOf(id);
        /*String folderID = ticketManager.getTicket(id).getFile();
        if (folderID == null) {
            folderID = attachedFiles.createDriveFolder(attachedFiles.getDriveService(), strId);
            attachedFiles.moveAttachedFile(attachedFiles.getDriveService(), file.getAbsolutePath(), folderID);
            ticketManager.getTicket(id).setFile(folderID);
            dbController.updateTicket(ticketManager.getTicket(id));
        } else {
            attachedFiles.moveAttachedFile(attachedFiles.getDriveService(), file.getAbsolutePath(), folderID);
        }*/
    }

    public ArrayList<String> getAttachedFiles(int id) throws GeneralSecurityException, IOException {
        String strId = String.valueOf(id);
        ArrayList filenames = new ArrayList<String>();
        String folderID = attachedFiles.checkIfExist(attachedFiles.getDriveService(), strId);
        if (folderID != null) {
            filenames = attachedFiles.seeAttachedFiles(attachedFiles.getDriveService(), folderID);
        }
        return filenames;
    }
    
    public void getFilesFromID(int id){
       ArrayList<com.google.api.services.drive.model.File> files = attachedFiles.getFilesFromID(id);
       for(com.google.api.services.drive.model.File file : files){
           System.out.println(file.getName());
       }
    }


    public void setStatus(Ticket t) {
        if (t.getAgent().isEmpty() && t.getEnddate() != null) {
            t.setStatus("Open");
        }
    }

    public void addComment(String comment, String email, Ticket ticket) {
        User u = userManager.getUserFromString(email);
        String commentToAdd = String.format("%s: %s", u.toString(), comment);
        ticket.addComment(commentToAdd);
    }

    public void addCommentToTicket(String comment, String email, int id) {
        Ticket ticketToUpdate = ticketManager.getTicket(id);
        String commentToAdd = String.format("%s: %s", getSignedInUser().toString(), comment);
        ticketToUpdate.addComment(commentToAdd);
        try {
            dbController.newTicketComment(comment, email, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author Patrik Brandell
     * Separate thread to get all tickets from DB and add to TicketManager
     */
    private class GetAllTickets extends Thread {
        public void run() {
            try {
                dbController.getAllTickets();
            } catch (Exception e) {
                e.printStackTrace();
            }
            new getFilesThread().start();
        }
    }

    /**
     * Class extends thread.
     * Uses thread to retrieve all users from the database.
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
            getAllTickets();
        }
    }

    /**
     * This inner class adds assignees to the ticket in the database.
     * @Author Jakob Hagman
     */
    private class AssigneesThread extends Thread {
        private Ticket ticket;
        private ArrayList<String> assignees;
        private ArrayList<User> agents, assigneesToAdd;

        public AssigneesThread(Ticket t, ArrayList<String> assignees) {
            this.ticket = t;
            this.assignees = assignees;
            agents = t.getAgent();
            assigneesToAdd = new ArrayList<>();
            start();
        }

        @Override
        public void run() {
            boolean found = false;
            for (String s : assignees) {
                User assignee = getUserFromString(s);
                found = false;
                for (User u : agents) {
                    if (u.equals(assignee)) {
                        found = true;
                    }
                }
                if (!found) {
                    ticket.addAgent(assignee);
                    assigneesToAdd.add(assignee);
                }
            }
            try {
                dbController.addAgentToTicket(assigneesToAdd, ticket);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * This class is a thread that handles attatching files.
     * @author Jakob Hagman
     */
    private class placeFilesThread extends Thread{
        public placeFilesThread(){

        }

        @Override
        public void run() {

        }
    }


    /**
     * This thread retrieves all files attached to a ticket
     * @Author Jakob Hagman
     */
    private class getFilesThread extends Thread{
        @Override
        public void run() {
            try{
                attachedFiles.getAllFolders(attachedFiles.getDriveService());
                attachedFiles.getAllFiles(attachedFiles.getDriveService());
                ArrayList<Ticket> tickets = ticketManager.getAllTickets();
                for(Ticket t : tickets){
                    attachedFiles.getFilesFromID(t.getId());
                }
            }catch (GeneralSecurityException ex){
            }catch (IOException ie){}
        }
    }
}
