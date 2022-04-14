package Controller;

import Model.Ticket;
import Model.User;

import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class DatabaseController {

    private final String url = "jdbc:postgresql://pgserver.mau.se:5432/bugtracker";
    private final String user = "am4032";
    private final String password = "krhi9kxm";
    private Ticket ticket;
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
                "VALUES ('" + user.getUsername() + "','" + user.getUsername() + "','" + user.getEmail() + "','" + user.getPassword() + "','User')";

        //EXECUTES QUERY
        Statement stmt = con.createStatement();
        stmt.executeUpdate(QUERY);
        con.close();
    }

    /**
     * @author Jakob Hagman
     * Method used to get all registered users from the database when the application starts
     * @return ArrayList of userobjects
     * @throws Exception
    */
    public void getAllUsers() throws SQLException {
    Connection con = getDBConnection();
    String QUERY = String.format("SELECT FROM userid");
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

    /**
     * aAuthor Patrik Brandell
     * @return int id of last ticket made
     * @throws SQLException
     *
     */
    public int newTicket() throws SQLException {
        int id;
        Connection con = getDBConnection();
        //Create empty ticket
        String QUERY = "INSERT INTO ticket(id) VALUES (default)";
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
            int id = rs.getInt("id");
            int priority = rs.getInt("priority");
            String category = rs.getString("category");
            String status = rs.getString("status");
            String file = rs.getString("files");
            String time = rs.getString("time");
            Date startdate = rs.getDate("dateopen");
            Date enddate = rs.getDate("dateclose");

            ticket = new Ticket(id, category, status, priority, startdate, enddate, file);
            list.add(ticket);

        }
        stmt.close();
        con.close();

        return list;
    }



}
