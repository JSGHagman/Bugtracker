/**
 * Följande klass fungerar som login-gui.
 * Den används för att logga in i applikationen och skapa konto
 *
 * @authors Jakob Hagman, Patrik Brandell, Yara Rajjoub, Viktor Abraham.
 */


package View.LogInView;

import Controller.Controller;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogInGUI extends JComponent implements ActionListener, FocusListener {
    private JFrame frame;
    private JButton btnLogIn, btnSignUp, btnCreateAccount, btnCancel;
    private JPanel mainPanel, inputPanel, buttonPanel, logoPanel;
    private JLabel emailLabel, passwordLabel, firstNameLabel, lastNameLabel, roleLabel, logoLabel;
    private JTextField emailTextField, firstNameTextField, lastNameTextField;
    private JPasswordField passwordField;
    private JComboBox roleBox;
    private Color backgroundColor = new Color(65, 105, 225);
    private Controller controller;
    private Color menuColor = new Color(65, 105, 225);
    private Color hoverColor = new Color(65, 145, 225);
    private String oldValue;


    public LogInGUI(Controller controller) {
        this.controller = controller;
        setUtilities();
        initiateButtons();
        initiatePanels();

        frame = new JFrame("Bugtracker Log In");
        frame.setSize(new Dimension(400, 600));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("Images/bugTrackerIcon.png");
        frame.setIconImage(icon);
        Image logo = Toolkit.getDefaultToolkit().getImage("Images/bugTrckerLogo.png");
        initiateLabels();
        mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        mainPanel.add(new JLabel(new ImageIcon(logo)));
        setLogInPanel();
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(btnLogIn);
    }

    /**
     * Initiate JLabels and setIcon
     */
    public void initiateLabels() {
        logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/BugTrckerLogo.png")).getImage().getScaledInstance(frame.getWidth() - 18, 100, Image.SCALE_DEFAULT)));
    }

    /**
     *
     * @param btn JButton add actionListener
     */
    public void addActionListerner(JButton btn) {
        btn.addActionListener(this);
    }

    /**
     * Setup GUI utilites,
     */
    public void setUtilities() {

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
        setTextFieldDesign(passwordField);

        emailTextField = new JTextField("Enter e-mail here");
        setTextFieldDesign(emailTextField);

        firstNameTextField = new JTextField("Enter first name here");
        setTextFieldDesign(firstNameTextField);
        lastNameTextField = new JTextField("Enter last name here");
        setTextFieldDesign(lastNameTextField);


        roleBox = new JComboBox();
        roleBox.addItem("Admin");
        roleBox.addItem("Agent");
        roleBox.addItem("User");
        roleBox.setSelectedItem("User");
        roleBox.setBackground(Color.WHITE);
    }

    /**
     * Initiate Panels
     */
    private void initiatePanels() {
        inputPanel = new JPanel();
        buttonPanel = new JPanel();
        logoPanel = new JPanel();

    }

    /**
     * Setup loginPanel
     */
    public void setLogInPanel() {
        frame.setTitle("Bugtracker Log In");
        mainPanel.removeAll();
        inputPanel.removeAll();
        buttonPanel.removeAll();
        setUtilities();
        logoPanel.setBounds(mainPanel.getX() + 1, mainPanel.getY() + 1, frame.getWidth() - 18, 100);
        logoPanel.setLayout(new GridLayout(1, 1));
        logoPanel.add(logoLabel);
        inputPanel.setBounds(45, 125, 300, 100);
        inputPanel.setLayout(new GridLayout(4, 1));
        inputPanel.add(emailLabel);
        inputPanel.add(emailTextField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        buttonPanel.setBounds(45, 235, 300, 35);
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(btnLogIn);
        buttonPanel.add(btnSignUp);
        inputPanel.setBackground(backgroundColor);
        buttonPanel.setBackground(backgroundColor);
        mainPanel.add(logoPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Switch to signUp view if btn clicked
     */
    public void switchToSignUp() {
        frame.setTitle("Bugtracker Sign Up");
        inputPanel.removeAll();
        buttonPanel.removeAll();
        inputPanel.setBounds(45, 125, 300, 300);
        inputPanel.setLayout(new GridLayout(10, 1));
        inputPanel.add(firstNameLabel);
        inputPanel.add(firstNameTextField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameTextField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailTextField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.setBackground(backgroundColor);
        buttonPanel.setBounds(45, 435, 300, 35);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(backgroundColor);
        inputPanel.add(roleLabel);
        inputPanel.add(roleBox);
        buttonPanel.add(btnCreateAccount);
        buttonPanel.add(btnCancel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Initiate Buttons
     */
    private void initiateButtons() {
        btnSignUp = new JButton("Sign up");
        setButtonDesign(btnSignUp);

        btnLogIn = new JButton("Log in");
        setButtonDesign(btnLogIn);

        btnCreateAccount = new JButton("Create Account");
        setButtonDesign(btnCreateAccount);

        btnCancel = new JButton("Cancel");
        setButtonDesign(btnCancel);
    }

    /**
     *
     * @param btn JButton set button design
     */
    private void setButtonDesign(JButton btn) {
        btn.setBackground(menuColor);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
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
     *
     * @param text JTextField set design
     */
    private void setTextFieldDesign(JTextField text) {
        text.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        text.addFocusListener(this);
    }

    /**
     *
     * @param btn JButton add actionListener
     */
    private void addActionListener(JButton btn) {
        btn.addActionListener(this);
    }

    /**
     *
     * @param e ActionPerformed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSignUp) {
            switchToSignUp();
        }
        if (e.getSource() == btnLogIn) {
            controller.onSignInBtnClick();
        }
        if (e.getSource() == btnCreateAccount) {
            //CREATE ACCOUNT
            controller.onSignUpBtnClick();
            //BACK TO LOG IN
        }
        if (e.getSource() == btnCancel) {
            setLogInPanel();
        }
    }

    /**
     *
     * @param e FocusEvent
     */
    @Override
    public void focusGained(FocusEvent e) {
        JTextField src = (JTextField) e.getSource();
        src.setText("");
    }

    /**
     *
     * @param e FocusLost
     */
    @Override
    public void focusLost(FocusEvent e) {

    }

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
