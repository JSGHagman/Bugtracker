package View.MainView.Menu;

import Controller.Controller;
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
    private JButton tickets, statistics, settings, logout;
    private Color menuColor = new Color(255, 255, 255);
    private Color hoverColor = new Color(65,145,225);


    public MenuView (MainFrame mainFrame,Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        menuPanel = mainFrame.getMenuPanel();
        menuPanel.setLayout(new GridLayout(12,1,0,30));
        setUp();
    }

    public void setUp() {
        tickets = new JButton("Tickets");
        setButtonDesign(tickets);
        menuPanel.add(tickets);

        statistics = new JButton("Statistics");
        setButtonDesign(statistics);
        menuPanel.add(statistics);

        settings = new JButton("Settings");
        setButtonDesign(settings);
        menuPanel.add(settings);

        logout = new JButton("Log out");
        setButtonDesign(logout);
        menuPanel.add(logout,0,3);



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

    }
}



