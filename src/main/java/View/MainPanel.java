package View;

import Controller.Controller;
import Model.Ticket;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JTextField topic;
    private JTextArea comment;
    private JLabel currUser;
    private JButton exit;
    private JButton save;
    private Controller controller;



    public MainPanel(int width, int height, Controller controller) {
        super(new GridLayout(10, 1));
        this.controller = controller;
        this.setSize(width, height);
        setUp();
    }

    public void setUp () {
        topic = new JTextField("Topic");
        topic.setVisible(true);
        add(topic);

        comment = new JTextArea("Beskriv ärendet");
        add(comment);

        currUser = new JLabel("Current user");
        add(currUser);

        exit = new JButton("EXIT");
        add(exit);

        save = new JButton("Spara ärende");
        save.addActionListener(l -> {
            try {
                controller.updateTicket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        add(save);


    }

    public JTextField getTopic() {
        return topic;
    }

    public void setTopic(JTextField topic) {
        this.topic = topic;
    }

    public JTextArea getComment() {
        return comment;
    }

    public void setComment(JTextArea comment) {
        this.comment = comment;
    }

    public JLabel getCurrUser() {
        return currUser;
    }

    public void setCurrUser(String currUser) {
        this.currUser.setText(currUser);
    }
}
