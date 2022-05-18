package View.MainView.ProfileView;
import Controller.Controller;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.Format;

public class ProfileView implements ActionListener {

    //Swing Objects
    private JPanel mainContentPanel, infoPanel, changePanel, buttonPanel, currentPanelOnDisplay, imagePanel, topInfoPanel, middleInfoPanel, loweInfoPanel, roleInfoPanel;
    private JPanel changeBtnPanel, imageChangePanel, topChangePanel, middleChangePanel, lowerChangePanel, roleChangePanel;
    private JTextField fName, lName, eMail;
    private JLabel confirmPasswordLabel , roleInfoLabel, firstnameLabel, lastnameLabel, passwordLabel, roleLabel, infoFirstname, infolastname,infoEmail, infoInputFirstname, infoInputLastname, infoInputEmail;
    private JTextArea infoBox;
    private JPasswordField passwordField, tfConfirmPassword;
    private JButton btnChangeInfo, btnShowInfo, btnChange, btnCancel;
    private JTextPane infoArea;
    private JScrollPane infoScroll, changeScroll;

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
        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(menuColor);
        btnCancel.setForeground(Color.white);
        addActionListener(btnCancel);
        btnCancel.setFont(new Font("Dialog", Font.BOLD, 16));

        btnChangeInfo = new JButton("Change profileinfo");
        btnChangeInfo.setBackground(menuColor);
        btnChangeInfo.setForeground(Color.white);
        addActionListener(btnChangeInfo);
        btnChangeInfo.setFont(new Font("Dialog", Font.BOLD, 16));

        btnChange = new JButton("Save");
        btnChange.setBackground(menuColor);
        btnChange.setForeground(Color.white);
        addActionListener(btnChange);
        btnChange.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    public void createMainPanels(){
        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(mainContentPanel.getX() + mainContentPanel.getWidth() / 14 + 10 , mainContentPanel.getY() + 12, mainContentPanel.getWidth()/2, mainContentPanel.getHeight()- (mainContentPanel.getHeight()/9));
        infoPanel.setBorder(BorderFactory.createLineBorder(menuColor, 1));
        setInfoPanelDetails();

        changePanel = new JPanel();
        changePanel.setLayout(null);
        changePanel.setBounds(mainContentPanel.getX() + mainContentPanel.getWidth() / 14 + 10, mainContentPanel.getY()+ 12, mainContentPanel.getWidth()/2, mainContentPanel.getHeight() - (mainContentPanel.getHeight()/9));
        changePanel.setBorder(BorderFactory.createLineBorder(menuColor, 1));
        setChangePanelDetails();

        currentPanelOnDisplay = infoPanel;
        mainContentPanel.add(currentPanelOnDisplay);
    }

