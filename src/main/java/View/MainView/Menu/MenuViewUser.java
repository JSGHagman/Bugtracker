package View.MainView.Menu;
/**
 * @author Patrik Brandell
 * Class to show menu gui for Users
 */
import Controller.Controller;
import View.LogInView.LogInGUI;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuViewUser extends JComponent implements ActionListener {

    private Controller controller;
    private JPanel menuPanel;
    private MainFrame mainFrame;
    private int height;
    private int width;
    private JButton btnTickets, btnSettings, btnLogout;
    private Color menuColor = new Color(255, 255, 255);
    private Color hoverColor = new Color(65, 145, 225);


    public MenuViewUser(Controller controller, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        menuPanel = mainFrame.getMenuPanel();
        menuPanel.setLayout(new GridLayout(12, 1, 0, 30));
        setUp();
    }
    /**
     * Setup buttons
     */
    public void setUp() {
        btnTickets = new JButton("Tickets");
        setButtonDesign(btnTickets);
        menuPanel.add(btnTickets);


        btnSettings = new JButton("Profile Settings");
        setButtonDesign(btnSettings);
        menuPanel.add(btnSettings);

        btnLogout = new JButton("Log out");
        setButtonDesign(btnLogout);
        menuPanel.add(btnLogout, 0, 2);


    }
    /**
     *
     * @param btn JButton set design
     */
    private void setButtonDesign(JButton btn) {
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

    /**
     *
     * @param btn JButton add actionlistener
     */
    private void addActionListener(JButton btn) {
        btn.addActionListener(this);
    }

    /**
     *
     * @param e ButtonActions
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTickets) {
            controller.switchToTicket();
        }

        if (e.getSource() == btnSettings) {
            controller.switchToProfile();
        }

        if (e.getSource() == btnLogout) {
            mainFrame.getFrame().dispose();
            LogInGUI loginView = new LogInGUI(controller);

        }
    }
}
