package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.BasicPermission;
import java.util.ArrayList;


public class SouthPanel extends JPanel implements ActionListener {

    private JButton createTicket;
    private JPanel ticketInnerPanel1, ticketInnerPanel2;
    private CenterPanel c;
    private String[] stringList;
    private Controller controller;
    private JTextField topic;
    private JTextArea comment;
    private JLabel topicLabel, commentLabel, currentUser, userName;
    private JButton exit;
    private JButton save;

    public SouthPanel(CenterPanel c, Controller controller, int width, int height, int margin) {
        this.controller = controller;
        this.c = c;
        setBorder(BorderFactory.createTitledBorder("Create new ticket"));
        Border border = this.getBorder();
        Border emptyBorder = BorderFactory.createEmptyBorder(margin, margin, margin, margin);
        setBorder(new CompoundBorder(border, emptyBorder));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));
        createImportantStuff();
    }
    private void createImportantStuff() {
        //SKAPAR TVÅ PANELER FÖR BOXAR OCH SÖKKNAPP
        ticketInnerPanel1 = new JPanel(new GridLayout(4, 1, 10, 10));
        ticketInnerPanel2 = new JPanel(new GridLayout(4, 1, 10, 10));

        //STRÄNGAR SOM SÄTTS SOM DEFAULTVÄRDEN I COMBOBOXARNA

        //LITE LABELS FÖR TYDLIGHET
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

        // LÄGGER TILL COMBOBOXAR OCH LABEL I PANEL
        ticketInnerPanel1.add(topicLabel);
        ticketInnerPanel2.add(topic);
        ticketInnerPanel1.add(commentLabel);
        ticketInnerPanel2.add(comment);
        ticketInnerPanel1.add(currentUser);
        ticketInnerPanel2.add(userName);
        ticketInnerPanel1.add(createTicket);

        //LÄGGER TILL PANELERNA PÅ HUVUDPANELEN
        add(ticketInnerPanel1, BorderLayout.WEST);
        add(ticketInnerPanel2, BorderLayout.CENTER);

        //LÄGGER TILL LYSSNARE FÖR KNAPPEN
        createTicket.addActionListener(this);
    }

    //LÄGGER TILL VALDA LOGGAR I EN ARRAY OCH PLACERAR I CENTERPANEL
    public void setTickets(ArrayList <String> list){
        stringList = new String [list.size()];
        for (int i = 0; i < list.size(); i++ ){
            stringList[i] = list.get(i);
        }
        c.showTicketsInView(stringList);
    }

    public String getComment(){
        return comment.getText();
    }

    public String getTopic(){
        return topic.getText();
    }

    public void setUser(String user){
        userName.setText(user);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createTicket) {

        }
    }
}
