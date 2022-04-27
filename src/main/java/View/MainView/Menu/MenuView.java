package View.MainView.Menu;

import Controller.Controller;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JComponent {
    private Controller controller;
    private JPanel menuPanel;
    private MainFrame mainFrame;
    private int height;
    private int width;
    private JButton tickets, statistics, settings, logout;


    public MenuView (MainFrame mainFrame,Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        menuPanel = mainFrame.getMenuPanel();
        menuPanel.setLayout(new GridLayout(4,1,0,10));
        setUp();
    }

    public void setUp() {
        tickets = new JButton("Tickets");
        menuPanel.add(tickets);

        statistics = new JButton("Statistics");
        menuPanel.add(statistics);

        settings = new JButton("Settings");
        menuPanel.add(settings);

        logout = new JButton("Log out");
        menuPanel.add(logout);
        


    }
}



