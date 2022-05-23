
package View.MainView.MainFrame;
import Controller.Controller;
import View.MainView.Menu.MenuViewAdmin;
import View.MainView.Menu.MenuViewAgent;
import View.MainView.Menu.MenuViewUser;
import View.MainView.ProfileView.ProfileView;
import View.MainView.Tickets.TicketView;
import View.MainView.UserAdmin.UserAdminView;
import View.MainView.StaticsView.StatisticView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JFrame mainFrame;
    private JPanel menuPanel, contentPanel;
    private Color menuColor = new Color(65, 105, 225);
    private Controller controller;
    private TicketView ticketView;
    private MenuViewAdmin menuViewAdmin;
    private MenuViewAgent menuViewAgent;
    private MenuViewUser menuViewUser;
    private UserAdminView userAdminView;
    private ProfileView profileView;
    private StatisticView statisticView;

    public MainFrame(Controller controller){
        this.controller = controller;
        mainFrame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        mainFrame.setSize(screenWidth, screenHeight);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setTitle("Bugtracker");
        Image icon = Toolkit.getDefaultToolkit().getImage("Images/bugTrackerIcon.png");
        mainFrame.setIconImage(icon);
        mainFrame.setVisible(true);
        mainFrame.setResizable(true);
        setUpPanels();
        mainFrame.add(menuPanel);
        mainFrame.add(contentPanel);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ticketView = new TicketView(controller, this);
        profileView = new ProfileView(controller, this);


        switch (controller.getSignedInUser().getRole()) {
            case "User":
                menuViewUser = new MenuViewUser(controller, this);
                break;
            case "Admin":
                menuViewAdmin = new MenuViewAdmin(controller, this);
                break;
            case "Agent":
                menuViewAgent = new MenuViewAgent(controller, this);
                break;
            default:
                menuViewUser = new MenuViewUser(controller, this);
                break;
        }

    }

    public void setUpPanels(){
        menuPanel = new JPanel();
        menuPanel.setBounds(0,0, mainFrame.getWidth()/14, mainFrame.getHeight());
        menuPanel.setBackground(menuColor);
        menuPanel.setLayout(null);
        contentPanel = new JPanel();
        contentPanel.setBounds(menuPanel.getX() + menuPanel.getWidth(),0, (mainFrame.getWidth() - menuPanel.getWidth()), mainFrame.getHeight());
        contentPanel.setLayout(null);
    }

    public JPanel getMenuPanel(){
        return menuPanel;
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }

    public JFrame getFrame(){
        return mainFrame;
    }

    public void userAdminView(ArrayList users) {
        userAdminView = new UserAdminView(controller, this, users);
    }

    public void ticketView () {
        contentPanel.removeAll();
        ticketView.initializeTicketView();
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public TicketView getTicketView(){
        return ticketView;
    }

    public void profileView(){
        contentPanel.removeAll();
        profileView.CreateProfileView();
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void setUsertxtUserAdmin(String firstName, String lastName, String email, String password, String role) {
        userAdminView.setTxtFirstName(firstName);
        userAdminView.setTxtLastName(lastName);
        userAdminView.setTxtEmail(email);
        userAdminView.setPasswordtxt(password);
        userAdminView.setRole(role);
    }

    public void statisticsView() {
        statisticView = new StatisticView(this, controller);
    }


}
