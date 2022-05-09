/**
 * This class is used for creating the mainframe for our makeshift create ticket view.
 * @author Jakob Hagman
 */

package View.OldTicketGui;

import Controller.Controller;
import Model.TicketManager;
import Model.User;
import Model.UserManager;

import javax.swing.*;

public class MainFrame extends JFrame {
    private int width = 600;
    private int height = 600;
    private MainPanel mainPanel;
    private Controller controller;
    private UserManager userManager;
    private TicketManager ticketManager;

    public MainFrame(Controller controller){
        setUpframe();
        this.controller = controller;
        this.userManager = UserManager.getInstance();
        setCurrUser(userManager.getSignedInUser());
        this.ticketManager = TicketManager.getInstance();
    }

    public void setUpframe() {
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

    public void setCurrUser(User user) {
        mainPanel.getSouthPanel().setUser(user);
    }

    public String getTopic() {
        return mainPanel.getSouthPanel().getTopic();
    }
    public String getComment() {
        return mainPanel.getSouthPanel().getComment();
    }
}
