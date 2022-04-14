package Controller;

import Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.*;

public class DatabaseController {

    private final String url = "jdbc:postgresql://pgserver.mau.se:5432/bugtracker";
    private final String user = "am4032";
    private final String password = "krhi9kxm";
    private Controller controller;

    public DatabaseController(Controller controller) throws SQLException {
        this.controller = controller;
    }

    public Connection getDBConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            System.out.println("CONNECTION ESTABLISHED");
            return con;
        }catch(Exception e){
            System.out.print(e);
            return null;
        }
    }

    public void addNormalUser (User user) throws SQLException {
        //ESTABLISHES DBCONNECTION
        Connection con = getDBConnection();
        //SPECIFIES QUERY
        String QUERY = "INSERT INTO userid (firstname, lastname, email, password, role)" +
                "VALUES ('" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.getRole() + "')";

        //EXECUTES QUERY
        Statement stmt = con.createStatement();
        stmt.executeUpdate(QUERY);
        con.close();
    }

    /**
     * @Author Patrik Brandell
     * @return int id of last ticket made
     * @throws SQLException
     *
     */
    public int newTicket() throws SQLException {
        int id;
        Connection con = getDBConnection();
        //Create empty ticket
        String QUERY = "INSERT INTO ticket";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(QUERY);
        //Get ID from ticket
        QUERY = "SELECT TOP 1 * from ticket ORDER by id DESC";
        id = stmt.executeUpdate(QUERY);
        con.close();

        return id;
    }

    /**
     * @author Patrik Brandell
     * @return ArrayList of all current tickets in db
     * @throws Exception
     */
    public ArrayList getAllTickets () throws Exception{
        ArrayList list = new ArrayList();
        Connection con = getDBConnection();
        String QUERY = String.format("SELECT * FROM ticket");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);

        while (rs.next()) {
            list.add(rs);
            System.out.println(rs);
        }
        stmt.close();
        con.close();

        return list;
    }

    /**
     * @author Jakob Hagman
     * Method used to get all registered users from the database when the application starts
     * @return ArrayList of userobjects
     * @throws Exception
     */
    public void getAllUsers() throws SQLException {
        Connection con = getDBConnection();
        String QUERY = String.format("SELECT * FROM userid");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);

        while(rs.next()){
            User u = null;
            String firstName = rs.getString("firstname");
            String lastName =  rs.getString("lastname");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");
            u = new User(firstName, lastName, email, password, role);
            controller.newUser(u);
        }
        stmt.close();
        con.close();
    }


    /*public void menu() throws SQLException {
        Scanner choice = new Scanner(System.in);
        int menuChoice = -1;
        while(menuChoice!=0){
            System.out.println("MENU");
            System.out.println("1. TEST ADD USER");
            System.out.println("2. SHOW ALL USERS");
            System.out.println("0. EXIT");
            menuChoice = choice.nextInt();
            if(menuChoice == 1){
                testAddUser();
            }else if(menuChoice == 2){
                testShowAllUsers();
            }

        }
    }*/

    /*public void testAddUser() throws SQLException {
        //ESTABLISHES DBCONNECTION
        Connection con = getDBConnection();

        //SCANNER FOR INPUT
        Scanner input = new Scanner(System.in);

        //GET NEW USER INFO
        System.out.println("ENTER FIRST NAME:");
        String firstName = input.nextLine();
        System.out.println("ENTER LAST NAME:");
        String lastName = input.nextLine();
        System.out.println("ENTER EMAIL:");
        String email = input.nextLine();
        System.out.println("ENTER PASSWORD:");
        String password = input.nextLine();
        System.out.println("ENTER YOUR ROLE:");
        String role = input.nextLine();

        //SPECIFIES QUERY
        String QUERY = "INSERT INTO userid (firstname, lastname, email, password, role)" +
                "VALUES ('" + firstName + "','" + lastName + "','" + email + "','" + password + "','" + role +"')";

        //EXECUTES QUERY
        Statement stmt = con.createStatement();
        stmt.executeUpdate(QUERY);
        stmt.close();
        con.close();
    }*/

    /*public void testShowAllUsers() throws SQLException {
        //ESTABLISHES DBCONNECTION
        Connection con = getDBConnection();
        //DEFINES QUERY
        String QUERY = String.format("SELECT * FROM userid");
        //CREATES AND EXECUTES QUERY
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        //PRINTS RESULTS
        while (rs.next()) {
            //RETRIEVES VALUE BY COLUMN NAME
            System.out.print("\nNAME: " + rs.getString("firstname"));
            System.out.print(" " + rs.getString("lastname"));
            System.out.print(" | EMAIL: " + rs.getString("email"));
            System.out.print(" | PASSWORD: " + rs.getString("password"));
            System.out.print(" | ROLE: " + rs.getString("role"));
            System.out.println("\n");
        }
        stmt.close();
        con.close();
    }*/
}
