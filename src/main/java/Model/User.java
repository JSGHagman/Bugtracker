package Model;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public User( String firstName, String lastName, String password, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
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
        return String.format("Name: %s %s\n EMAIL: %s\nPASSWORD: %s", this.firstName, this.lastName,
                this.email, this.password);
    }
}
