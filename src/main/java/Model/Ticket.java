package Model;

import java.util.ArrayList;

public class Ticket {

    private int id;
    private String category;
    private String status;
    private int priority;
    private ArrayList<User> agent = new ArrayList<>();
    private User user;
    private int time;


    public Ticket (User user) {
        this.user = user;
    }

    public void addAgent(User agent) {
        this.agent.add(agent);
    }

    public void removeAgent(User agent) {
        this.agent.remove(agent);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ArrayList<User> getAgent() {
        return agent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
