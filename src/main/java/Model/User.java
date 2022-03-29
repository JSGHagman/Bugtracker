package Model;

public class User {
    private String userName, passWord;

    public User(String userName, String passWord){
        this.setUserName(userName);
        this.setPassWord(passWord);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
