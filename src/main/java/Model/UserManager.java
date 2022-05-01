package Model;
import java.util.ArrayList;
public class UserManager {
    private final ArrayList<User> users;
    private User signedInUser;
    private static UserManager instance = new UserManager();

    private UserManager() {
        users = new ArrayList<>();
    }

    public static UserManager getInstance(){
        return instance;
    }

    public void printUserList(){
        for(User u : users){
            System.out.println(u.toString());
        }
    }
    /**
     * This method adds a new user to an ArrayList of users.
     * @param user
     */
    public void addToUsers(User user) {
        users.add(user);
    }

    /**
     * This method will later be used to check the list of users when
     * a user attempts to sign in.
     * Will return true if email and password matches that of a user in the list.
     * @param email
     * @param password
     * @return found
     * */
    public boolean checkPassword(String email, String password) {
        boolean found = false;
        for (User u : users) {
            if (u.getPassword().equals(password) && u.getEmail().equals(email)) {
                found = true;
                setSignedInUser(u);
            }
        }
        return found;
    }

    /**
     * This method is used when a user wants to create an account
     * Checks if a user with an email already exists
     * @param email
     * @return
     */
    public boolean checkIfUserExists(String email){
        boolean exists = false;
        for(User u : users){
            if(u.getEmail().equals(email)){
                exists = true;
            }
        }
        return exists;
    }

    public void setSignedInUser(User signedInUser) {
        this.signedInUser = signedInUser;
    }

    public User getSignedInUser() {
        return signedInUser;
    }

    public ArrayList<User> getAllUsers(){
        return users;
    }

    public User getUserAtIndex (int index) {
        if (users.get(index) != null) {
            return users.get(index);
        }
        else {
            return getSignedInUser();
        }
    }

    public ArrayList<String> infoStrings() {
        ArrayList<String> infoStrings = new ArrayList<>();
        for (User u : users) {
            infoStrings.add(String.format("%-50s %30s %70s",u.toString(), u.getEmail(), u.getRole()));

        }
        return infoStrings;
    }

}
