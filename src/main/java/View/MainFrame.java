package View;
import javax.swing.*;
import Controller.Controller;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private Controller controller;


    public MainFrame(int width, int height, Controller controller) {
    this.controller = controller;
        this.setResizable(false);
        this.setSize(width, height);
        this.mainPanel = new MainPanel(width, height, controller);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void setCurrUser(String username) {
        mainPanel.setCurrUser(username);
    }
    public String getTopic() {
        return mainPanel.getTopic().getText();
    }
    public String getComment() {
        return mainPanel.getComment().getText();
    }
}