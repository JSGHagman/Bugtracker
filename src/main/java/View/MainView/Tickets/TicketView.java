
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
    private JScrollPane descriptionScroll, createScroll;
    private JComboBox priorityBox, categoryBox, ownerBox, collaboratorsBox;

    /**
     * Constructor, creates all components needed and sets the home view for tickets through initilaizeTicketView();
     * @param controller
     * @param mainFrame
     */
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

    /**
     * This method creates buttons.
     * Sends each button to the design method.
     */
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

    /**
     * This method sets some design of buttons and adds an actionlistener and mouselistener to every button
     * @param btn
     */
    private void setButtonDesign(JButton btn){
        btn.setBackground(menuColor);
        btn.setForeground(Color.WHITE);
        //btn.setFont(new Font("Dialog", Font.BOLD, 16));
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


    /**
     * This method creates all labels which is placed around the different panels
     */
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

        collaboratorLabel = new JLabel("Collaborators");
        collaboratorLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        collaboratorLabel.setForeground(menuColor);

        assigneesLabel = new JLabel("Assignees");
        assigneesLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        assigneesLabel.setForeground(menuColor);

        assigneesListLabel = new JLabel();
        assigneesListLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        assigneesListLabel.setText("<html>This label will be updated for every added collaborator<br/>Owner: Viktor<br/>Collaborators: Yara, Jakob, Patrik<br/></html>");


        //FOR EDIT VIEW

    }

    /**
     * This method creates all the components wich will be used for inout from the user
     */
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

    /**
     * This method creates the main panels which the user later will be able to switch back and forth between.
     */
    private void createPanels(){
        //CREATE PANELS

        mainButtonsPanel = new JPanel();
        mainButtonsPanel.setBounds(mainContentPanel.getX() + 10, 10, mainContentPanel.getWidth()/2, mainContentPanel.getHeight()/14 );
        mainButtonsPanel.setLayout(new GridLayout(1,4,10,10));

        mainButtonsPanel.add(btnMyTickets);
        mainButtonsPanel.add(btnAllTickets);
        mainButtonsPanel.add(btnNewTicket);
        mainButtonsPanel.add(btnEditTicket);

        mainTicketsPanel = new JPanel();
        mainTicketsPanel.setBounds(mainContentPanel.getX() + 10, mainContentPanel.getY() + 100, mainContentPanel.getWidth() - 500, mainContentPanel.getHeight()-150);
        mainTicketsPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));

        mainCreatePanel = new JPanel();
        mainCreatePanel.setBounds(mainContentPanel.getX() + 10, mainButtonsPanel.getY() + mainButtonsPanel.getHeight() +5, mainContentPanel.getWidth() - 500, mainContentPanel.getHeight()-mainButtonsPanel.getHeight());
        //mainCreatePanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        mainCreatePanel.setLayout(null);
        setCreatePanelDetails();

        mainEditPanel = new JPanel();
        mainEditPanel.setBounds(150,100, mainContentPanel.getWidth() - 500, mainContentPanel.getHeight()-150);
        mainEditPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        mainEditPanel.setLayout(null);
        setEditPanelDetails();

        /*
        mainCommentPanel = new JPanel();
        mainCommentPanel.setBounds(150,100, mainContentPanel.getWidth() - 500, mainContentPanel.getHeight()/8);
        mainCommentPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));*/

    }

    /**
     * Will set everything needed to edit a ticket. Not done yet, only has two buttons
     */
    private void setEditPanelDetails() {
        JPanel innerButtonPanelEdit = new JPanel();
        innerButtonPanelEdit.setBounds(0,760, 350, 75);
        innerButtonPanelEdit.setLayout(new GridLayout(1,1,10,10));
        innerButtonPanelEdit.add(btnSaveChanges);
        innerButtonPanelEdit.add(btnEditReturn);
        mainEditPanel.add(innerButtonPanelEdit);
    }

    /**
     * Sets evrything needed to create a ticket in the create ticket panel
     */
    private void setCreatePanelDetails() {
        //500
        JPanel innerTopicPanel = new JPanel();
        innerTopicPanel.setBounds(0,10,mainCreatePanel.getWidth()/2, mainCreatePanel.getHeight()/7);
        innerTopicPanel.setLayout(new GridLayout(3,1));
        innerTopicPanel.add(topicLabel);
        innerTopicPanel.add(topicField);
        innerTopicPanel.add(descriptionLabel);

        JPanel innerDescriptionPanel = new JPanel();
        innerDescriptionPanel.setBounds(0,innerTopicPanel.getY() + innerTopicPanel.getHeight(),mainCreatePanel.getWidth()/2,mainCreatePanel.getHeight()/5);
        innerDescriptionPanel.setLayout(new GridLayout());
        innerDescriptionPanel.add(descriptionScroll);

        JPanel innerChoicesPanel = new JPanel();
        innerChoicesPanel.setLayout(new GridLayout(4,1,10,10));
        innerChoicesPanel.setBounds(0, innerDescriptionPanel.getY() + innerDescriptionPanel.getHeight(), (innerDescriptionPanel.getWidth()/2), innerDescriptionPanel.getHeight());
        //innerChoicesPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        innerChoicesPanel.add(priorityLabel);
        innerChoicesPanel.add(priorityBox);
        innerChoicesPanel.add(categoryLabel);
        innerChoicesPanel.add(categoryBox);

        JPanel innerPeoplePanel = new JPanel();
        innerPeoplePanel.setLayout(new GridLayout(4,1,10,10));
        innerPeoplePanel.setBounds((innerChoicesPanel.getX() + innerChoicesPanel.getWidth()) + 5, innerDescriptionPanel.getY() + innerDescriptionPanel.getHeight(), (innerDescriptionPanel.getWidth()/2), innerDescriptionPanel.getHeight());
        innerPeoplePanel.add(ownerLabel);
        innerPeoplePanel.add(ownerBox);
        innerPeoplePanel.add(collaboratorLabel);
        innerPeoplePanel.add(collaboratorsBox);

        JPanel innerCollaboratorButtonsPanel = new JPanel();
        innerCollaboratorButtonsPanel.setLayout(new GridLayout(1,2,10,10));
        innerCollaboratorButtonsPanel.setBounds(innerPeoplePanel.getX(), innerPeoplePanel.getY() + innerPeoplePanel.getHeight() + 5, innerPeoplePanel.getWidth(), innerPeoplePanel.getHeight()/4);
        innerCollaboratorButtonsPanel.add(btnAddCollaborator);
        innerCollaboratorButtonsPanel.add(btnRemoveCollaborator);

        JPanel innerAssigneesPanel = new JPanel();
        innerAssigneesPanel.setBounds(0, innerCollaboratorButtonsPanel.getY() + innerCollaboratorButtonsPanel.getHeight(), innerChoicesPanel.getWidth()/2,innerChoicesPanel.getHeight()/4);
        innerAssigneesPanel.add(assigneesLabel);
        innerAssigneesPanel.setLayout(new GridLayout(1,2,10,10));
        //innerAssigneesPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));

        JPanel innerAssigneesListPanel = new JPanel();
        innerAssigneesListPanel.setBounds(0, innerAssigneesPanel.getY() + innerAssigneesPanel.getHeight(), innerTopicPanel.getWidth(), innerChoicesPanel.getHeight()/2);
        //innerAssigneesListPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        innerAssigneesListPanel.setLayout(new GridLayout(1,1,10,10));
        innerAssigneesListPanel.setBorder((BorderFactory.createMatteBorder(0, 0, 3, 0, menuColor)));
        innerAssigneesListPanel.add(assigneesListLabel);


        JPanel innerButtonPanel = new JPanel();
        innerButtonPanel.setBounds(0,innerAssigneesListPanel.getY() + innerAssigneesListPanel.getHeight() + innerCollaboratorButtonsPanel.getHeight(), innerChoicesPanel.getWidth(), innerChoicesPanel.getHeight()/4);
        innerButtonPanel.setLayout(new GridLayout(1,3,10,10));
        innerButtonPanel.add(btnCreateTicket);
        innerButtonPanel.add(btnCreateReturn);

        mainCreatePanel.add(innerButtonPanel);
        mainCreatePanel.add(innerTopicPanel);
        mainCreatePanel.add(innerDescriptionPanel);
        mainCreatePanel.add(innerChoicesPanel);
        mainCreatePanel.add(innerPeoplePanel);
        mainCreatePanel.add(innerCollaboratorButtonsPanel);
        mainCreatePanel.add(innerAssigneesPanel);
        mainCreatePanel.add(innerAssigneesListPanel);

        /*createScroll = new JScrollPane(mainCreatePanel);
        createScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);*/


    }

    /**
     * Initializes the first view
     */
    public void initializeTicketView(){
        mainContentPanel.removeAll();
        mainContentPanel.add(mainTicketsPanel);
        //mainContentPanel.add(mainCommentPanel);
        mainContentPanel.add(mainButtonsPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainTicketsPanel;
    }

    /**
     * Updates view to ticket view
     */
    public void changeToTicketView(){
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainTicketsPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainTicketsPanel;
    }

    /**
     * Changes to create a new ticket view
     */
    public void changeToCreate(){
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainCreatePanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainCreatePanel;
    }

    /**
     * Changes to edit an existing ticket view
     */
    public void changeToEdit(){
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainEditPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainEditPanel;
    }

    /**
     * Adds actionlistener to the buttons.
     */
    private void addActionListener(JButton btn){
        btn.addActionListener(this);
    }


    /**
     * Not sure if this is used tbh
     * @param g
     */
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
