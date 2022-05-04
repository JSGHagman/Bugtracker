package View.MainView.ProfileView;

import Controller.Controller;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfileView implements ActionListener {

    private JPanel mainContentPanel, infoPanel, changePanel, buttonPanel;
    private JTextField fName, lName, password, eMail;
    private JLabel randomeText;
    private JButton btnChangeInfo, btnShowInfo;

    private String mail;
    private String logInPassword;
    private String logInFname;
    private String logInLname;

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

    public void createLabel(){
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
/*
    private void setButtonDesign(JButton btn){
        btn.setBackground(menuColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Dialog", Font.BOLD, 16));
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
     */



    public void createMainPanels(){
        buttonPanel = new JPanel();
        buttonPanel.setBounds(mainContentPanel.getX() + 10, 10, mainContentPanel.getWidth()/2, mainContentPanel.getHeight()/10 );
        buttonPanel.setLayout(new GridLayout(1,2,10,10));
        buttonPanel.setBackground(Color.black);

        infoPanel = new JPanel();
        infoPanel.setBounds(mainContentPanel.getX() + 10, mainContentPanel.getY() + buttonPanel.getHeight(), mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - 150);
        infoPanel.setBackground(Color.GREEN);

        changePanel = new JPanel();
        changePanel = new JPanel();
        changePanel.setBounds(mainContentPanel.getX() + 10, mainContentPanel.getY() + buttonPanel.getHeight(), mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - 150);
        changePanel.setBackground(Color.BLUE);

        mainContentPanel.add(infoPanel);
        mainContentPanel.add(buttonPanel);

    }




    public void creatInputField(){
        fName = new JTextField();
        //fName.setText(controller.getSignedInUser().getFirstName());
        fName.setText("Förnamn");
        fName.setFont(new Font("Firstname", Font.BOLD, 16));
        fName.setForeground(menuColor);
        //fName.setBorder(BorderFactory.createLineBorder(menuColor, 3, false));

        lName = new JTextField();
        lName.setText(controller.getSelectedRole());
        lName.setForeground(menuColor);
        lName.setFont(new Font("Lastname", Font.BOLD, 16));

        eMail = new JTextField();
        eMail.setText("LogIn user email");
        eMail.setFont(new Font("E-mail", Font.BOLD, 16));




        //infoPanel.add(lName);
        //infoPanel.add(fName);
        //infoPanel.add(eMail);
    }

    private void setCreatePanelDetails() {
        //set details for infoPlane


    }

    public void CreateProfileView(){
        createMainPanels();
        creatInputField();
        createButton();
    }

    public void setEditPanelDetails(){

    }
    /**
     * Adds actionlistener to the buttons.
     * */
    private void addActionListener(JButton btn){
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}