    public void createLabel(){
        confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setText("Confirm password");
        confirmPasswordLabel.setForeground(menuColor);
        confirmPasswordLabel.setFont(new Font("Dialog", Font.BOLD, 12));

        roleLabel = new JLabel();
        roleLabel.setText(controller.getSignedInUser().getRole());
        roleLabel.setForeground(menuColor);
        roleLabel.setFont(new Font("Dialog", Font.BOLD, 12));

        firstnameLabel = new JLabel();
        firstnameLabel.setText("Firstname:");
        firstnameLabel.setForeground(menuColor);
        firstnameLabel.setFont(new Font("Dialog", Font.BOLD, 12));

        lastnameLabel = new JLabel();
        lastnameLabel.setText("Lastname:");
        lastnameLabel.setForeground(menuColor);
        lastnameLabel.setFont(new Font("Dialog", Font.BOLD, 12));

        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setForeground(menuColor);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 12));

        infoFirstname = new JLabel();
        infoFirstname.setText(controller.getSignedInUser().getFirstName());
        infoFirstname.setFont(new Font("Dialog", Font.BOLD, 12));
        infoFirstname.setBorder(BorderFactory.createLineBorder(menuColor, 1));

        infolastname = new JLabel();
        infolastname.setText(controller.getSignedInUser().getLastName());
        infolastname.setFont(new Font("Dialog", Font.BOLD, 12));
        infolastname.setBorder(BorderFactory.createLineBorder(menuColor, 1));

        infoEmail = new JLabel();
        infoEmail.setText(controller.getSignedInUser().getEmail());
        infoEmail.setFont(new Font("Dialog", Font.BOLD, 12));
        infoEmail.setBorder(BorderFactory.createLineBorder(menuColor, 1));

        infoInputFirstname = new JLabel();
        infoInputFirstname.setText("Firstname");
        infoInputFirstname.setForeground(menuColor);
        infoInputFirstname.setFont(new Font("Dialog", Font.BOLD, 12));

        infoInputLastname = new JLabel();
        infoInputLastname.setText("Lastname");
        infoInputLastname.setForeground(menuColor);
        infoInputLastname.setFont(new Font("Dialog", Font.BOLD, 12));

        infoInputEmail = new JLabel();
        infoInputEmail.setText("Email");
        infoInputEmail.setForeground(menuColor);
        infoInputEmail.setFont(new Font("Dialog", Font.BOLD, 12));

        roleInfoLabel = new JLabel();
        roleInfoLabel.setText(controller.getSignedInUser().getRole());
        roleInfoLabel.setForeground(menuColor);
        roleInfoLabel.setFont(new Font("Dialog", Font.BOLD, 12));
    }

    public void creatInputField(){
        tfConfirmPassword = new JPasswordField();
        tfConfirmPassword.setText("");
        tfConfirmPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
        tfConfirmPassword.setBorder(BorderFactory.createLineBorder(menuColor, 1));

        fName = new JTextField();
        fName.setText("");
        fName.setForeground(menuColor);
        fName.setFont(new Font("Dialog", Font.PLAIN, 12));
        fName.setBorder(BorderFactory.createLineBorder(menuColor, 1));

        lName = new JTextField();
        lName.setText("");
        lName.setForeground(menuColor);
        lName.setFont(new Font("Dialog", Font.PLAIN, 12));
        lName.setBorder(BorderFactory.createLineBorder(menuColor, 1));

        passwordField = new JPasswordField();
        passwordField.setForeground(menuColor);
        passwordField.setText("");
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 12));
        passwordField.setBorder(BorderFactory.createLineBorder(menuColor, 1));

        infoBox = new JTextArea();
        infoBox.setFont(new Font("Dialog", Font.PLAIN,  12));
        infoBox.setText("Personal \n skills");

        infoArea = new JTextPane();
        infoArea.setFont(new Font("Dialog", Font.BOLD, 12));
        infoArea.setText(infoBox.getText());
        infoArea.setEditable(false);

        infoScroll = new JScrollPane(infoArea);
        infoScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        changeScroll = new JScrollPane(infoBox);
        changeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
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
        middleInfoPanel.setBounds(topInfoPanel.getX(), infoPanel.getY()+5 + imagePanel.getHeight() + topInfoPanel.getHeight(), infoPanel.getWidth()/3, (infoPanel.getHeight() - imagePanel.getHeight())/6);
        middleInfoPanel.setLayout(new GridLayout(2, 1));
        middleInfoPanel.add(infoInputEmail);
        middleInfoPanel.add(infoEmail);

        loweInfoPanel = new JPanel();
        loweInfoPanel.setBounds(imagePanel.getX(), middleInfoPanel.getY() + middleInfoPanel.getHeight() + 5, infoPanel.getWidth()/3,  mainContentPanel.getHeight()/4);
        loweInfoPanel.setLayout(new GridLayout(1, 1));
        loweInfoPanel.add(infoScroll);
        loweInfoPanel.setBorder(BorderFactory.createLineBorder(menuColor, 1));

        roleInfoPanel = new JPanel();
        roleInfoPanel.setBounds(infoPanel.getWidth()*2/3,infoPanel.getY() - (mainContentPanel.getHeight()/6), infoPanel.getWidth()*2/7, infoPanel.getHeight()/7);
        roleInfoPanel.add(roleInfoLabel);

        changeBtnPanel = new JPanel();
        changeBtnPanel.setBounds(infoPanel.getWidth() * 2/3, infoPanel.getY() + (infoPanel.getHeight()* 6/7), infoPanel.getWidth()/ 4, infoPanel.getHeight()/8);
        changeBtnPanel.add(btnChangeInfo);

        infoPanel.add(imagePanel);
        infoPanel.add(topInfoPanel);
        infoPanel.add(middleInfoPanel);
        infoPanel.add(loweInfoPanel);
        infoPanel.add(roleInfoPanel);
        infoPanel.add(changeBtnPanel);
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
        middleChangePanel.setBounds(topChangePanel.getX(), changePanel.getY()+5 + imageChangePanel.getHeight() + topChangePanel.getHeight(), changePanel.getWidth()/3, (changePanel.getHeight()/4));
        middleChangePanel.setLayout(new GridLayout(4, 1));
        middleChangePanel.add(passwordLabel);
        middleChangePanel.add(passwordField);
        middleChangePanel.add(confirmPasswordLabel);
        middleChangePanel.add(tfConfirmPassword);

        lowerChangePanel = new JPanel();
        lowerChangePanel.setBounds(imageChangePanel.getX(), middleChangePanel.getY() + middleChangePanel.getHeight() + 5, changePanel.getWidth()/3, mainContentPanel.getHeight()/8);
        lowerChangePanel.setBorder(BorderFactory.createLineBorder(menuColor, 1));
        lowerChangePanel.setLayout(new GridLayout(1, 1));
        lowerChangePanel.add(changeScroll);

        roleChangePanel = new JPanel();
        roleChangePanel.setBounds(changePanel.getWidth()*2/3,changePanel.getY() - (mainContentPanel.getHeight()/6), changePanel.getWidth()*2/7, changePanel.getHeight()/7);
        roleChangePanel.add(roleLabel);

        changeBtnPanel = new JPanel();
        changeBtnPanel.setBounds(changePanel.getWidth() * 2/3, changePanel.getY() + (changePanel.getHeight()* 6/7), changePanel.getWidth()/ 4, changePanel.getHeight()/8);
        changeBtnPanel.add(btnChange);
        changeBtnPanel.add(btnCancel);


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
        String firstName = "";
        String lastName = "";
        String passwordChangeText = "";
        String email = controller.getSignedInUser().getEmail();
        char[] changedPassword = passwordField.getPassword();

        if(fName.getText().equals("")){
            firstName = controller.getSignedInUser().getFirstName();
        }
        else{
            firstName = fName.getText();
        }
        if (lName.getText().equals("")){
            lastName = controller.getSignedInUser().getLastName();
        }
        else{
            lastName = lName.getText();
        }
        if(passwordField.getPassword().length == 0){
            passwordChangeText = controller.getSignedInUser().getPassword();
        }
        if ((passwordField.getPassword().length > 0) && (passwordField.getPassword() == tfConfirmPassword.getPassword())){
            passwordChangeText = String.valueOf(changedPassword);
            controller.showMessage("Profileinfo is changed!");
        }
        System.out.println(passwordField.getPassword());
        System.out.println(passwordChangeText);
        System.out.println(tfConfirmPassword.getPassword());

        infolastname.setText(lastName);
        infoFirstname.setText(firstName);
        infoArea.setText(infoBox.getText());
        controller.updateUserDB(firstName, lastName, email, passwordChangeText, controller.getSignedInUser().getRole());
    }


    public void setTextFieldNull(){
        tfConfirmPassword.setText("");
        fName.setText("");
        lName.setText("");
        passwordField.setText("");

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
            setTextFieldNull();
            changeToChangeInfoview();
        }
        if (e.getSource().equals(btnChange)){
            changetoInfoView();
            changeInfo();
        }
        if (e.getSource().equals(btnCancel)){
            setTextFieldNull();
            changetoInfoView();

        }
    }

}



