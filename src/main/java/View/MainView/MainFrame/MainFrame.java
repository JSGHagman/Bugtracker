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
 */
package View.MainView.MainFrame;
import Controller.Controller;
import View.MainView.Tickets.TicketView;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JFrame mainFrame;
    private JPanel menuPanel, contentPanel;
    private Color menuColor = new Color(65, 105, 225);
    private Controller controller;
    private TicketView ticketView;

    public MainFrame(Controller controller){
        this.controller = controller;
        mainFrame = new JFrame();
        mainFrame.setSize(1650,1080);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setTitle("Bugtracker");
        Image icon = Toolkit.getDefaultToolkit().getImage("Images/bugTrackerIcon.png");
        mainFrame.setIconImage(icon);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        setUpPanels();
        mainFrame.add(menuPanel);
        mainFrame.add(contentPanel);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ticketView = new TicketView(controller,this);
    }

    public void setUpPanels(){
        menuPanel = new JPanel();
        menuPanel.setBounds(0,0,100, mainFrame.getHeight());
        menuPanel.setBackground(menuColor);
        menuPanel.setLayout(null);
        contentPanel = new JPanel();
        contentPanel.setBounds(20,0, (mainFrame.getWidth() - 100), mainFrame.getHeight());
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




}
