package View.MainView.ProfileView;

import Controller.Controller;
import Model.UserManager;
import View.MainView.MainFrame.MainFrame;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfileView implements ActionListener {

    private JPanel mainContentPanel, infoPanel, changePanel, buttonPanel, currentPanelOnDisplay, imagePanel, topInfoPanel, middleInfoPanel, loweInfoPanel, roleInfoPanel;
    private JPanel changeBtnPanel, imageChangePanel, topChangePanel, middleChangePanel, lowerChangePanel, roleChangePanel;
    private JTextField fName, lName, passwordField, passwordChange;
    private JLabel roleInfoLabel, firstnameLabel, lastnameLabel, passwordLabel, roleLabel, infoFirstname, infolastname,infoEmail, infoInputFirstname, infoInputLastname, infoInputEmail;
    private JTextArea infoBox;
    private JButton btnChangeInfo, btnShowInfo, btnChange;
    private JTextPane infoArea;

    private String email;

    //GENERAL
    private Controller controller;
    private MainFrame mainFrame;
    private Color menuColor = new Color(65, 105, 225);
    private Color hoverColor = new Color(65, 145, 225);
    private Object image;

    public ProfileView(Controller controller, MainFrame mainFrame) {
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.mainContentPanel = mainFrame.getContentPanel();
    }
    /*public void setProfilePicture() throws IOException {
        BufferedImage myPicture = ImageIO. read(new File("Images/bugTrackerIcon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    }

     */

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
        buttonPanel.setBounds(mainContentPanel.getX() + mainContentPanel.getWidth() / 14 + 10, 10, mainContentPanel.getWidth()/2, mainContentPanel.getHeight()/10 );
        buttonPanel.setLayout(new GridLayout(1,2,10,10));

        buttonPanel.add(btnShowInfo);
        buttonPanel.add(btnChangeInfo);

        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(mainContentPanel.getX() + mainContentPanel.getWidth() / 14 + 10 , mainContentPanel.getY() + buttonPanel.getHeight() + 12, mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - mainContentPanel.getHeight()/6);
        infoPanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        setInfoPanelDetails();

        changePanel = new JPanel();
        changePanel.setLayout(null);
        changePanel.setBounds(mainContentPanel.getX() + mainContentPanel.getWidth() / 14 + 10, mainContentPanel.getY() + buttonPanel.getHeight() + 12, mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - mainContentPanel.getHeight()/6);
        changePanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        setChangePanelDetails();

        currentPanelOnDisplay = infoPanel;
        mainContentPanel.add(buttonPanel);
        mainContentPanel.add(currentPanelOnDisplay);
    }

    public void createLabel(){
        roleLabel = new JLabel();
        roleLabel.setText(controller.getSignedInUser().getRole());
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

        passwordLabel = new JLabel();
        passwordLabel.setText("Email:");
        passwordLabel.setForeground(menuColor);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        infoFirstname = new JLabel();
        infoFirstname.setText(controller.getSignedInUser().getFirstName());
        infoFirstname.setFont(new Font("Dialog", Font.BOLD, 20));
        infoFirstname.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        infolastname = new JLabel();
        infolastname.setText(controller.getSignedInUser().getLastName());
        infolastname.setFont(new Font("Dialog", Font.BOLD, 20));
        infolastname.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        infoEmail = new JLabel();
        infoEmail.setText(controller.getSignedInUser().getEmail());
        infoEmail.setFont(new Font("Dialog", Font.BOLD, 16));
        infoEmail.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        infoInputFirstname = new JLabel();
        infoInputFirstname.setText("Firstname");
        infoInputFirstname.setForeground(menuColor);
        infoInputFirstname.setFont(new Font("Dialog", Font.BOLD, 20));

        infoInputLastname = new JLabel();
        infoInputLastname.setText("Lastname");
        infoInputLastname.setForeground(menuColor);
        infoInputLastname.setFont(new Font("Dialog", Font.BOLD, 20));

        infoInputEmail = new JLabel();
        infoInputEmail.setText("Email");
        infoInputEmail.setForeground(menuColor);
        infoInputEmail.setFont(new Font("Dialog", Font.BOLD, 20));

        roleInfoLabel = new JLabel();
        roleInfoLabel.setText(controller.getSignedInUser().getRole());
        roleInfoLabel.setForeground(menuColor);
        roleInfoLabel.setFont(new Font("Dialog", Font.BOLD, 20));
    }

    public void creatInputField(){
        fName = new JTextField();
        fName.setText(controller.getSignedInUser().getFirstName());
        fName.setForeground(menuColor);
        fName.setFont(new Font("Dialog", Font.PLAIN, 16));
        fName.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        lName = new JTextField();
        lName.setText(controller.getSignedInUser().getLastName());
        lName.setForeground(menuColor);
        lName.setFont(new Font("Dialog", Font.PLAIN, 16));
        lName.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        passwordChange = new JTextField();
        passwordChange.setText("Passwordbox");
        passwordChange.setForeground(menuColor);
        passwordChange.setFont(new Font("Dialog", Font.PLAIN, 16));
        passwordChange.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        infoBox = new JTextArea();
        infoBox.setText("Write your infomation here");
        infoBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        infoBox.setSize((mainContentPanel.getHeight()/8) / 2, mainContentPanel.getHeight()/8);

        infoArea = new JTextPane();
        infoArea.setFont(new Font("Dialog", Font.BOLD, 16));
        infoArea.setText("Userinformation about every\n user to get\n the right user\n for tickets\n\n\n\n\n");
        infoArea.setSize((mainContentPanel.getHeight()/8) / 2, mainContentPanel.getHeight()/8);
        infoArea.setEditable(false);
    }

    public void setInfoPanelDetails() {
        //set details for infoPanel
        imagePanel = new JPanel();
        imagePanel.setBounds(infoPanel.getX() - infoPanel.getX()*5/6, infoPanel.getY() - infoPanel.getY()*4/5, infoPanel.getWidth()/4, infoPanel.getHeight()/6);
        imagePanel.createImage(imagePanel.getWidth(), imagePanel.getHeight());
        imagePanel.setBackground(menuColor);

        topInfoPanel = new JPanel();
        topInfoPanel.setBounds(imagePanel.getX(), imagePanel.getY()+imagePanel.getHeight() + 10, infoPanel.getWidth()/3, infoPanel.getHeight()/3);
        topInfoPanel.setLayout(new GridLayout(4, 1));


        topInfoPanel.add(infoInputFirstname);
        topInfoPanel.add(infoFirstname);
        topInfoPanel.add(infoInputLastname);
        topInfoPanel.add(infolastname);

        middleInfoPanel = new JPanel();
        middleInfoPanel.setBounds(topInfoPanel.getX(), infoPanel.getY()+5 + imagePanel.getHeight() + topInfoPanel.getHeight() - (mainContentPanel.getHeight() / 10), infoPanel.getWidth()/3, (infoPanel.getHeight() - imagePanel.getHeight())/6);
        middleInfoPanel.setLayout(new GridLayout(2, 1));
        middleInfoPanel.add(infoInputEmail);
        middleInfoPanel.add(infoEmail);

        loweInfoPanel = new JPanel();
        loweInfoPanel.setBounds(imagePanel.getX(), middleInfoPanel.getY() + middleInfoPanel.getHeight() + 5, infoPanel.getWidth()/3, infoBox.getHeight());
        loweInfoPanel.add(infoArea);
        loweInfoPanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));

        roleInfoPanel = new JPanel();
        roleInfoPanel.setBounds(infoPanel.getWidth()*2/3,infoPanel.getY() - (mainContentPanel.getHeight()/10), infoPanel.getWidth()*2/7, infoPanel.getHeight()/7);
        roleInfoPanel.add(roleInfoLabel);
        roleInfoLabel.setBorder(BorderFactory.createLineBorder(menuColor, 3));

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
        middleChangePanel.setBounds(topChangePanel.getX(), changePanel.getY()+5 + imageChangePanel.getHeight() + topChangePanel.getHeight() - (mainContentPanel.getHeight() / 10), changePanel.getWidth()/3, (changePanel.getHeight() - imageChangePanel.getHeight())/6);
        middleChangePanel.setLayout(new GridLayout(2, 1));
        middleChangePanel.add(passwordLabel);
        middleChangePanel.add(passwordChange);

        lowerChangePanel = new JPanel();
        lowerChangePanel.setBounds(imageChangePanel.getX(), middleChangePanel.getY() + middleChangePanel.getHeight() + 5, changePanel.getWidth()/3, infoBox.getHeight());
        lowerChangePanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        lowerChangePanel.add(infoBox);

        roleChangePanel = new JPanel();
        roleChangePanel.setBounds(changePanel.getWidth()*2/3,changePanel.getY() - (mainContentPanel.getHeight()/10), changePanel.getWidth()*2/7, changePanel.getHeight()/7);
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

    public void changeInfo(){
        String firstName = fName.getText();
        String lastName = lName.getText();
        String passwordChangeText = controller.getSignedInUser().getPassword();
        email = infoEmail.getText();

        if (passwordChangeText.contains(controller.getSignedInUser().getPassword())){
            passwordChangeText = controller.getSignedInUser().getPassword();
        }

        lastName = lName.getText();
        infolastname.setText(lastName);
        firstName = fName.getText();
        infoFirstname.setText(firstName);
        passwordChangeText = passwordLabel.getText();
        controller.updateUserDB(firstName, lastName, email, passwordChangeText, controller.getSignedInUser().getRole() );
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
            changeInfo();

        }
    }

}



