package View.MainView.ProfileView;

import Controller.Controller;
import View.MainView.MainFrame.MainFrame;
import View.OldTicketGui.CenterPanel;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfileView implements ActionListener {

    private JPanel mainContentPanel, infoPanel, changePanel, buttonPanel, currentPanelOnDisplay;
    private JTextField fName, lName, password, eMail;
    private JLabel randomeText, firstnameLabel, lastnameLabel, emailLabel, roleLabel;
    private JTextArea infoBox;
    private JButton btnChangeInfo, btnShowInfo;
    private ImageIcon profilePicture;

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

        btnChangeInfo = new JButton("Change profileinfo");
        btnChangeInfo.setBackground(menuColor);
        btnChangeInfo.setForeground(Color.white);
        btnChangeInfo.setFont(new Font("Dialog", Font.BOLD, 16));
    }



    public void createMainPanels(){
        buttonPanel = new JPanel();
        buttonPanel.setBounds(mainContentPanel.getX() + 10, 10, mainContentPanel.getWidth()/2, mainContentPanel.getHeight()/10 );
        buttonPanel.setLayout(new GridLayout(1,2,10,10));

        buttonPanel.add(btnChangeInfo);
        buttonPanel.add(btnShowInfo);

        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(mainContentPanel.getX() + 10, mainContentPanel.getY() + buttonPanel.getHeight() + 12, mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - mainContentPanel.getHeight()/6);
        infoPanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        setInfoPanelDetails();



        changePanel = new JPanel();
        changePanel = new JPanel();
        changePanel.setBounds(mainContentPanel.getX() + 10, mainContentPanel.getY() + buttonPanel.getHeight() + 12, mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - mainContentPanel.getHeight()/6);
        changePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        //setChangePanelDetails();



        mainContentPanel.add(infoPanel);
        //mainContentPanel.add(changePanel);
        mainContentPanel.add(buttonPanel);

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
        infoBox.setSize(50, 220);

    }

    private void setInfoPanelDetails() {
        //set details for infoPanel

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(infoPanel.getX() - 110, infoPanel.getY() - 80, infoPanel.getWidth()/3, infoPanel.getHeight()/7);
        imagePanel.setBackground(menuColor);

        JPanel topInfoPanel = new JPanel();
        topInfoPanel.setBounds(imagePanel.getX(), imagePanel.getY()+imagePanel.getHeight() + 10, infoPanel.getWidth()/3, infoPanel.getHeight()/3);
        topInfoPanel.setLayout(new GridLayout(4, 2));

        topInfoPanel.add(firstnameLabel);
        topInfoPanel.add(fName);
        topInfoPanel.add(lastnameLabel);
        topInfoPanel.add(lName);


        JPanel middleInfoPanel = new JPanel();
        middleInfoPanel.setBounds(topInfoPanel.getX(), infoPanel.getY()+5 + imagePanel.getHeight() + topInfoPanel.getHeight() -80, infoPanel.getWidth()/3, (infoPanel.getHeight() - imagePanel.getHeight())/6);
        middleInfoPanel.setLayout(new GridLayout(2, 1));
        middleInfoPanel.add(emailLabel);
        middleInfoPanel.add(eMail);

        JPanel loweInfoPanel = new JPanel();
        loweInfoPanel.setBounds(imagePanel.getX(), middleInfoPanel.getY() + middleInfoPanel.getHeight() + 5, infoPanel.getWidth()/3, infoBox.getHeight());
        loweInfoPanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        loweInfoPanel.add(infoBox);

        JPanel roleInfoPanel = new JPanel();
        roleInfoPanel.setBounds(infoPanel.getWidth()*2/3,infoPanel.getY() - 80, infoPanel.getWidth()*2/7, infoPanel.getHeight()/7);
        roleInfoPanel.add(roleLabel);

        infoPanel.add(imagePanel);
        infoPanel.add(topInfoPanel);
        infoPanel.add(middleInfoPanel);
        infoPanel.add(loweInfoPanel);
        infoPanel.add(roleInfoPanel);

    }
    private void setChangePanelDetails() {
        //set details for infoPanel


        JPanel imageChangePanel = new JPanel();
        imageChangePanel.setBounds(changePanel.getX() - 110, changePanel.getY() - 80, changePanel.getWidth()/3, changePanel.getHeight()/7);
        imageChangePanel.setBackground(menuColor);

        JPanel topChangePanel = new JPanel();
        //topInfoPanel.setBounds(infoPanel.getX()+5, infoPanel.getY()+5 + imagePanel.getHeight(), infoPanel.getWidth()/3, (infoPanel.getHeight() - imagePanel.getHeight())/3);
        topChangePanel.setBounds(imageChangePanel.getX(), imageChangePanel.getY()+imageChangePanel.getHeight() + 10, changePanel.getWidth()/3, changePanel.getHeight()/3);
        topChangePanel.setLayout(new GridLayout(4, 2));

        topChangePanel.add(firstnameLabel);
        topChangePanel.add(fName);
        topChangePanel.add(lastnameLabel);
        topChangePanel.add(lName);


        JPanel middleChangePanel = new JPanel();
        middleChangePanel.setBounds(topChangePanel.getX(), changePanel.getY()+5 + imageChangePanel.getHeight() + topChangePanel.getHeight() -80, changePanel.getWidth()/3, (changePanel.getHeight() - imageChangePanel.getHeight())/6);
        middleChangePanel.setLayout(new GridLayout(2, 1));
        middleChangePanel.add(emailLabel);
        middleChangePanel.add(eMail);

        JPanel lowerChangePanel = new JPanel();
        lowerChangePanel.setBounds(imageChangePanel.getX(), middleChangePanel.getY() + middleChangePanel.getHeight() + 5, changePanel.getWidth()/3, infoBox.getHeight());
        lowerChangePanel.setBorder(BorderFactory.createLineBorder(menuColor, 3));
        lowerChangePanel.add(infoBox);

        JPanel roleChangePanel = new JPanel();
        roleChangePanel.setBounds(changePanel.getWidth()*2/3,changePanel.getY() - 80, changePanel.getWidth()*2/7, changePanel.getHeight()/7);
        roleChangePanel.add(roleLabel);

        changePanel.add(imageChangePanel);
        changePanel.add(topChangePanel);
        changePanel.add(middleChangePanel);
        changePanel.add(lowerChangePanel);
        changePanel.add(roleChangePanel);

    }

    /*public void changeToChangeInfoView(){
        mainContentPanel.remove();
        mainContentPanel.add(infoPanel);
        mainFrame.add(buttonPanel);
        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();
        currentPanelOnDisplay = infoPanel;
    }

     */

    public void CreateProfileView(){
        creatInputField();
        createButton();
        createLabel();
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
            //mainContentPanel.add(changePanel);
            //setChangePanelDetails();
            System.out.println("byt till changeInfo");
        }
        if (e.getSource().equals(btnShowInfo)){
            //changeToChangeInfoView();
            System.out.println("Byt till info");
        }
    }
}



