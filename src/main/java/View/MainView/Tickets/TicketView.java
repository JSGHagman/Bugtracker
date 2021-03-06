/**
 * Class used for different views of tickets, the main functionality of the program.
 *
 * @author Jakob Hagman
 * @todo: Sätt ticket till open om inga assignees
 * @todo: Sätt ticket till open om inga assignees
 * @todo: Sätt ticket till open om inga assignees
 * @todo: Sätt ticket till open om inga assignees
 */

/**
 * @todo: Sätt ticket till open om inga assignees
 *
 */

package View.MainView.Tickets;

import Controller.Controller;
import Model.AttachedFiles;
import Model.Ticket;
import View.MainView.MainFrame.MainFrame;
import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TicketView extends JComponent implements ActionListener {
    //GENERAL
    private Controller controller;
    private MainFrame mainFrame;
    private Color menuColor = new Color(65, 105, 225);
    private Color hoverColor = new Color(65, 145, 225);
    private Object[][] data;
    private String[] columnNames;
    private JTable table;
    private JLabel searchLabel, summaryLabel, commentsLabel, emptyLabel;
    private JTextField searchField;
    private JButton btnAddComment, btnRemoveComment;
    private TableRowSorter sorter;
    private JList<String> commentsList = new JList<>();
    private JTextArea summaryText, commentText;
    private ArrayList<String> assignees, comments;
    private String owner;
    private boolean myTicketsView = false;
    private boolean isCreateView = false;
    private JFileChooser fileChooser;
    private File attachedFile;
    private ArrayList<File> attachedFiles = new ArrayList<>();
    //FOR OTHER VIEWS
    private JButton btnNewTicket, btnAllTickets, btnMyTickets, btnEditTicket, btnCreateTicket, btnCreateReturn, claimTicket, btnAddCollaborator, btnRemoveCollaborator, btnAttachFileCreate, btnDownLoadFilesCreate;
    private JPanel mainContentPanel, mainCreatePanel, mainTicketsPanel, mainEditPanel, mainCommentPanel, mainButtonsPanel, currentPanelOnDisplay;
    private JTextField topicField;
    private JTextArea descriptionText, comment, assigneesText, filesTextCreate;
    private JLabel topicLabel, descriptionLabel, priorityLabel, categoryLabel, ownerLabel, collaboratorLabel, assigneesLabel, assigneesListLabel, attachedLabel;
    private JScrollPane descriptionScroll;
    private JComboBox priorityBox, categoryBox, ownerBox, collaboratorsBox;
    //FOR EDIT VIEW
    private JButton btnEditReturn, btnSaveChanges, btnAddCollaboratorEdit, btnRemoveCollaboratorEdit, btnCloseTicket, btnAttachFileEdit, btnDownloadFilesEdit;
    private JPanel mainContentPanelEdit, mainCreatePanelEdit, mainTicketsPanelEdit, mainEditPanelEdit, mainCommentPanelEdit, mainButtonsPanelEdit, currentPanelOnDisplayEdit;
    private JTextField topicFieldEdit;
    private JTextArea descriptionTextEdit, commentEdit, assigneesTextEdit, filesTextEdit;
    private JLabel topicLabelEdit, descriptionLabelEdit, priorityLabelEdit, categoryLabelEdit, ownerLabelEdit, collaboratorLabelEdit, assigneesLabelEdit, assigneesListLabelEdit, attachedLabelEdit;
    private JScrollPane descriptionScrollEdit;
    private JComboBox priorityBoxEdit, categoryBoxEdit, ownerBoxEdit, collaboratorsBoxEdit;
    private int id;


    /**
     * Constructor, creates all components needed and sets the home view for tickets through initilaizeTicketView();
     *
     * @param controller
     * @param mainFrame
     */
    public TicketView(Controller controller, MainFrame mainFrame) {
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.mainContentPanel = mainFrame.getContentPanel();
        createAllButtons();
        createLabels();
        createInputFields();
        createLabelsEdit();
        createInputFieldsEdit();
        createPanels();
        comments = new ArrayList<>();
        assignees = new ArrayList<>();
        //  initializeTicketView();
    }

    /**
     * This method creates buttons.
     * Sends each button to the design method.
     */
    private void createAllButtons() {
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
        btnAddCollaboratorEdit = new JButton("Add");
        setButtonDesign(btnAddCollaboratorEdit);
        btnRemoveCollaboratorEdit = new JButton("Remove");
        setButtonDesign(btnRemoveCollaboratorEdit);
        btnCloseTicket = new JButton("Close Ticket");
        setButtonDesign(btnCloseTicket);
        btnAddComment = new JButton("Add comment");
        setButtonDesign(btnAddComment);
        btnAttachFileEdit = new JButton("Attach file");
        setButtonDesign(btnAttachFileEdit);
        btnAttachFileCreate = new JButton("Attach file");
        setButtonDesign(btnAttachFileCreate);
        btnDownloadFilesEdit = new JButton("Download files");
        setButtonDesign(btnDownloadFilesEdit);
        btnDownLoadFilesCreate = new JButton("Download files");
        setButtonDesign(btnDownLoadFilesCreate);
    }

    /**
     * This method sets some design of buttons and adds an actionlistener and mouselistener to every button
     *
     * @param btn
     */
    private void setButtonDesign(JButton btn) {
        btn.setBackground(menuColor);
        btn.setForeground(Color.WHITE);
        //btn.setFont(new Font("Dialog", Font.BOLD, 12));
        addActionListener(btn);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent evt) {
                btn.setBackground(menuColor);
            }

            public void mousePressed(MouseEvent evt) {
                btn.setBackground(menuColor);
            }
        });
    }

    /**
     * This method creates all labels which is placed around the different panels
     */
    private void createLabels() {
        //FOR CREATE VIEW
        topicLabel = new JLabel("Topic");
        topicLabel.setForeground(menuColor);
        topicLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setForeground(menuColor);
        descriptionLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        priorityLabel = new JLabel("Priority");
        priorityLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        priorityLabel.setForeground(menuColor);

        categoryLabel = new JLabel("Type");
        categoryLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        categoryLabel.setForeground(menuColor);

        ownerLabel = new JLabel("Owner");
        ownerLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        ownerLabel.setForeground(menuColor);

        collaboratorLabel = new JLabel("Collaborators");
        collaboratorLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        collaboratorLabel.setForeground(menuColor);

        assigneesLabel = new JLabel("Assignees");
        assigneesLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        assigneesLabel.setForeground(menuColor);

        assigneesListLabel = new JLabel();
        assigneesListLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        //assigneesListLabel.setText("<html>This label will be updated for every added collaborator<br/>Owner: Viktor<br/>Collaborators: Yara, Jakob, Patrik<br/></html>");

        searchLabel = new JLabel("Search");
        searchLabel.setForeground(menuColor);
        searchLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        summaryLabel = new JLabel("Summary");
        summaryLabel.setForeground(menuColor);
        summaryLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        commentsLabel = new JLabel("Comments");
        commentsLabel.setForeground(menuColor);
        commentsLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        attachedLabel = new JLabel("Files");
        attachedLabel.setForeground(menuColor);
        attachedLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        attachedLabelEdit = new JLabel("Files");
        attachedLabelEdit.setForeground(menuColor);
        attachedLabelEdit.setFont(new Font("Dialog", Font.BOLD, 16));

        emptyLabel = new JLabel("");


    }

    /**
     * This method creates all the components wich will be used for inout from the user
     */
    private void createInputFields() {
        //FOR CREATE VIEW
        topicField = new JTextField();
        topicField.setFont(new Font("Dialog", Font.PLAIN, 16));
        topicField.setBorder(BorderFactory.createLineBorder(menuColor, 3, false));

        descriptionText = new JTextArea();
        descriptionText.setFont(new Font("Dialog", Font.PLAIN, 16));
        descriptionText.setEditable(true);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);

        descriptionScroll = new JScrollPane(descriptionText);
        descriptionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScroll.setBorder(BorderFactory.createLineBorder(menuColor, 3, false));

        filesTextEdit = new JTextArea();
        filesTextEdit.setLineWrap(true);
        filesTextEdit.setEditable(false);
        filesTextEdit.setOpaque(false);
        filesTextEdit.setBorder(BorderFactory.createEmptyBorder());
        filesTextEdit.setText("Attached Files: \n");

        filesTextCreate = new JTextArea();
        filesTextCreate.setLineWrap(true);
        filesTextCreate.setEditable(false);
        filesTextCreate.setOpaque(false);
        filesTextCreate.setBorder(BorderFactory.createEmptyBorder());
        filesTextCreate.setText("Attached Files: \n");

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
        if (controller.getSignedInUser().getRole().equals("Admin")) {
            controller.populatePeopleBox(ownerBox);
        } else {
            ownerBox.addItem(controller.getSignedInUser().toString());
        }
        ownerBox.setSelectedItem(controller.getSignedInUser().toString());
        collaboratorsBox = new JComboBox();
        collaboratorsBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        collaboratorsBox.setBackground(Color.WHITE);
        controller.populatePeopleBox(collaboratorsBox);
        collaboratorsBox.addItem("None");
        collaboratorsBox.setSelectedItem("None");
        setDefaultValuesInFields();
    }


    /**
     * This method creates all the labels for the edit panel
     */
    private void createLabelsEdit() {
        topicLabelEdit = new JLabel("Change Topic");
        topicLabelEdit.setForeground(menuColor);
        topicLabelEdit.setFont(new Font("Dialog", Font.BOLD, 16));

        descriptionLabelEdit = new JLabel("Change Description");
        descriptionLabelEdit.setForeground(menuColor);
        descriptionLabelEdit.setFont(new Font("Dialog", Font.BOLD, 16));

        priorityLabelEdit = new JLabel("Change Priority");
        priorityLabelEdit.setFont(new Font("Dialog", Font.BOLD, 16));
        priorityLabelEdit.setForeground(menuColor);

        categoryLabelEdit = new JLabel("Change Type");
        categoryLabelEdit.setFont(new Font("Dialog", Font.BOLD, 16));
        categoryLabelEdit.setForeground(menuColor);

        ownerLabelEdit = new JLabel("Change Owner");
        ownerLabelEdit.setFont(new Font("Dialog", Font.BOLD, 16));
        ownerLabelEdit.setForeground(menuColor);

        collaboratorLabelEdit = new JLabel("Collaborators");
        collaboratorLabelEdit.setFont(new Font("Dialog", Font.BOLD, 16));
        collaboratorLabelEdit.setForeground(menuColor);

        assigneesLabelEdit = new JLabel("Assignees");
        assigneesLabelEdit.setFont(new Font("Dialog", Font.BOLD, 16));
        assigneesLabelEdit.setForeground(menuColor);

        assigneesListLabelEdit = new JLabel();
        assigneesListLabelEdit.setFont(new Font("Dialog", Font.PLAIN, 12));
        //assigneesListLabelEdit.setText("<html>This label will be updated for every added collaborator<br/>Owner: Viktor<br/>Collaborators: Yara, Jakob, Patrik<br/></html>");
    }


    /**
     * This method creates all the inputfields for the editpanel
     */
    private void createInputFieldsEdit() {
        //FOR EDIT VIEW
        topicFieldEdit = new JTextField();
        topicFieldEdit.setFont(new Font("Dialog", Font.PLAIN, 16));
        topicFieldEdit.setBorder(BorderFactory.createLineBorder(menuColor, 3, false));

        descriptionTextEdit = new JTextArea();
        descriptionTextEdit.setFont(new Font("Dialog", Font.PLAIN, 16));
        descriptionTextEdit.setEditable(true);
        descriptionTextEdit.setLineWrap(true);
        descriptionTextEdit.setWrapStyleWord(true);

        descriptionScrollEdit = new JScrollPane(descriptionTextEdit);
        descriptionScrollEdit.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollEdit.setBorder(BorderFactory.createLineBorder(menuColor, 3, false));

        priorityBoxEdit = new JComboBox();
        priorityBoxEdit.setFont(new Font("Dialog", Font.PLAIN, 16));
        priorityBoxEdit.setBackground(Color.WHITE);
        priorityBoxEdit.addItem("Low");
        priorityBoxEdit.addItem("Medium");
        priorityBoxEdit.addItem("High");

        categoryBoxEdit = new JComboBox();
        categoryBoxEdit.setFont(new Font("Dialog", Font.PLAIN, 16));
        categoryBoxEdit.setBackground(Color.WHITE);
        categoryBoxEdit.addItem("Issue");
        categoryBoxEdit.addItem("Bug");
        categoryBoxEdit.addItem("New feature request");

        ownerBoxEdit = new JComboBox();
        ownerBoxEdit.setFont(new Font("Dialog", Font.PLAIN, 16));
        ownerBoxEdit.setBackground(Color.WHITE);
        //ownerBoxEdit.addItem(controller.getSignedInUser());

        collaboratorsBoxEdit = new JComboBox();
        collaboratorsBoxEdit.setFont(new Font("Dialog", Font.PLAIN, 16));
        collaboratorsBoxEdit.setBackground(Color.WHITE);
        controller.populatePeopleBox(collaboratorsBoxEdit);
        collaboratorsBoxEdit.addItem("None");
        collaboratorsBoxEdit.setSelectedItem(null);
    }

    /**
     * This method creates the main panels which the user later will be able to switch back and forth between.
     */
    private void createPanels() {
        //CREATE PANELS
        mainButtonsPanel = new JPanel();
        mainButtonsPanel.setBounds(mainContentPanel.getX() + 10, 10, mainContentPanel.getWidth() / 2, mainContentPanel.getHeight() / 14);
        mainButtonsPanel.setLayout(new GridLayout(1, 4, 10, 10));

        mainButtonsPanel.add(btnMyTickets);
        mainButtonsPanel.add(btnAllTickets);
        mainButtonsPanel.add(btnNewTicket);
        mainButtonsPanel.add(btnEditTicket);

        mainTicketsPanel = new JPanel();
        mainTicketsPanel.setBounds(mainContentPanel.getX() + 10, mainContentPanel.getY() + 100, mainContentPanel.getWidth() - 500, mainContentPanel.getHeight() - 150);
        //mainTicketsPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        mainTicketsPanel.setLayout(null);
        setTicketPanelDetails();

        mainCreatePanel = new JPanel();
        mainCreatePanel.setBounds(mainContentPanel.getX() + 10, mainButtonsPanel.getY() + mainButtonsPanel.getHeight() + 5, mainContentPanel.getWidth() - 500, mainContentPanel.getHeight() - mainButtonsPanel.getHeight());
        mainCreatePanel.setLayout(null);
        setCreatePanelDetails();

        mainEditPanel = new JPanel();
        mainEditPanel.setBounds(mainContentPanel.getX() + 10, mainButtonsPanel.getY() + mainButtonsPanel.getHeight() + 5, mainContentPanel.getWidth() - 500, mainContentPanel.getHeight() - mainButtonsPanel.getHeight());
        mainEditPanel.setLayout(null);
    }

    /**
     * Will set everything needed to edit a ticket. Not done yet, only has two buttons
     */
    private void setEditPanelDetails() {
        mainEditPanel.removeAll();
        JPanel innerTopicPanelEdit = new JPanel();
        innerTopicPanelEdit.setBounds(0, 10, mainCreatePanel.getWidth() / 2, mainCreatePanel.getHeight() / 7);
        innerTopicPanelEdit.setLayout(new GridLayout(3, 1));
        innerTopicPanelEdit.add(topicLabelEdit);
        innerTopicPanelEdit.add(topicFieldEdit);
        innerTopicPanelEdit.add(descriptionLabelEdit);

        JPanel innerDescriptionPanelEdit = new JPanel();
        innerDescriptionPanelEdit.setBounds(0, innerTopicPanelEdit.getY() + innerTopicPanelEdit.getHeight(), mainCreatePanel.getWidth() / 2, mainCreatePanel.getHeight() / 5);
        innerDescriptionPanelEdit.setLayout(new GridLayout());
        innerDescriptionPanelEdit.add(descriptionScrollEdit);

        JPanel innerChoicesPanelEdit = new JPanel();
        innerChoicesPanelEdit.setLayout(new GridLayout(4, 1, 10, 10));
        innerChoicesPanelEdit.setBounds(0, innerDescriptionPanelEdit.getY() + innerDescriptionPanelEdit.getHeight(), (innerDescriptionPanelEdit.getWidth() / 2), innerDescriptionPanelEdit.getHeight());
        innerChoicesPanelEdit.add(priorityLabelEdit);
        innerChoicesPanelEdit.add(priorityBoxEdit);
        innerChoicesPanelEdit.add(categoryLabelEdit);
        innerChoicesPanelEdit.add(categoryBoxEdit);

        JPanel innerPeoplePanelEdit = new JPanel();
        innerPeoplePanelEdit.setLayout(new GridLayout(4, 1, 10, 10));
        innerPeoplePanelEdit.setBounds((innerChoicesPanelEdit.getX() + innerChoicesPanelEdit.getWidth()) + 5, innerDescriptionPanelEdit.getY() + innerDescriptionPanelEdit.getHeight(), (innerDescriptionPanelEdit.getWidth() / 2), innerDescriptionPanelEdit.getHeight());
        innerPeoplePanelEdit.add(ownerLabelEdit);
        innerPeoplePanelEdit.add(ownerBoxEdit);
        innerPeoplePanelEdit.add(collaboratorLabelEdit);
        innerPeoplePanelEdit.add(collaboratorsBoxEdit);

        JPanel innerCollaboratorButtonsPanelEdit = new JPanel();
        innerCollaboratorButtonsPanelEdit.setLayout(new GridLayout(1, 2, 10, 10));
        innerCollaboratorButtonsPanelEdit.setBounds(innerPeoplePanelEdit.getX(), innerPeoplePanelEdit.getY() + innerPeoplePanelEdit.getHeight() + 5, innerPeoplePanelEdit.getWidth(), innerPeoplePanelEdit.getHeight() / 6);
        innerCollaboratorButtonsPanelEdit.add(btnAddCollaboratorEdit);
        innerCollaboratorButtonsPanelEdit.add(btnRemoveCollaboratorEdit);

        JPanel innerAssigneesPanelEdit = new JPanel();
        innerAssigneesPanelEdit.setBounds(0, innerCollaboratorButtonsPanelEdit.getY() + innerCollaboratorButtonsPanelEdit.getHeight(), innerChoicesPanelEdit.getWidth() / 2, innerChoicesPanelEdit.getHeight() / 4);
        innerAssigneesPanelEdit.setLayout(new GridLayout(1, 2, 10, 10));
        innerAssigneesPanelEdit.add(assigneesLabel);

        JPanel innerAttachedPanelEdit = new JPanel();
        innerAttachedPanelEdit.setBounds(innerPeoplePanelEdit.getX(), innerCollaboratorButtonsPanelEdit.getY() + innerCollaboratorButtonsPanelEdit.getHeight(), innerPeoplePanelEdit.getWidth(), innerPeoplePanelEdit.getHeight() / 4);
        innerAttachedPanelEdit.setLayout(new GridLayout(1, 2, 10, 10));
        innerAttachedPanelEdit.add(attachedLabelEdit);

        JPanel innerFilesListPanelEdit = new JPanel();
        innerFilesListPanelEdit.setBounds(innerPeoplePanelEdit.getX(), innerAssigneesPanelEdit.getY() + innerAssigneesPanelEdit.getHeight(), innerTopicPanelEdit.getWidth() / 2, innerChoicesPanelEdit.getHeight() / 3);
        innerFilesListPanelEdit.setLayout(new GridLayout(1, 1, 10, 10));
        //innerFilesListPanelEdit.setBorder(BorderFactory.createLineBorder(menuColor));

        JScrollPane filesScroll = new JScrollPane(filesTextEdit);
        filesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        filesScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        filesScroll.setBorder(BorderFactory.createEmptyBorder());
        innerFilesListPanelEdit.add(filesScroll);

        JPanel innerFilesButtonEdit = new JPanel();
        innerFilesButtonEdit.setBounds(innerFilesListPanelEdit.getX(), innerFilesListPanelEdit.getY() + innerFilesListPanelEdit.getHeight(), innerFilesListPanelEdit.getWidth(), (innerChoicesPanelEdit.getHeight() / 2) - innerFilesListPanelEdit.getHeight());
        innerFilesButtonEdit.setLayout(new GridLayout(1, 2, 10, 10));
        innerFilesButtonEdit.add(btnAttachFileEdit);
        innerFilesButtonEdit.add(btnDownloadFilesEdit);

        JPanel innerAssigneesListPanelEdit = new JPanel();
        innerAssigneesListPanelEdit.setBounds(0, innerAssigneesPanelEdit.getY() + innerAssigneesPanelEdit.getHeight(), innerTopicPanelEdit.getWidth() / 2, innerChoicesPanelEdit.getHeight() / 2);
        innerAssigneesListPanelEdit.setLayout(new GridLayout(1, 1, 10, 10));
        //innerAssigneesListPanelEdit.setBorder((BorderFactory.createMatteBorder(0, 0, 3, 0, menuColor)));
        assigneesTextEdit = new JTextArea();
        assigneesTextEdit.setLineWrap(true);
        assigneesTextEdit.setEditable(false);
        assigneesTextEdit.setOpaque(false);
        assigneesText.setBorder(BorderFactory.createEmptyBorder());
        innerAssigneesListPanelEdit.add(assigneesTextEdit);

        JPanel innerButtonPanelEdit = new JPanel();
        innerButtonPanelEdit.setBounds(0, innerAssigneesListPanelEdit.getY() + innerAssigneesListPanelEdit.getHeight() + innerCollaboratorButtonsPanelEdit.getHeight() + innerFilesListPanelEdit.getHeight(), innerTopicPanelEdit.getWidth(), innerChoicesPanelEdit.getHeight() / 4);
        innerButtonPanelEdit.setLayout(new GridLayout(1, 3, 10, 10));
        innerButtonPanelEdit.add(btnCloseTicket);
        innerButtonPanelEdit.add(btnSaveChanges);
        innerButtonPanelEdit.add(btnEditReturn);

        mainEditPanel.add(innerButtonPanelEdit);
        mainEditPanel.add(innerTopicPanelEdit);
        mainEditPanel.add(innerDescriptionPanelEdit);
        mainEditPanel.add(innerChoicesPanelEdit);
        mainEditPanel.add(innerPeoplePanelEdit);
        mainEditPanel.add(innerAttachedPanelEdit);
        mainEditPanel.add(innerCollaboratorButtonsPanelEdit);
        mainEditPanel.add(innerAssigneesPanelEdit);
        mainEditPanel.add(innerAssigneesListPanelEdit);
        mainEditPanel.add(innerFilesListPanelEdit);
        mainEditPanel.add(innerFilesButtonEdit);
    }

    /**
     * Sets evrything needed to create a ticket in the create ticket panel
     */
    private void setCreatePanelDetails() {
        JPanel innerTopicPanel = new JPanel();
        innerTopicPanel.setBounds(0, 10, mainCreatePanel.getWidth() / 2, mainCreatePanel.getHeight() / 7);
        innerTopicPanel.setLayout(new GridLayout(3, 1));
        innerTopicPanel.add(topicLabel);
        innerTopicPanel.add(topicField);
        innerTopicPanel.add(descriptionLabel);

        JPanel innerDescriptionPanel = new JPanel();
        innerDescriptionPanel.setBounds(0, innerTopicPanel.getY() + innerTopicPanel.getHeight(), mainCreatePanel.getWidth() / 2, mainCreatePanel.getHeight() / 5);
        innerDescriptionPanel.setLayout(new GridLayout());
        innerDescriptionPanel.add(descriptionScroll);

        JPanel innerChoicesPanel = new JPanel();
        innerChoicesPanel.setLayout(new GridLayout(4, 1, 10, 10));
        innerChoicesPanel.setBounds(0, innerDescriptionPanel.getY() + innerDescriptionPanel.getHeight(), (innerDescriptionPanel.getWidth() / 2), innerDescriptionPanel.getHeight());
        innerChoicesPanel.add(priorityLabel);
        innerChoicesPanel.add(priorityBox);
        innerChoicesPanel.add(categoryLabel);
        innerChoicesPanel.add(categoryBox);

        JPanel innerPeoplePanel = new JPanel();
        innerPeoplePanel.setLayout(new GridLayout(4, 1, 10, 10));
        innerPeoplePanel.setBounds((innerChoicesPanel.getX() + innerChoicesPanel.getWidth()) + 5, innerDescriptionPanel.getY() + innerDescriptionPanel.getHeight(), (innerDescriptionPanel.getWidth() / 2), innerDescriptionPanel.getHeight());
        innerPeoplePanel.add(ownerLabel);
        innerPeoplePanel.add(ownerBox);
        innerPeoplePanel.add(collaboratorLabel);
        innerPeoplePanel.add(collaboratorsBox);

        JPanel innerCollaboratorButtonsPanel = new JPanel();
        innerCollaboratorButtonsPanel.setLayout(new GridLayout(1, 2, 10, 10));
        innerCollaboratorButtonsPanel.setBounds(innerPeoplePanel.getX(), innerPeoplePanel.getY() + innerPeoplePanel.getHeight() + 5, innerPeoplePanel.getWidth(), innerPeoplePanel.getHeight() / 6);
        innerCollaboratorButtonsPanel.add(btnAddCollaborator);
        innerCollaboratorButtonsPanel.add(btnRemoveCollaborator);

        JPanel innerAssigneesPanel = new JPanel();
        innerAssigneesPanel.setBounds(0, innerCollaboratorButtonsPanel.getY() + innerCollaboratorButtonsPanel.getHeight(), innerChoicesPanel.getWidth() / 2, innerChoicesPanel.getHeight() / 4);
        innerAssigneesPanel.add(assigneesLabel);
        innerAssigneesPanel.setLayout(new GridLayout(1, 2, 10, 10));

        JPanel innerAttachFilesPanel = new JPanel();
        innerAttachFilesPanel.setBounds(innerPeoplePanel.getX(), innerCollaboratorButtonsPanel.getY() + innerCollaboratorButtonsPanel.getHeight(), (innerChoicesPanel.getWidth() / 2) - 5, innerChoicesPanel.getHeight() / 4);
        innerAttachFilesPanel.setLayout(new GridLayout(1, 2, 10, 10));
        innerAttachFilesPanel.add(attachedLabel);

        JPanel innerFilesListPanel = new JPanel();
        innerFilesListPanel.setBounds(innerPeoplePanel.getX(), innerAssigneesPanel.getY() + innerAssigneesPanel.getHeight(), innerTopicPanel.getWidth() / 2, innerChoicesPanel.getHeight() / 3);
        innerFilesListPanel.setLayout(new GridLayout(1, 1, 10, 10));
        //innerFilesListPanelEdit.setBorder(BorderFactory.createLineBorder(menuColor));
        JScrollPane filesScroll = new JScrollPane(filesTextCreate);
        filesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        filesScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        filesScroll.setBorder(BorderFactory.createEmptyBorder());
        innerFilesListPanel.add(filesScroll);

        JPanel innerFilesButton = new JPanel();
        innerFilesButton.setBounds(innerFilesListPanel.getX(), innerFilesListPanel.getY() + innerFilesListPanel.getHeight(), innerFilesListPanel.getWidth(), (innerChoicesPanel.getHeight() / 2) - innerFilesListPanel.getHeight());
        innerFilesButton.setLayout(new GridLayout(1, 2, 10, 10));
        innerFilesButton.add(btnAttachFileCreate);
        innerFilesButton.add(emptyLabel);

        JPanel innerAssigneesListPanel = new JPanel();
        innerAssigneesListPanel.setBounds(0, innerAssigneesPanel.getY() + innerAssigneesPanel.getHeight(), innerTopicPanel.getWidth() / 2, innerChoicesPanel.getHeight() / 2);
        innerAssigneesListPanel.setLayout(new GridLayout(1, 1, 10, 10));
        //innerAssigneesListPanel.setBorder(BorderFactory.createLineBorder(menuColor));
        assigneesText = new JTextArea();
        assigneesText.setLineWrap(true);
        assigneesText.setLineWrap(true);
        assigneesText.setEditable(false);
        assigneesText.setOpaque(false);
        assigneesText.setBorder(BorderFactory.createEmptyBorder());
        assigneesText.setText("Owner: \nAssignees: ");
        innerAssigneesListPanel.add(assigneesText);

        JPanel innerButtonPanel = new JPanel();
        innerButtonPanel.setBounds(0, innerAssigneesListPanel.getY() + innerAssigneesListPanel.getHeight() + innerCollaboratorButtonsPanel.getHeight() + innerFilesListPanel.getHeight(), innerChoicesPanel.getWidth(), innerChoicesPanel.getHeight() / 4);
        innerButtonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        innerButtonPanel.add(btnCreateTicket);
        innerButtonPanel.add(btnCreateReturn);

        //innerAssigneesListPanelEdit.getY() + innerAssigneesListPanelEdit.getHeight() + innerCollaboratorButtonsPanelEdit.getHeight() + innerFilesListPanelEdit.getHeight(), innerTopicPanelEdit.getWidth(), innerChoicesPanelEdit.getHeight() / 4);

        mainCreatePanel.add(innerButtonPanel);
        mainCreatePanel.add(innerTopicPanel);
        mainCreatePanel.add(innerDescriptionPanel);
        mainCreatePanel.add(innerChoicesPanel);
        mainCreatePanel.add(innerPeoplePanel);
        mainCreatePanel.add(innerCollaboratorButtonsPanel);
        mainCreatePanel.add(innerAssigneesPanel);
        mainCreatePanel.add(innerAssigneesListPanel);
        mainCreatePanel.add(innerAttachFilesPanel);
        mainCreatePanel.add(innerFilesListPanel);
        mainCreatePanel.add(innerFilesButton);
    }


    /**
     * This method sets all details and places all the components in the ticketpanel
     */
    private void setTicketPanelDetails() {
        mainTicketsPanel.removeAll();
        JPanel innerSearchPanel = new JPanel();
        innerSearchPanel.setBounds(0, mainTicketsPanel.getY() - mainButtonsPanel.getHeight() + 10, mainButtonsPanel.getWidth() / 12, mainButtonsPanel.getHeight() / 2);
        innerSearchPanel.setLayout(new GridLayout(1, 1));
        innerSearchPanel.add(searchLabel);
        JPanel innerSearchFieldPanel = new JPanel();
        innerSearchFieldPanel.setBounds(innerSearchPanel.getX() + innerSearchPanel.getWidth(), innerSearchPanel.getY(), mainButtonsPanel.getWidth() / 5, mainButtonsPanel.getHeight() / 2);
        innerSearchFieldPanel.setLayout(new GridLayout(1, 1, 10, 10));
        searchField = new JTextField();
        innerSearchFieldPanel.add(searchField);

        setTicketList();
        table = new JTable(data, columnNames);
        table.setRowHeight(mainTicketsPanel.getHeight() / 40);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setBackground(menuColor);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setEnabled(false);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        removeEditors();
        setSorter();

        JScrollPane scrollPane = new JScrollPane(table);
        int verticalPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
        scrollPane.setVerticalScrollBarPolicy(verticalPolicy);
        scrollPane.setBounds(0, mainTicketsPanel.getY(), mainTicketsPanel.getWidth(), mainTicketsPanel.getHeight() - mainContentPanel.getHeight() / 2);

        JPanel innerSummaryPanel = new JPanel();
        innerSummaryPanel.setBounds(0, mainTicketsPanel.getY() + scrollPane.getHeight() + 5, mainTicketsPanel.getWidth(), scrollPane.getHeight());

        JPanel summaryHeadlinePanel = new JPanel();
        summaryHeadlinePanel.setBounds(0, innerSummaryPanel.getY() + innerSearchPanel.getHeight() / 2, innerSearchPanel.getWidth(), innerSearchPanel.getHeight());
        summaryHeadlinePanel.setLayout(new GridLayout(1, 1));
        summaryHeadlinePanel.add(summaryLabel);

        summaryText = new JTextArea();
        summaryText.setLineWrap(true);
        summaryText.setLineWrap(true);
        summaryText.setEditable(false);
        summaryText.setOpaque(false);
        summaryText.setBorder(BorderFactory.createEmptyBorder());

        JScrollPane summaryScroll = new JScrollPane(summaryText);
        summaryScroll.setBounds(0, innerSummaryPanel.getY() + innerSearchPanel.getHeight() * 2, mainTicketsPanel.getWidth() / 2, innerSummaryPanel.getHeight() - innerSearchPanel.getHeight() * 2);
        int verticalScrollbarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
        summaryScroll.setVerticalScrollBarPolicy(verticalScrollbarPolicy);
        summaryScroll.setBorder(BorderFactory.createEmptyBorder());

        JPanel commentsHeadlinePanel = new JPanel();
        commentsHeadlinePanel.setBounds(summaryHeadlinePanel.getX() + mainTicketsPanel.getWidth() / 2 + 5, innerSummaryPanel.getY() + innerSearchPanel.getHeight() / 2, mainButtonsPanel.getWidth() / 8, innerSearchPanel.getHeight());
        commentsHeadlinePanel.setLayout(new GridLayout(1, 1));
        commentsHeadlinePanel.add(commentsLabel);

        JScrollPane commentsListScroll = new JScrollPane(commentsList);
        commentsListScroll.setBounds(summaryScroll.getX() + summaryScroll.getWidth() + 5, innerSummaryPanel.getY() + innerSearchPanel.getHeight() * 2, mainTicketsPanel.getWidth() / 2 - 5, summaryScroll.getHeight() - summaryScroll.getHeight() / 3);
        commentsListScroll.setVerticalScrollBarPolicy(verticalPolicy);

        commentText = new JTextArea();
        commentText.setLineWrap(true);
        commentText.setLineWrap(true);
        JScrollPane commentTextScroll = new JScrollPane(commentText);
        commentTextScroll.setBounds(summaryScroll.getX() + summaryScroll.getWidth() + 5, innerSummaryPanel.getY() + innerSearchPanel.getHeight() * 2 + commentsListScroll.getHeight() + 5, mainTicketsPanel.getWidth() / 2 - 5, summaryScroll.getHeight() / 6);
        commentTextScroll.setVerticalScrollBarPolicy(verticalPolicy);
        btnAddComment.setBounds(commentTextScroll.getX(), commentTextScroll.getY() + commentTextScroll.getHeight() + 5, commentTextScroll.getWidth() / 4, commentTextScroll.getHeight());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    try {
                        id = getIdFromTable();
                        setSummaryInView(id);
                        comments = controller.getTicketComments(id);
                        if (comments != null) {
                            String[] list = comments.toArray(new String[comments.size()]);
                            setCommentsList(list);
                        }
                    } catch (IndexOutOfBoundsException exception) {
                    }
                }
                if (e.getClickCount() == 2) {
                    attachedFiles.clear();
                    isCreateView = false;
                    boolean ok = false;
                    try {
                        id = getIdFromTable();
                        ok = true;
                    } catch (IndexOutOfBoundsException ex) {
                        controller.showMessage("Select a ticket");
                    }
                    if (ok) {
                        if (controller.editGuard(id)) {
                            setEditTicket(id);
                            setEditPanelDetails();
                            changeToEdit();
                            assignees.clear();
                            setAssigneesTextEdit(id);
                            updateAssigneesTextEdit();
                            ownerBoxEdit.addItemListener(new ItemListener() {
                                @Override
                                public void itemStateChanged(ItemEvent e) {
                                    owner = ownerBoxEdit.getSelectedItem().toString();
                                    updateAssigneesTextEdit();
                                }
                            });
                            myTicketsView = false;
                            table.clearSelection();
                        } else {
                            controller.showMessage("You don't have authorization to edit this ticket");
                        }

                    }
                }
            }
        });

        mainTicketsPanel.add(btnAddComment);
        mainTicketsPanel.add(commentTextScroll);
        mainTicketsPanel.add(commentsHeadlinePanel);
        mainTicketsPanel.add(summaryHeadlinePanel);
        mainTicketsPanel.add(summaryScroll);
        mainTicketsPanel.add(commentsListScroll);
        mainTicketsPanel.add(innerSearchPanel);
        mainTicketsPanel.add(innerSearchFieldPanel);
        mainTicketsPanel.add(scrollPane);
        mainTicketsPanel.add(innerSummaryPanel);
        mainTicketsPanel.setBorder((BorderFactory.createMatteBorder(3, 0, 3, 0, menuColor)));
    }

    /**
     * This method takes the id from the selected ticket in the table
     * It then places all the info from that ticket in the summarypanel
     *
     * @param id
     */
    private void setSummaryInView(int id) {
        String summary = controller.showTicketSummary(id);
        summaryText.setText(summary);
        summaryText.setFont(new Font("Dialog", Font.PLAIN, 16));
        mainTicketsPanel.revalidate();
        mainTicketsPanel.repaint();
    }

    /**
     * Sets every column on everyu row as uneditable
     */
    private void removeEditors() {
        for (int i = 0; i < table.getColumnCount(); i++) {
            Class<?> col_class = table.getColumnClass(i);
            table.setDefaultEditor(col_class, null);
        }
    }

    /**
     * This method sets the list with the tickets
     *
     * @param list
     */
    private void setCommentsList(String[] list) {
        commentsList.setListData(list);
    }

    /**
     * This method initializes the sorting used in the seacrhfield
     */
    private void setSorter() {
        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }

    /**
     * This method creates the table contents all places all tickets in the table
     * There are two different tables that will be created based on which view is present, one for all tickets
     * and one for the tickets associated with the signed in user
     */
    public void setTicketList() {
        if (!myTicketsView) {
            data = null;
            columnNames = new String[]{"ID", "Type", "Topic", "Priority", "Status", "Owner", "Date"};
            ArrayList<Model.Ticket> list = controller.getAllTicketsFromManager();
            data = new Object[list.size()][7];
            for (int i = 0; i < list.size(); i++) {
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getCategory();
                data[i][2] = list.get(i).getTopic();
                if (list.get(i).getPriority() == 1) {
                    data[i][3] = "High";
                } else if (list.get(i).getPriority() == 2) {
                    data[i][3] = "Medium";
                } else if (list.get(i).getPriority() == 3) {
                    data[i][3] = "Low";
                }
                data[i][4] = list.get(i).getStatus();
                if (list.get(i).getOwner().getEmail() != "none@email.com") {
                    String firstName = list.get(i).getOwner().getFirstName();
                    char lastName = list.get(i).getOwner().getLastName().charAt(0);
                    char lastNameUpper = Character.toUpperCase(lastName);
                    String userName = String.format("%s %s", firstName, lastNameUpper);
                    data[i][5] = userName;
                } else {
                    data[i][5] = "None";
                }
                data[i][6] = list.get(i).getStartdate();
            }
        }

        if (myTicketsView) {
            data = null;
            columnNames = new String[]{"ID", "Type", "Topic", "Priority", "Status", "Owner", "Date"};
            ArrayList<Model.Ticket> list = controller.getMyTickets();
            data = new Object[list.size()][7];
            for (int i = 0; i < list.size(); i++) {
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getCategory();
                data[i][2] = list.get(i).getTopic();
                if (list.get(i).getPriority() == 1) {
                    data[i][3] = "High";
                } else if (list.get(i).getPriority() == 2) {
                    data[i][3] = "Medium";
                } else if (list.get(i).getPriority() == 3) {
                    data[i][3] = "Low";
                }
                data[i][4] = list.get(i).getStatus();
                if (list.get(i).getOwner().getEmail() != "none@email.com") {
                    String firstName = list.get(i).getOwner().getFirstName();
                    char lastName = list.get(i).getOwner().getLastName().charAt(0);
                    char lastNameUpper = Character.toUpperCase(lastName);
                    String userName = String.format("%s %s", firstName, lastNameUpper);
                    data[i][5] = userName;
                } else {
                    data[i][5] = "None";
                }
                data[i][6] = list.get(i).getStartdate();
            }
        }
    }

    /**
     * Initializes the first view
     */
    public void initializeTicketView() {
        mainContentPanel.removeAll();
        mainContentPanel.add(mainTicketsPanel);
        mainContentPanel.add(mainButtonsPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainTicketsPanel;
    }

    /**
     * Updates view to ticket view
     */
    public void changeToTicketView() {
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainTicketsPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainTicketsPanel;
    }

    /**
     * Changes to create a new ticket view
     */
    public void changeToCreate() {
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainCreatePanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainCreatePanel;
    }

    /**
     * Changes to edit an existing ticket view
     */
    public void changeToEdit() {
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(mainEditPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = mainEditPanel;
    }

    /**
     * This method is used for creating a ticket.
     * It takes all the values from the inputfields and passes them all to a method in the controller class
     */
    private void createTicket() {
        String topic = topicField.getText();
        String description = descriptionText.getText();
        String priorityString = (String) priorityBox.getSelectedItem();
        int priority = 0;
        if (priorityString.equals("Low")) {
            priority = 3;
        }
        if (priorityString.equals("Medium")) {
            priority = 2;
        }
        if (priorityString.equals("High")) {
            priority = 1;
        }
        String type = (String) categoryBox.getSelectedItem();
        String owner = controller.getSignedInUser().toString();

        try {
            controller.newTicket(topic, description, priority, type, owner, assignees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when edit ticket is pressed.
     * It sets the values in the fields based on what ticket is selected.
     *
     * @param id
     * @throws IndexOutOfBoundsException
     */
    private void setEditTicket(int id) throws IndexOutOfBoundsException {
        Ticket t = controller.getTicketInfo(id);
        if (controller.getSignedInUser().getRole().equals("Admin")) {
            controller.populatePeopleBox(ownerBoxEdit);
        } else {
            ownerBoxEdit.addItem(t.getOwner());
        }
        int priority = t.getPriority();
        topicFieldEdit.setText(t.getTopic());
        descriptionTextEdit.setText(t.getDescription());
        categoryBoxEdit.setSelectedItem(t.getCategory());
        ownerBoxEdit.setSelectedItem(t.getOwner());
        priorityBoxEdit.setSelectedItem(t.getPriorityAsString());
        owner = ownerBoxEdit.getSelectedItem().toString();
        attachedFiles.clear();
        String files = controller.getFilesAsString(t);
        filesTextEdit.setText("Attached files: " + files);
    }

    /**
     * Method is called when save changes button is pressed.
     * It takes all values in the fields passes them to method in the controller which saves all the new info.
     *
     * @param id
     */
    private void saveTicketChanges(int id) {
        String topic = topicFieldEdit.getText();
        String description = descriptionTextEdit.getText();
        String priorityString = priorityBoxEdit.getSelectedItem().toString();
        int priority = 0;
        if (priorityString.equals("High")) {
            priority = 1;
        }
        if (priorityString.equals("Medium")) {
            priority = 2;
        }
        if (priorityString.equals("Low")) {
            priority = 3;
        }
        String owner = controller.getSignedInUser().toString();
        String type = categoryBoxEdit.getSelectedItem().toString();
        int ticketid = id;
        try {
            controller.updateTicket(id, topic, description, priority, owner, type, assignees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds actionlistener to the buttons.
     */
    private void addActionListener(JButton btn) {
        btn.addActionListener(this);
    }


    /**
     * This method is called when some of the buttons are pressed
     * It's purpose is the return the values in boxes to default
     */
    private void setDefaultValuesInFields() {
        topicField.setText(" Enter topic");
        descriptionText.setText(" Describe the issue");
        priorityBox.setSelectedItem("Low");
        categoryBox.setSelectedItem("Issue");
        ownerBox.setSelectedItem("None");
    }

    /**
     * This method is used to set a temporary list of assignees when you create a ticket
     * The list will then be passed to the controller through the create ticket method
     */
    public void setAssigneesCreate() {
        assignees.add(collaboratorsBox.getSelectedItem().toString());
    }

    /**
     * This method is used to set a temporary list of assignees when you save changes of ticket
     * The list will then be passed to the controller through the save changes method
     */
    public void setAssigneesEdit() {
        assignees.add(collaboratorsBoxEdit.getSelectedItem().toString());
    }

    /**
     * This method updates the the lable which displays the information about the owner/assignees
     */
    private void updateAssigneesTextCreate() {
        if (owner == null) {
            owner = "None";
        }
        String assigneesString = String.format("Owner: %s\nAssignees: %s",
                owner, createAssigneesString(assignees));
        assigneesText.setText(assigneesString);
    }

    /**
     * This method updates the the lable which displays the information about the owner/assignees
     */
    private void updateAssigneesTextEdit() {
        String assigneesString = String.format("Owner: %s\nAssignees: %s",
                owner, createAssigneesString(assignees));
        assigneesTextEdit.setText(assigneesString);
    }

    /**
     * This method is called when edit button is pressed
     * It takes the id and gets all assignees on that ticket from the controller
     *
     * @param id
     */
    private void setAssigneesTextEdit(int id) {
        assignees = controller.getAgentsOnTicket(id);
    }

    /**
     * This method takes the list of assignees and formats every string in the list as one string
     * Which will be used whenever all assignees is displayed
     *
     * @param assignees
     * @return
     */
    private String createAssigneesString(ArrayList<String> assignees) {
        String listString = assignees.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
        return listString;
    }

    /**
     * This gets the id of the ticket which has been selected in the list.
     *
     * @return
     */
    private int getIdFromTable() {
        int selectionRow = table.getSelectedRow();
        int idColumn = 0;
        String idString = table.getValueAt(selectionRow, idColumn).toString();
        int id = Integer.parseInt(idString);
        return id;
    }

    /**
     * This method is used whenever the remove collaborator button has been pressed
     * It removes the user selected in the box if that user is in the assignees list
     * otherwise it will throw an errormessage
     *
     * @param user
     */
    private void removeCollaborator(String user) {
        boolean found = false;
        for (String s : assignees) {
            if (s.equals(user)) {
                assignees.remove(user);
                found = true;
                break;
            }
        }
        if (!found) {
            controller.showMessage("Cannot remove unassigned user");
        }
    }

    /**
     * This method is used whenever the remove collaborator button has been pressed
     * It removes the user selected in the box if that user is in the assignees list
     * otherwise it will throw an errormessage
     *
     * @param user
     */
    private void removeCollaboratorFromOldTicket(String user, int id) {
        boolean found = false;
        for (String s : assignees) {
            if (s.equals(user)) {
                assignees.remove(user);
                controller.removeAgent(user, id);
                found = true;
                break;
            }
        }
        if (!found) {
            controller.showMessage("Cannot remove unassigned user");
        }
    }

    /**
     * Used whenever attachfile is pressed
     */
    public void chooseFile() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            attachedFile = fileChooser.getSelectedFile();
            attachedFiles.add(attachedFile);
            if (isCreateView) {
                setFilesCreate();
            } else {
                setFilesEdit();
            }
        }
    }

    private void setFilesCreate() {
        String listString = attachedFiles.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
        filesTextCreate.setText("Attached Files: \n" + listString);
    }

    private void setFilesEdit() {
        Ticket t = controller.getMarkedTicket(id);
        String oldfiles = controller.getFilesAsString(t);
        String newFiles = attachedFiles.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
        filesTextEdit.setText("Attached Files: \n" + oldfiles + newFiles);
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Not sure if this is used tbh
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * This method specifies what will be called whenever a button is pressed
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAttachFileEdit) {
            chooseFile();
        }
        if (e.getSource() == btnAttachFileCreate) {
            chooseFile();
        }

        if (e.getSource() == btnDownloadFilesEdit) {
            controller.startDownloadThread(id);
        }

        if (e.getSource() == btnMyTickets) {
            myTicketsView = true;
            isCreateView = false;
            setTicketPanelDetails();
            changeToTicketView();
            table.clearSelection();
        }


        if (e.getSource() == btnAllTickets) {
            myTicketsView = false;
            isCreateView = false;
            setTicketPanelDetails();
            changeToTicketView();
            table.clearSelection();
        }

        if (e.getSource() == btnNewTicket) {
            isCreateView = true;
            changeToCreate();
            assignees.clear();
            attachedFiles.clear();
            ownerBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    owner = ownerBox.getSelectedItem().toString();
                    updateAssigneesTextCreate();
                }
            });
            myTicketsView = false;
            table.clearSelection();
        }

        if (e.getSource() == btnEditTicket) {
            attachedFiles.clear();
            isCreateView = false;
            boolean ok = false;
            try {
                id = getIdFromTable();
                ok = true;
            } catch (IndexOutOfBoundsException ex) {
                controller.showMessage("Select a ticket");
            }
            if (ok) {
                if (controller.editGuard(id)) {
                    setEditTicket(id);
                    setEditPanelDetails();
                    changeToEdit();
                    assignees.clear();
                    setAssigneesTextEdit(id);
                    updateAssigneesTextEdit();
                    ownerBoxEdit.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            owner = ownerBoxEdit.getSelectedItem().toString();
                            updateAssigneesTextEdit();
                        }
                    });
                    myTicketsView = false;
                    table.clearSelection();
                } else {
                    controller.showMessage("You don't have authorization to edit this ticket");
                }
            }
        }

        if (e.getSource() == btnEditReturn || e.getSource() == btnCreateReturn) {
            changeToTicketView();
            setDefaultValuesInFields();
            myTicketsView = false;
            table.clearSelection();
        }

        if (e.getSource() == btnSaveChanges) {
            saveTicketChanges(id);
            setTicketPanelDetails();
            changeToTicketView();
            table.clearSelection();
            setCommentsList(new String[0]);
            myTicketsView = false;
            if (attachedFiles.size() > 0) {
                controller.startPlaceFileThread(id, attachedFiles);
            }
            attachedFile = null;
        }

        if (e.getSource() == btnCreateTicket) {
            createTicket();
            setTicketPanelDetails();
            setDefaultValuesInFields();
            changeToTicketView();
            table.clearSelection();
            setCommentsList(new String[0]);
            myTicketsView = false;
            if (attachedFiles.size() > 0) {
                controller.startPlaceFileThread(id, attachedFiles);
            }
            attachedFile = null;
        }

        if (e.getSource() == btnAddCollaborator) {
            setAssigneesCreate();
            updateAssigneesTextCreate();
        }

        if (e.getSource() == btnAddCollaboratorEdit) {
            setAssigneesEdit();
            updateAssigneesTextEdit();
        }

        if (e.getSource() == btnRemoveCollaboratorEdit) {
            removeCollaboratorFromOldTicket(collaboratorsBoxEdit.getSelectedItem().toString(), id);
            updateAssigneesTextEdit();
        }

        if (e.getSource() == btnRemoveCollaborator) {
            removeCollaborator(collaboratorsBox.getSelectedItem().toString());
            updateAssigneesTextCreate();
        }

        if (e.getSource() == btnCloseTicket) {
            myTicketsView = false;
            controller.closeTicket(id);
            setTicketPanelDetails();
            changeToTicketView();
            table.clearSelection();
            setCommentsList(new String[0]);
        }

        if (e.getSource() == btnAddComment) {
            String check = commentText.getText().trim(); //read contents of text area into 'data'
            if (check.equals("")) {
                controller.showMessage("Comment cannot be null");
            } else {
                try {
                    id = getIdFromTable();
                    controller.addCommentToTicket(commentText.getText(), controller.getSignedInUser().getEmail(), id);
                    comments = controller.getTicketComments(id);
                    String[] list = comments.toArray(new String[comments.size()]);
                    setCommentsList(list);
                    commentText.setText("");
                } catch (IndexOutOfBoundsException exc) {
                    controller.showMessage("Select a ticket first.");
                }
            }
        }
    }
}
