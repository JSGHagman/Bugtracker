package Model;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;

public class Ticket {

    private String topic;
    private ArrayList<String> comment = new ArrayList<>();
    private int id;
    private String category;
    private String status;
    private int priority;
    private ArrayList<User> agent = new ArrayList<>();
    private User user;
    private int time;
    private Date startdate;
    private Date enddate;
    private String file;
    private String description;

    public Ticket (User user) {
        this.user = user;
    }

    public Ticket (int id, User user, String topic, String description) {
        this.id = id;
        this.user = user;
        this.topic = topic;
        this.description = description;
        startdate = new Date();
    }

    public Ticket (int id, String category, String status, int priority, Date startdate, Date enddate, String file, String topic) {
        this.id = id;
        this.category = category;
        this.status = status;
        this.priority = priority;
        this.startdate = startdate;
        this.enddate = enddate;
        this.file = file;
        this.topic = topic;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<String> getComment() {
        return comment;
    }

    public void setComment(ArrayList<String> comment) {
        this.comment = comment;
    }

    public void setComment (String comment) {
        this.comment.add(comment);
    }

    public void setAgent(ArrayList<User> agent) {
        this.agent = agent;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | OWNER: %s | TOPIC: %s | COMMENTS: %s", this.id, this.user, this.topic, this.comment);
    }
}
