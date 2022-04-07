package Controller;
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

    public DatabaseController() throws SQLException {
       menu();
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

    public void menu() throws SQLException {
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
    }

    public void testAddUser() throws SQLException {
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
        con.close();
    }

    public void testShowAllUsers() throws SQLException {
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
    }

    public static void main(String[] args) throws SQLException {
        new DatabaseController();
    }
}
