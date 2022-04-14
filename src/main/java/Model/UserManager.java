package Model;

import java.util.ArrayList;


public class UserManager {


    private final ArrayList<User> users;


    public UserManager() {
        users = new ArrayList<>();
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
            }
        }
        return found;
    }

}
