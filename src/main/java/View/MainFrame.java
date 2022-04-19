package View;

import Controller.Controller;

import javax.swing.*;

public class MainFrame extends JFrame {
    private int width = 600;
    private int height = 600;
    private MainPanel mainPanel;
    private Controller controller;

    public MainFrame(Controller controller){
        setUpframe();
        this.controller = controller;
    }

    public void setUpframe(){
        final int offsetX = width/5;
        final int offsetY = height/5;

        setSize(width, height);
        setTitle("Ticket Window");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(offsetX, offsetY);

        mainPanel = new MainPanel(controller ,width, height);
        setContentPane(mainPanel);
        setResizable(false);
        pack();
        setVisible(true);
    }

    public void setCurrUser(String username) {
        mainPanel.getSouthPanel().setUser(username);
    }
    public String getTopic() {
        return mainPanel.getSouthPanel().getTopic();
    }
    public String getComment() {
        return mainPanel.getSouthPanel().getComment();
    }

    public static void main(String[] args) {
        new MainFrame(new Controller());
    }
}
