package Model;

import java.util.ArrayList;


public class UserManager {


    private final ArrayList<User> users;


    public UserManager() {
        users = new ArrayList<>();

    }

    public void addToUsers(User user) {
        users.add(user);
    }

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
