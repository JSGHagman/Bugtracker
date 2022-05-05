/**
 * Gjorde detta guit i går eftermiddag
 * Där finns mycket kvar att göra här
 * Förslår att någon tar detta på sig
 * Ett Gui för att logga in, här finns två paneler, en för textfälten och en för knapparna.
 * Observera att när man trycker på sign up målas panelerna om.
 * Trycker man på log in öppnas main view
 *
 * MainPanels layout är null, med det kan man placera paneler med setBounds(x,y,bredd,höjd)
 * Tips när ni lägger in annan panel är att inte sätta färgen på panelen till backgroundcolour förs än den sitter rätt.
 *
 * Att göra:
 * Öka bredden på guit
 * Ändra så att sign up delarna hamnar till höger om login på samma sätt som i Java FX
 * För att göra det behöver man skapa en ny panel, placera dem till höger om den andra och flytta signup textfält/lables/combobox från InputPanel till denna
 * skapa nya fält för password och email för signup (kan använda samma i dagsläget för panelen målas om vid sign up så ingen risk att den tar fel)
 * Behöver få in logga
 * Behöver annan styling för att göra det snyggare
 * Behöver koppla funktioner mot controller när allt är fixat.
 */


package View.LogInView;
import Controller.Controller;
import View.MainView.MainFrame.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class LogInGUI extends JComponent implements ActionListener {
    private JFrame frame;
    private JButton btnLogIn, btnSignUp, btnCreateAccount;
    private JPanel mainPanel, inputPanel, buttonPanel, logoPanel;
    private JLabel emailLabel, passwordLabel, firstNameLabel, lastNameLabel, roleLabel;
    private JTextField emailTextField, firstNameTextField, lastNameTextField;
    private JPasswordField passwordField;
    private JComboBox roleBox;
    private Color backgroundColor = new Color(65, 105, 225);
    private Controller controller;

    public LogInGUI(Controller controller) {
        this.controller = controller;
        setUtilities();
        frame = new JFrame("Bugtracker Log In");
        frame.setSize(new Dimension(400, 500));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("Images/bugTrackerIcon.png");
        frame.setIconImage(icon);
        Image logo = Toolkit.getDefaultToolkit().getImage("Images/bugTrckerLogo.png");
        mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(null);
        mainPanel.add(new JLabel(new ImageIcon(logo)));
        setLogInPanel();
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void addActionListerner(JButton btn){
        btn.addActionListener(this);
    }

    public void setUtilities(){
        inputPanel = new JPanel();
        buttonPanel = new JPanel();

        emailLabel = new JLabel("E-mail");
        emailLabel.setForeground(Color.WHITE);
        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        firstNameLabel = new JLabel("First name");
        firstNameLabel.setForeground(Color.WHITE);
        lastNameLabel = new JLabel("Last name");
        lastNameLabel.setForeground(Color.WHITE);
        roleLabel = new JLabel("Select role");
        roleLabel.setForeground(Color.WHITE);

        passwordField = new JPasswordField("Password");
        passwordField.setEchoChar('\u25CF');
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        emailTextField = new JTextField("Enter e-mail here");
        emailTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        firstNameTextField = new JTextField("Enter first name here");
        firstNameTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        lastNameTextField = new JTextField("Enter last name here");
        lastNameTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        btnSignUp = new JButton("Sign up");
        btnSignUp.setBackground(new Color(255,255,255));
        btnSignUp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        addActionListerner(btnSignUp);

        btnLogIn = new JButton("Log in");
        btnLogIn.setBackground(new Color(255,255,255));
        btnLogIn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        addActionListerner(btnLogIn);

        btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setBackground(new Color(255,255,255));
        btnCreateAccount.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        addActionListerner(btnCreateAccount);

        roleBox = new JComboBox();
        roleBox.addItem("Admin");
        roleBox.addItem("Agent");
        roleBox.addItem("User");
        roleBox.setSelectedItem("User");
        roleBox.setBackground(Color.WHITE);
    }

    public void setLogInPanel(){
        frame.setTitle("Bugtracker Log In");
        mainPanel.removeAll();
        inputPanel.removeAll();
        buttonPanel.removeAll();
        setUtilities();
        inputPanel.setBounds(45,125,300,100);
        inputPanel.setLayout(new GridLayout(4,1));
        inputPanel.add(emailLabel);
        inputPanel.add(emailTextField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        buttonPanel.setBounds(45,235, 300,35);
        buttonPanel.setLayout(new GridLayout(1,3,10,10));
        buttonPanel.add(btnLogIn);
        buttonPanel.add(btnSignUp);
        inputPanel.setBackground(backgroundColor);
        buttonPanel.setBackground(backgroundColor);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void switchToSignUp(){
        frame.setTitle("Bugtracker Sign Up");
        inputPanel.removeAll();
        buttonPanel.removeAll();
        inputPanel.setBounds(45,50, 300, 300);
        inputPanel.setLayout(new GridLayout(10,1));
        inputPanel.add(firstNameLabel);
        inputPanel.add(firstNameTextField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameTextField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailTextField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.setBackground(backgroundColor);
        buttonPanel.setBounds(45,360,300,60);
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.setBackground(backgroundColor);
        inputPanel.add(roleLabel);
        inputPanel.add(roleBox);
        buttonPanel.add(btnCreateAccount);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSignUp){
            switchToSignUp();
        } if(e.getSource() == btnLogIn){
            //controller.onSignInBtnClick();
            controller.openMainWindow();
            frame.dispose();
        } if(e.getSource() == btnCreateAccount){
            //CREATE ACCOUNT
            controller.onSignUpBtnClick();
            //BACK TO LOG IN
        }
    }


    //getters for controller-class to get values later
    public JFrame getFrame() {
        return frame;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public JTextField getLastNameTextField() {
        return lastNameTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JComboBox getRoleBox() {
        return roleBox;
    }
}
