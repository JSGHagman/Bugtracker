package View;
import Controller.*;
import Model.Ticket;
import Model.TicketManager;
import Model.User;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.BasicPermission;
import java.sql.SQLException;
import java.util.ArrayList;

public class SouthPanel extends JPanel implements ActionListener {
    private JButton createTicket;
    private JPanel ticketInnerPanel1, ticketInnerPanel2;
    private CenterPanel c;
    private String[] stringList;
    private Controller controller;
    private DatabaseController dbController;
    private JTextField topic;
    private JTextArea comment;
    private JLabel topicLabel, commentLabel, currentUser, userName;
    private JButton exit;
    private JButton save;
    private User u, signedInUser;
    private TicketManager ticketManager;
    private Ticket ticket;

    public SouthPanel(CenterPanel c, Controller controller, int width, int height, int margin){
        this.controller = new Controller();
        this.c = c;
        setBorder(BorderFactory.createTitledBorder("Create new ticket"));
        Border border = this.getBorder();
        Border emptyBorder = BorderFactory.createEmptyBorder(margin, margin, margin, margin);
        setBorder(new CompoundBorder(border, emptyBorder));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));
        createImportantStuff();
        ticketManager = TicketManager.getInstance();

        try{
            this.dbController = new DatabaseController(controller);
            this.controller.getAllTickets();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setTickets(ticketManager.getAllTickets());
    }

    private void createImportantStuff() {

        ticketInnerPanel1 = new JPanel(new GridLayout(4, 1, 10, 10));
        ticketInnerPanel2 = new JPanel(new GridLayout(4, 1, 10, 10));
        topicLabel = new JLabel("TOPIC:  ", SwingConstants.LEFT);
        commentLabel = new JLabel("COMMENT:  ", SwingConstants.LEFT);
        currentUser = new JLabel("CURRENT USER:  ", SwingConstants.LEFT);
        userName = new JLabel();
        createTicket = new JButton("CREATE TICKET");
        topic = new JTextField();
        topic.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comment = new JTextArea();
        comment.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comment.setLineWrap(true);
        ticketInnerPanel1.add(topicLabel);
        ticketInnerPanel2.add(topic);
        ticketInnerPanel1.add(commentLabel);
        ticketInnerPanel2.add(comment);
        ticketInnerPanel1.add(currentUser);
        ticketInnerPanel2.add(userName);
        ticketInnerPanel1.add(createTicket);
        add(ticketInnerPanel1, BorderLayout.WEST);
        add(ticketInnerPanel2, BorderLayout.CENTER);
        createTicket.addActionListener(this);
    }

    public void setTickets(ArrayList <Ticket> list){
        stringList = new String [list.size()];
        for (int i = 0; i < list.size(); i++ ){
            stringList[i] = list.get(i).toString();
        }
        c.showTicketsInView(stringList);
    }

    public String getComment(){
        return comment.getText();
    }

    public String getTopic(){
        return topic.getText();
    }

    public void setUser(User user){
        userName.setText(user.toString());
        signedInUser = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createTicket) {
            User u = this.signedInUser;
            String topic = this.topic.getText();
            String comment = this.comment.getText();
            try {
                controller.newTicket(u, topic, comment);
                setTickets(ticketManager.getAllTickets());
                this.topic.setText("");
                this.comment.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
