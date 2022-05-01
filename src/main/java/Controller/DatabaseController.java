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

    public DatabaseController(Controller controller) {
        this.controller = controller;
    }

    public Connection getDBConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("CONNECTION ESTABLISHED");
            return con;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }

    public void addNormalUser(User user) throws SQLException {
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

    public void updateUser(User user) throws SQLException {
        Connection con = getDBConnection();
        String QUERY = "UPDATE userid " +
                "SET firstname = " + fixSQLString(user.getFirstName()) +
                ", lastname = " + fixSQLString(user.getLastName()) +
                ", password = " + fixSQLString(user.getPassword()) +
                ", role = " + fixSQLString(user.getRole()) +
                "WHERE email = " + fixSQLString(user.getEmail());

        Statement stmt = con.createStatement();
        stmt.executeUpdate(QUERY);
        con.close();
        controller.updateUserManager(user);
    }

    /**
     * @return ArrayList of userobjects
     * @throws Exception
     * @author Jakob Hagman
     * Method used to get all registered users from the database when the application starts
     */
    public void getAllUsers() throws SQLException {
        Connection con = getDBConnection();
        String QUERY = String.format("SELECT * FROM userid");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            User u = null;
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
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
     * @return int id of last ticket made
     * @throws SQLException
     * @author Patrik Brandell
     */
    public int newTicket() throws SQLException {
        int id = 0;
        Connection con = getDBConnection();
        //Create empty ticket
        String QUERY = "INSERT INTO ticket(id) VALUES (default)";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(QUERY);
        //Get ID from ticket
        QUERY = "SELECT id from ticket ORDER by id DESC limit 1";
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            id = Integer.parseInt(rs.getObject(1).toString());
        }
        stmt.close();
        con.close();

        return id;
    }

    /**
     * @param ticket - Ticket object
     * @throws SQLException Updates ticket in db with information from ticket object
     * @author Patrik Brandell
     */
    public void updateTicket(Ticket ticket) throws SQLException {
        Connection con = getDBConnection();
        String QUERY = "UPDATE ticket SET priority =" + ticket.getPriority() + ", category =" + fixSQLString(ticket.getCategory()) +
                ", status =" + fixSQLString(ticket.getStatus()) + ", files =" + fixSQLString(ticket.getFile()) + ", time =" + ticket.getTime() +
                ", dateopen =" + fixSQLDate(ticket.getStartdate()) + ", dateclose =" + fixSQLDate(ticket.getEnddate()) +
                ", topic = " + fixSQLString(ticket.getTopic()) + " WHERE id = " + ticket.getId();

        Statement stmt = con.createStatement();
        stmt.executeUpdate(QUERY);
        stmt.close();
        con.close();
        updateTicketComments(ticket);
    }

    /**
     * @param ticket current ticket
     * @throws SQLException writes comments in table ticketcomments with a new id. Can connect to ticket through ticketId
     * @author Patrik Brandell
     */
    public void updateTicketComments(Ticket ticket) throws SQLException {
        Connection con = getDBConnection();
        Statement stmt = con.createStatement();
        for (String str : ticket.getComment()) {
            String QUERY = "INSERT INTO ticketcomments (id, ticketid, comment) VALUES (default," + ticket.getId() + ", " + fixSQLString(str) + ")";
            stmt.executeUpdate(QUERY);
        }
        stmt.close();
        con.close();
    }

    /**
     * @return ArrayList of all current tickets in db
     * @throws Exception
     * @author Patrik Brandell
     */
    public ArrayList getAllTickets() throws Exception {
        ArrayList list = new ArrayList<Ticket>();
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

    /**
     * @param str or null
     * @return string or null which can be used in SQLQuery
     * @author Patrik Brandell
     */
    public String fixSQLString(String str) {
        boolean isNull = str == null;
        if (!isNull) {
            str = "'" + str + "'";
            return str;
        } else {
            return null;
        }
    }

    /**
     * @param date or null
     * @return date or null which can be used in SQLQuery
     * @author Patrik Brandell
     */
    public String fixSQLDate(Date date) {
        boolean isNull = date == null;
        if (!isNull) {
            String strdate = "'" + date.toString() + "'";
            return strdate;
        } else {
            return null;
        }
    }


}
