package Model;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    /**
     * @author Jakob Hagman
     * Constructor
     * Will set any paramater that is null to an empty string
     * @param firstName
     * @param lastName
     * @param password
     * @param email
     * @param role
     */
    public User(String firstName, String lastName, String password, String email, String role){
        if(firstName.equals(null)){
            this.firstName = "";
        } else{
            this.firstName = firstName;
        }

        if(lastName.equals(null)){
            this.lastName = "";
        }else{
            this.lastName = lastName;
        }

        if(password.equals(null)){
            this.password = "";
        }else{
            this.password = password;
        }

        if(email.equals(null)){
            this.email = "";
        }else{
            this.email = email;
        }

        if(role.equals(null)){
            this.role = null;
        }else{
            this.role = role;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        return String.format("NAME: %s %s\nEMAIL: %s\nPASSWORD: %s\nROLE: %s", this.firstName, this.lastName, this.email, this.password, this.role);
    }
}
