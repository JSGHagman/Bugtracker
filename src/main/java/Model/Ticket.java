package Model;
/**
 * @author Jakob Hagman, Patrik Brandell
 * This class contains the Ticket object
 */

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ticket {

    private String topic;
    private ArrayList<String> comments = new ArrayList<>();
    private ArrayList<String> assigneesStrings = new ArrayList<>();
    private int id;
    private String category;
    private String status;
    private int priority;
    private ArrayList<User> agent = new ArrayList<>();
    private User owner;
    private int time;
    private Date startdate;
    private Date enddate;
    private ArrayList<String> files = new ArrayList<>();
    private String description;
    private String[] infoStrings;

    /**
     *
     * @param owner Owner of ticket
     *              Minimum constructor
     */

    public Ticket(User owner) {
        this.owner = owner;
    }

    public Ticket(int id, User owner, String topic, String description) {
        this.id = id;
        this.owner = owner;
        this.topic = topic;
        this.description = description;
        startdate = new Date();
    }

    /**
     *
     * @param id int Ticked ID
     * @param category String Category
     * @param status String Status
     * @param priority int priority
     * @param startdate Date startdate
     * @param enddate Date enddate
     * @param topic String topic
     */
    public Ticket(int id, String category, String status, int priority, Date startdate, Date enddate, String topic) {
        this.id = id;
        this.category = category;
        this.status = status;
        this.priority = priority;
        this.startdate = startdate;
        this.enddate = enddate;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public void setComment(String comment) {
        this.comments.add(comment);
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

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFile(ArrayList<File> file) {
        this.files = files;
    }

    public void addFile(String file) {
        files.add(file);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return String.format("ID: %s | OWNER: %s | TOPIC: %s | COMMENTS: %s", this.id, this.owner, this.topic, this.comments);
    }

    /**
     * Returns the priority of the ticket as a string
     *
     * @return
     */
    public String getPriorityAsString() {
        String priority = "";
        if (this.priority == 1) {
            priority = "High";
        }
        if (this.priority == 2) {
            priority = "Medium";
        }
        if (this.priority == 3) {
            priority = "Low";
        }
        return priority;
    }

    public ArrayList<String> getAgentsAsStrings() {
        ArrayList<String> agentStrings = new ArrayList<>();
        for (User u : agent) {
            agentStrings.add(u.toString());
        }
        return agentStrings;
    }

    public String[] getCommentsAsStringList() {
        return infoStrings;
    }

    public void setCommentsList() {
        new commentsThread().start();
    }

    class commentsThread extends Thread {
        @Override
        public void run() {
            infoStrings = new String[comments.size()];
            for (int i = 0; i < comments.size(); i++) {
                infoStrings[i] = comments.get(i).toString();
            }
        }
    }

}
