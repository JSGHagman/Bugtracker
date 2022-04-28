
/**
 * @todo
 * Kunna sätta kategori, (Issue, Bug, New Feature Request)
 * Kunna sätta prioritet (Low, Medium, High)
 * Kunna sätta Ägare (Endast möjligt att sätta sig själv om man är agent, kan sätta vem som helst om man ör admin, kan inte sätta alls om man bara är user)
 * HÄMTA ALLA ANVÄNDARE OCH LÄGG I COMBOBOX
 * Lös claimedstatus,
 *
 *
 *
 */

package View.MainView.Tickets;
import Controller.Controller;
import View.MainView.MainFrame.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TicketView extends JComponent implements ActionListener{
    private Controller controller;
    private MainFrame mainFrame;
    private JButton btnNewTicket, btnAllTickets, btnMyTickets, btnEditTicket, btnCreateTicket, btnCreateReturn, btnEditReturn, btnSaveChanges, claimTicket, btnAddCollaborator, btnRemoveCollaborator;
    private JPanel mainContentPanel, mainCreatePanel, mainTicketsPanel, mainEditPanel, mainCommentPanel, mainButtonsPanel, currentPanelOnDisplay;
    private Color menuColor = new Color(65, 105, 225);
    private Color hoverColor = new Color(65,145,225);
    private JTextField topicField;
    private JTextArea descriptionText, comment;
    private JLabel topicLabel, descriptionLabel, priorityLabel, categoryLabel, ownerLabel, collaboratorLabel, assigneesLabel, assigneesListLabel;
    private JScrollPane descriptionScroll;
    private JComboBox priorityBox, categoryBox, ownerBox, collaboratorsBox;

    public TicketView(Controller controller, MainFrame mainFrame){
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.mainContentPanel = mainFrame.getContentPanel();
        createButtons();
        createLabels();
        createInputFields();
        createPanels();
        initializeTicketView();
    }

    private void createButtons(){
        //CREATE BUTTONS
        btnMyTickets = new JButton("My Tickets");
        setButtonDesign(btnMyTickets);
        btnAllTickets = new JButton("All Tickets");
        setButtonDesign(btnAllTickets);
        btnNewTicket = new JButton("New Ticket");
        setButtonDesign(btnNewTicket);
        btnEditTicket = new JButton("Edit Ticket");
        setButtonDesign(btnEditTicket);
        btnCreateReturn = new JButton("Return");
        setButtonDesign(btnCreateReturn);
        btnEditReturn = new JButton("Return");
        setButtonDesign(btnEditReturn);
        btnCreateTicket = new JButton("Create Ticket");
        setButtonDesign(btnCreateTicket);
        btnSaveChanges = new JButton("Save Changes");
        setButtonDesign(btnSaveChanges);
        btnAddCollaborator = new JButton("Add");
        setButtonDesign(btnAddCollaborator);
        btnRemoveCollaborator = new JButton("Remove");
        setButtonDesign(btnRemoveCollaborator);
    }

    private void setButtonDesign(JButton btn){
        btn.setBackground(menuColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Dialog", Font.BOLD, 20));
        addActionListener(btn);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent evt) {
                btn.setBackground(menuColor);
            }

            public void mousePressed(MouseEvent evt){
                btn.setBackground(menuColor);
            }
        });
    }


    private void createLabels(){
        //FOR CREATE VIEW
        topicLabel = new JLabel("Topic");
        topicLabel.setForeground(menuColor);
        topicLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setForeground(menuColor);
        descriptionLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        priorityLabel = new JLabel("Priority");
        priorityLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        priorityLabel.setForeground(menuColor);

        categoryLabel = new JLabel("Type");
        categoryLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        categoryLabel.setForeground(menuColor);

        ownerLabel = new JLabel("Owner");
        ownerLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        ownerLabel.setForeground(menuColor);

        collaboratorLabel = new JLabel("Add Collaborators");
        collaboratorLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        collaboratorLabel.setForeground(menuColor);

        assigneesLabel = new JLabel("Assignees");
        assigneesLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        assigneesLabel.setForeground(menuColor);

        assigneesListLabel = new JLabel();
        assigneesListLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        assigneesListLabel.setText("<html>This label will be updated for every added collaborator<br/>Owner: Viktor<br/>Collaborators: Yara, Jakob, Patrik</html>");


        //FOR EDIT VIEW

    }

    private void createInputFields(){
        //FOR CREATE VIEW
        topicField = new JTextField();
        topicField.setFont(new Font("Dialog", Font.PLAIN, 16));
        topicField.setText(" Enter topic");
        topicField.setBorder(BorderFactory.createLineBorder(menuColor, 3, false));

        descriptionText = new JTextArea();
        descriptionText.setFont(new Font("Dialog", Font.PLAIN, 16));
        descriptionText.setText(" Describe the issue");
        descriptionText.setEditable(true);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);

        descriptionScroll = new JScrollPane(descriptionText);
        descriptionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScroll.setBorder(BorderFactory.createLineBorder(menuColor, 3, false));

        priorityBox = new JComboBox();
        priorityBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        priorityBox.setBackground(Color.WHITE);
        priorityBox.addItem("Low");
        priorityBox.addItem("Medium");
        priorityBox.addItem("High");
        priorityBox.setSelectedItem("Low");

        categoryBox = new JComboBox();
        categoryBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        categoryBox.setBackground(Color.WHITE);
        categoryBox.addItem("Issue");
        categoryBox.addItem("Bug");
        categoryBox.addItem("New feature request");
        categoryBox.setSelectedItem("Issue");

        ownerBox = new JComboBox();
        ownerBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        ownerBox.setBackground(Color.WHITE);
        ownerBox.addItem("None");
        ownerBox.addItem("You");
        ownerBox.setSelectedItem("None");

        collaboratorsBox = new JComboBox();
        collaboratorsBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        collaboratorsBox.setBackground(Color.WHITE);
        collaboratorsBox.addItem("None");
        collaboratorsBox.setSelectedItem("None");

        //FOR EDIT VIEW
        
    }

    private void createPanels(){
        //CREATE PANELS
        mainTicketsPanel = new JPanel();
        mainTicketsPanel.setBounds(150,100, 1200, 900);
        mainTicketsPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));

        mainCreatePanel = new JPanel();
        mainCreatePanel.setBounds(150,100, 1200, 900);
        //mainCreatePanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        mainCreatePanel.setLayout(null);
        setCreatePanelDetails();

        mainEditPanel = new JPanel();
        mainEditPanel.setBounds(150,100, 1200, 900);
        mainEditPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        mainEditPanel.setLayout(null);
        setEditPanelDetails();

        mainCommentPanel = new JPanel();
        mainCommentPanel.setBounds(1370,100, 500, 900);
        mainCommentPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));

        mainButtonsPanel = new JPanel();
        mainButtonsPanel.setBounds(150, 15, 800, 75);
        mainButtonsPanel.setLayout(new GridLayout(1,4,10,10));

        mainButtonsPanel.add(btnMyTickets);
        mainButtonsPanel.add(btnAllTickets);
        mainButtonsPanel.add(btnNewTicket);
        mainButtonsPanel.add(btnEditTicket);
    }

    private void setEditPanelDetails() {
        JPanel innerButtonPanelEdit = new JPanel();
        innerButtonPanelEdit.setBounds(0,760, 350, 75);
        innerButtonPanelEdit.setLayout(new GridLayout(1,1,10,10));
        innerButtonPanelEdit.add(btnSaveChanges);
        innerButtonPanelEdit.add(btnEditReturn);
        mainEditPanel.add(innerButtonPanelEdit);
    }

    private void setCreatePanelDetails() {
        JPanel innerButtonPanel = new JPanel();
        innerButtonPanel.setBounds(0,760, 350, 75);
        innerButtonPanel.setLayout(new GridLayout(1,3,10,10));
        innerButtonPanel.add(btnCreateTicket);
        innerButtonPanel.add(btnCreateReturn);

        JPanel innerTopicPanel = new JPanel();
        innerTopicPanel.setBounds(0,10,600, 120);
        innerTopicPanel.setLayout(new GridLayout(3,1));
        innerTopicPanel.add(topicLabel);
        innerTopicPanel.add(topicField);
        innerTopicPanel.add(descriptionLabel);

        JPanel innerDescriptionPanel = new JPanel();
        innerDescriptionPanel.setBounds(0,130,600,200);
        innerDescriptionPanel.setLayout(new GridLayout());
        innerDescriptionPanel.add(descriptionScroll);

        JPanel innerChoicesPanel = new JPanel();
        innerChoicesPanel.setLayout(new GridLayout(4,1,10,10));
        innerChoicesPanel.setBounds(0, 325, 280, 200);
        //innerChoicesPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        innerChoicesPanel.add(priorityLabel);
        innerChoicesPanel.add(priorityBox);
        innerChoicesPanel.add(categoryLabel);
        innerChoicesPanel.add(categoryBox);

        JPanel innerPeoplePanel = new JPanel();
        innerPeoplePanel.setLayout(new GridLayout(4,1,10,10));
        innerPeoplePanel.setBounds(320, 325, 280,200);
        innerPeoplePanel.add(ownerLabel);
        innerPeoplePanel.add(ownerBox);
        innerPeoplePanel.add(collaboratorLabel);
        innerPeoplePanel.add(collaboratorsBox);

        JPanel innerCollaboratorButtonsPanel = new JPanel();
        innerCollaboratorButtonsPanel.setLayout(new GridLayout(1,2,10,10));
        innerCollaboratorButtonsPanel.setBounds(320, 530, 280,35);
        innerCollaboratorButtonsPanel.add(btnAddCollaborator);
        innerCollaboratorButtonsPanel.add(btnRemoveCollaborator);

        JPanel innerAssigneesPanel = new JPanel();
        innerAssigneesPanel.setBounds(0, 600, 200,50);
        innerAssigneesPanel.add(assigneesLabel);
        innerAssigneesPanel.setLayout(new GridLayout(1,2,10,10));
        //innerAssigneesPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));

        JPanel innerAssigneesListPanel = new JPanel();
        innerAssigneesListPanel.setBounds(0, 650, 600,150);
        //innerAssigneesListPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        innerAssigneesListPanel.setLayout(new GridLayout(2,1,10,10));
        innerAssigneesListPanel.add(assigneesListLabel);

        mainCreatePanel.add(innerButtonPanel);
        mainCreatePanel.add(innerTopicPanel);
        mainCreatePanel.add(innerDescriptionPanel);
        mainCreatePanel.add(innerChoicesPanel);
        mainCreatePanel.add(innerPeoplePanel);
        mainCreatePanel.add(innerCollaboratorButtonsPanel);
        mainCreatePanel.add(innerAssigneesPanel);
        mainCreatePanel.add(innerAssigneesListPanel);
    }

    public void initializeTicketView(){
        mainContentPanel.removeAll();
        mainContentPanel.add(mainTicketsPanel);
        mainContentPanel.add(mainCommentPanel);
        mainContentPanel.add(mainButtonsPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainTicketsPanel;
    }

    public void changeToTicketView(){
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainTicketsPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainTicketsPanel;
    }

    public void changeToCreate(){
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainCreatePanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainCreatePanel;
    }

    public void changeToEdit(){
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainEditPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainEditPanel;
    }

    private void addActionListener(JButton btn){
        btn.addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnMyTickets){
            changeToTicketView();
            //CLEAN TABLE
            //UPDATE LIST IN CASE OF CHANGES
            //SHOW ONLY SIGNED IN USERS TICKETS IN TABEL
        } if(e.getSource() == btnAllTickets){
            changeToTicketView();
            //CLEAN TABLE
            //UPDATE LIST IN CASE OF CHANGES
            //GET ALL TICKETS
            //SHOW TICKETS IN TABLE
        } if(e.getSource() == btnNewTicket){
            changeToCreate();
        } if(e.getSource() == btnEditTicket){
            changeToEdit();
            //READ ALL INFO FROM SELECTED TICKET
            //PLACE VALUES IN EDITPANEL
        } if(e.getSource() == btnEditReturn || e.getSource() == btnCreateReturn){
            changeToTicketView();
        } if(e.getSource() == btnCreateTicket){
            //GET VALUES FROM VIEW
            //CREATE TICKET
            //UPDATE TABLE WITH LATEST CREATED ADDED TICKET AT THE TOP OF THE LIST
        } if(e.getSource() == btnSaveChanges){
            //GET VALUES FROM VIEW
            //UPDATE CURRENT TICKET
            //UPDATE LIST
        }

    }

}
