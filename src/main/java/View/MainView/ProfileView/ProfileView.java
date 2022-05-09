package View.MainView.ProfileView;

import Controller.Controller;
import Model.UserManager;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfileView implements ActionListener {

    private JPanel mainContentPanel, infoPanel, changePanel, buttonPanel, currentPanelOnDisplay, imagePanel, topInfoPanel, middleInfoPanel, loweInfoPanel, roleInfoPanel;
    private JPanel changeBtnPanel, imageChangePanel, topChangePanel, middleChangePanel, lowerChangePanel, roleChangePanel;
    private JTextField fName, lName, password, eMail;
    private JLabel roleInfoLabel, firstnameLabel, lastnameLabel, emailLabel, roleLabel, infoFirstname, infolastname,infoEmail, infoInputFirstname, infoInputLastname, infoInputEmail;
    private JTextArea infoBox;
    private JButton btnChangeInfo, btnShowInfo, btnChange;
    private ImageIcon profilePicture;
    private JTextPane infoArea;

    private String mail;
    private String logInPassword;
    private String logInFname;
    private String logInLname;
    private String infotext;

    //GENERAL
    private Controller controller;
    private MainFrame mainFrame;
    private Color menuColor = new Color(65, 105, 225);
    private Color hoverColor = new Color(65, 145, 225);

    public ProfileView(Controller controller, MainFrame mainFrame) {
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.mainContentPanel = mainFrame.getContentPanel();
        CreateProfileView();
    }

    public void createButton(){
        btnShowInfo = new JButton("Show profileinfo");
        btnShowInfo.setBackground(menuColor);
        btnShowInfo.setForeground(Color.white);
        btnShowInfo.setFont(new Font("Dialog", Font.BOLD, 16));
        addActionListener(btnShowInfo);

        btnChangeInfo = new JButton("Change profileinfo");
        btnChangeInfo.setBackground(menuColor);
        btnChangeInfo.setForeground(Color.white);
        addActionListener(btnChangeInfo);
        btnChangeInfo.setFont(new Font("Dialog", Font.BOLD, 16));

        btnChange = new JButton("Edit changes");
        btnChange.setBackground(menuColor);
        btnChange.setForeground(Color.white);
        addActionListener(btnChange);
        btnChange.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    public void createMainPanels(){
        buttonPanel = new JPanel();
        buttonPanel.setBounds(mainContentPanel.getX() + 10, 10, mainContentPanel.getWidth()/2, mainContentPanel.getHeight()/10 );
        buttonPanel.setLayout(new GridLayout(1,2,10,10));

        buttonPanel.add(btnShowInfo);
        buttonPanel.add(btnChangeInfo);

        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(mainContentPanel.getX() + 10, mainContentPanel.getY() + buttonPanel.getHeight() + 12, mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - mainContentPanel.getHeight()/6);
        infoPanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        setInfoPanelDetails();

        changePanel = new JPanel();
        changePanel.setLayout(null);
        changePanel.setBounds(mainContentPanel.getX() + 10, mainContentPanel.getY() + buttonPanel.getHeight() + 12, mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - mainContentPanel.getHeight()/6);
        changePanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        setChangePanelDetails();

        currentPanelOnDisplay = infoPanel;
        mainContentPanel.add(buttonPanel);
        mainContentPanel.add(currentPanelOnDisplay);

    }

    public void createLabel(){
        roleLabel = new JLabel();
        roleLabel.setText("Role of user");
        roleLabel.setForeground(menuColor);
        roleLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        firstnameLabel = new JLabel();
        firstnameLabel.setText("Firstname:");
        firstnameLabel.setForeground(menuColor);
        firstnameLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        lastnameLabel = new JLabel();
        lastnameLabel.setText("Lastname:");
        lastnameLabel.setForeground(menuColor);
        lastnameLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        emailLabel = new JLabel();
        emailLabel.setText("Email:");
        emailLabel.setForeground(menuColor);
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        infoFirstname = new JLabel();
        infoFirstname.setText("Text of Firstname");
        infoFirstname.setFont(new Font("Dialog", Font.BOLD, 20));
        infoFirstname.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        infolastname = new JLabel();
        infolastname.setText("Text of lastname");
        infolastname.setFont(new Font("Dialog", Font.BOLD, 20));
        infolastname.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        infoEmail = new JLabel();
        infoEmail.setText("Text of Email");
        infoEmail.setFont(new Font("Dialog", Font.BOLD, 16));
        infoEmail.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        infoInputFirstname = new JLabel();
        //infoInputFirstname.setText(controller.getSignedInUser().getFirstName());
        infoInputFirstname.setText("firstname from inut");
        infoInputFirstname.setForeground(menuColor);
        infoInputFirstname.setFont(new Font("Dialog", Font.BOLD, 20));

        infoInputLastname = new JLabel();
        //infoInputLastname.setText(controller.getSignedInUser().getLastName());
        infoInputLastname.setText("lastname from input");
        infoInputLastname.setForeground(menuColor);
        infoInputLastname.setFont(new Font("Dialog", Font.BOLD, 20));

        infoInputEmail = new JLabel();
        //String userEmail = controller.getSignedInUser().getEmail();
        infoInputEmail.setText("userEmail");
        infoInputEmail.setForeground(menuColor);
        infoInputEmail.setFont(new Font("Dialog", Font.BOLD, 20));

        roleInfoLabel = new JLabel();
        roleInfoLabel.setText("Role of user");
        roleInfoLabel.setForeground(menuColor);
        roleInfoLabel.setFont(new Font("Dialog", Font.BOLD, 20));
    }


    public void creatInputField(){
        fName = new JTextField();
        //fName.setText(controller.getSignedInUser().getFirstName());
        fName.setText("Förnamn");
        fName.setForeground(menuColor);
        fName.setFont(new Font("Dialog", Font.PLAIN, 16));
        fName.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        lName = new JTextField();
        lName.setText("Lastname");
        lName.setForeground(menuColor);
        lName.setFont(new Font("Dialog", Font.PLAIN, 16));
        lName.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        eMail = new JTextField();
        eMail.setText("LogIn user email");
        eMail.setForeground(menuColor);
        eMail.setFont(new Font("Dialog", Font.PLAIN, 16));
        eMail.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        infoBox = new JTextArea();
        infoBox.setText("Write your infomation here");
        infoBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        infoBox.setSize(50, 100);

        infoArea = new JTextPane();
        infoArea.setFont(new Font("Dialog", Font.BOLD, 16));
        infoArea.setText("Userinformation about every\n user to get\n the right user\n for tickets\n\n\n\n\n");
        infoArea.setSize(50, 100);
        infoArea.setEditable(false);
    }

    public void setInfoPanelDetails() {
        //set details for infoPanel
        imagePanel = new JPanel();
        imagePanel.setBounds(infoPanel.getX() - infoPanel.getX()*5/6, infoPanel.getY() - infoPanel.getY()*4/5, infoPanel.getWidth()/4, infoPanel.getHeight()/6);
        imagePanel.setBackground(menuColor);

        topInfoPanel = new JPanel();
        topInfoPanel.setBounds(imagePanel.getX(), imagePanel.getY()+imagePanel.getHeight() + 10, infoPanel.getWidth()/3, infoPanel.getHeight()*2/8);
        topInfoPanel.setLayout(new GridLayout(4, 1));

        topInfoPanel.add(infoInputFirstname);
        topInfoPanel.add(infoFirstname);
        topInfoPanel.add(infoInputLastname);
        topInfoPanel.add(infolastname);

        middleInfoPanel = new JPanel();
        middleInfoPanel.setBounds(topInfoPanel.getX(), topInfoPanel.getY() + topInfoPanel.getHeight() +5 , infoPanel.getWidth()/3, (infoPanel.getHeight() - imagePanel.getHeight())/5);
        middleInfoPanel.setLayout(new GridLayout(2, 1));
        middleInfoPanel.add(infoInputEmail);
        middleInfoPanel.add(infoEmail);

        loweInfoPanel = new JPanel();
        loweInfoPanel.setBounds(imagePanel.getX(), middleInfoPanel.getY() + middleInfoPanel.getHeight() + 5, infoPanel.getWidth()/3, infoArea.getHeight() * 7/3);
        loweInfoPanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        loweInfoPanel.add(infoArea);

        roleInfoPanel = new JPanel();
        roleInfoPanel.setBounds(infoPanel.getWidth()*2/3,infoPanel.getY() - 80, infoPanel.getWidth()*2/7, infoPanel.getHeight()/7);
        roleInfoPanel.add(roleInfoLabel);

        infoPanel.add(imagePanel);
        infoPanel.add(topInfoPanel);
        infoPanel.add(middleInfoPanel);
        infoPanel.add(loweInfoPanel);
        infoPanel.add(roleInfoPanel);
        currentPanelOnDisplay = infoPanel;

    }
    public void setChangePanelDetails() {
        //set details for infoPanel
        imageChangePanel = new JPanel();
        imageChangePanel.setBounds(changePanel.getX() - changePanel.getX()*5/6, changePanel.getY() - changePanel.getY()*4/5, changePanel.getWidth()/4, changePanel.getHeight()/6);
        imageChangePanel.setBackground(menuColor);

        topChangePanel = new JPanel();
        topChangePanel.setBounds(imageChangePanel.getX(), imageChangePanel.getY()+imageChangePanel.getHeight() + 10, changePanel.getWidth()/3, changePanel.getHeight()/3);
        topChangePanel.setLayout(new GridLayout(4, 2));

        topChangePanel.add(firstnameLabel);
        topChangePanel.add(fName);
        topChangePanel.add(lastnameLabel);
        topChangePanel.add(lName);

        middleChangePanel = new JPanel();
        middleChangePanel.setBounds(topChangePanel.getX(), changePanel.getY()+5 + imageChangePanel.getHeight() + topChangePanel.getHeight() -80, changePanel.getWidth()/3, (changePanel.getHeight() - imageChangePanel.getHeight())/6);
        middleChangePanel.setLayout(new GridLayout(2, 1));
        middleChangePanel.add(emailLabel);
        middleChangePanel.add(eMail);

        lowerChangePanel = new JPanel();
        lowerChangePanel.setBounds(imageChangePanel.getX(), middleChangePanel.getY() + middleChangePanel.getHeight() + 5, changePanel.getWidth()/3, infoBox.getHeight()*6/3);
        lowerChangePanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        lowerChangePanel.add(infoBox);

        roleChangePanel = new JPanel();
        roleChangePanel.setBounds(changePanel.getWidth()*2/3,changePanel.getY() - 80, changePanel.getWidth()*2/7, changePanel.getHeight()/7);
        roleChangePanel.add(roleLabel);

        changeBtnPanel = new JPanel();
        changeBtnPanel.setBounds(changePanel.getWidth() * 2/3, changePanel.getY() + (changePanel.getHeight()* 5/7), changePanel.getWidth()/ 4, changePanel.getHeight()/8);
        changeBtnPanel.add(btnChange);


        changePanel.add(imageChangePanel);
        changePanel.add(topChangePanel);
        changePanel.add(middleChangePanel);
        changePanel.add(lowerChangePanel);
        changePanel.add(roleChangePanel);
        changePanel.add(changeBtnPanel);
        currentPanelOnDisplay = changePanel;

    }

    public void changeToChangeInfoview(){
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(changePanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = changePanel;

    }

    public void changetoInfoView() {
        mainContentPanel.remove(currentPanelOnDisplay);
        mainContentPanel.add(infoPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = infoPanel;
    }


    public void CreateProfileView(){
        createLabel();
        creatInputField();
        createButton();
        createMainPanels();
    }
    /**
     * Adds actionlistener to the buttons.
     * */
    private void addActionListener(JButton btn){
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnChangeInfo)){
            changeToChangeInfoview();
        }
        if (e.getSource().equals(btnShowInfo)){
            changetoInfoView();
        }
        if (e.getSource().equals(btnChange)){

        }
    }

}



