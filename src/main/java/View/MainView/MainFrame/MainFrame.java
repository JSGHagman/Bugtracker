/**
 * Observera att layout för panelerna är null, och i min mening bör de vara null
 * för mer precis kontroll om vart panelerna placeras använd setBounds();
 * Tips är att ha en unik färg på panelerna när man försöker placera ut den så man ser vart den hamnar.
 * layout på panelerna som placeras ovanpå grundpanelerna behöver inte vara null, det är vad som passar för dess ändamål.
 * Menypanelen bör styras här ifrån men beroende på vilket val man väljer i menyn målas töms contentpanelen och
 * målas om med en instans av klasser i de andra paketen.
 * När man instansierar dessa bör man skicka med MainFrame Objektet och Controller Objektet som parametrar i konstruktorerna.
 * Får tillgång till contentpanelen eller menypanelen från getters i MainFrame Objektet
 * Att göra:
 * Placera ut knappar i menyn
 * Börja med view för att tickets
 * Börja med view för profile
 * Börja med view för Settings
 *
 */
package View.MainView.MainFrame;
import Controller.Controller;
import View.MainView.Menu.MenuViewAdmin;
import View.MainView.Menu.MenuViewAgent;
import View.MainView.Menu.MenuViewUser;
import View.MainView.Tickets.TicketView;
import View.MainView.UserAdmin.UserAdminView;

import javax.swing.*;
import java.awt.*;

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
        System.out.println(menuPanel.getWidth());
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

    public void userAdminView(String[] users) {
        userAdminView = new UserAdminView(controller, this);
        userAdminView.setUserList(users);

    }

    public void ticketView () {
        ticketView.initializeTicketView();
    }

    public void setUsertxtUserAdmin(String firstName, String lastName, String email, String password, String role) {
        userAdminView.setTxtFirstName(firstName);
        userAdminView.setTxtLastName(lastName);
        userAdminView.setTxtEmail(email);
        userAdminView.setPasswordtxt(password);
        userAdminView.setRole(role);

    }


}
