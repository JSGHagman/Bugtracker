package Model;

public class User {
    private String username;
    private String email;
    private String password;


    public User(String userName, String password, String email){
        this.username = userName;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return String.format("USERNAME: %s\nEMAIL: %s\nPASSWORD: %s", this.username, this.email, this.password);
    }
}
