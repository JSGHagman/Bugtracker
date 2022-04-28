package View.MainView.Menu;

import Controller.Controller;
import View.LogInView.LogInGUI;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuView extends JComponent implements ActionListener {
    private Controller controller;
    private JPanel menuPanel;
    private MainFrame mainFrame;
    private int height;
    private int width;
    private JButton btnTickets, btnStatistics, btnSettings, btnLogout;
    private Color menuColor = new Color(255, 255, 255);
    private Color hoverColor = new Color(65,145,225);


    public MenuView (Controller controller, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        menuPanel = mainFrame.getMenuPanel();
        menuPanel.setLayout(new GridLayout(12,1,0,30));
        setUp();
    }

    public void setUp() {
        btnTickets = new JButton("Tickets");
        setButtonDesign(btnTickets);
        menuPanel.add(btnTickets);

        btnStatistics = new JButton("Statistics");
        setButtonDesign(btnStatistics);
        menuPanel.add(btnStatistics);

        btnSettings = new JButton("Settings");
        setButtonDesign(btnSettings);
        menuPanel.add(btnSettings);

        btnLogout = new JButton("Log out");
        setButtonDesign(btnLogout);
        menuPanel.add(btnLogout,0,3);



    }

    private void setButtonDesign(JButton btn){
        btn.setBackground(menuColor);
        btn.setForeground(Color.BLUE);
        addActionListener(btn);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent evt) {
                btn.setBackground(menuColor);
            }
        });
    }

    private void addActionListener(JButton btn){
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTickets) {
            controller.switchToTicket();
        }

        if (e.getSource() == btnSettings) {
            controller.switchToSettings();
        }

        if (e.getSource() == btnStatistics) {
            controller.switchToStatistics();
        }

        if (e.getSource() == btnLogout) {
            mainFrame.getFrame().dispose();
            LogInGUI loginView = new LogInGUI(controller);

        }
    }
}



