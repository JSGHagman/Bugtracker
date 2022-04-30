package View.MainView.UserAdmin;

import Controller.Controller;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserAdminView extends JComponent implements ActionListener {

    private Color menuColor = new Color(65, 105, 225);
    private Color hoverColor = new Color(65, 145, 225);

    private Controller controller;
    private MainFrame mainFrame;
    private JList<String> userList;
    private JPanel mainContentPanel, westPanel, eastPanel;

    private TextField firstNametxt, lastNametxt, passwordtxt, emailtxt;
    private JComboBox role;
    private JButton save, cancel;
    private JLabel firstNamelbl, lastNamelbl, passwordlbl, emaillbl;
    private String[] roles;


    public UserAdminView(Controller controller, MainFrame mainFrame) {
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.mainContentPanel = mainFrame.getContentPanel();
        roles = new String[]{"User", "Agent", "Admin"};
        initiateButtons();
        initiateUserList();
        initiateLabels();
        initiateComboBox();
        initiateTextfield();
        initiatePanels();
        initializeUserAdminView();

    }


    public void initiateButtons() {
        save = new JButton("Save");
        setButtonDesign(save);
        cancel = new JButton("Cancel");
        setButtonDesign(cancel);
    }

    public void initiateUserList() {
        userList = new JList<>();
        JScrollPane s = new JScrollPane(userList);
        s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        userList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        addListener();
        add(s);
    }

    private void initiatePanels() {
        eastPanel = new JPanel();
        eastPanel.setName("Users");
        eastPanel.add(userList);

        westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(5,2));
        westPanel.setName("Edit user");
        westPanel.add(firstNamelbl);
        westPanel.add(firstNametxt);
        westPanel.add(lastNamelbl);
        westPanel.add(lastNametxt);

        westPanel.add(emaillbl);
        westPanel.add(emailtxt);
        westPanel.add(passwordlbl);
        westPanel.add(passwordtxt);
        westPanel.add(role);



        eastPanel.setBounds(mainContentPanel.getWidth()/2, mainContentPanel.getY(), mainContentPanel.getWidth()/3, mainContentPanel.getHeight());
        eastPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        westPanel.setBounds(mainContentPanel.getX() , mainContentPanel.getY() , mainContentPanel.getWidth() /3, mainContentPanel.getHeight()/2);
        westPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));


    }

    public void initializeUserAdminView() {
        mainContentPanel.removeAll();
        mainContentPanel.add(westPanel);
        mainContentPanel.add(eastPanel);

        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();

    }

    private void initiateTextfield() {
        firstNametxt = new TextField();
        lastNametxt = new TextField();
        passwordtxt = new TextField();
        emailtxt = new TextField();
    }

    private void initiateComboBox() {
        role = new JComboBox(roles);
        role.setSelectedIndex(0);
        role.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String chosenRole = role.getSelectedItem().toString();
            }
        });
    }

    public void initiateLabels() {
        firstNamelbl = new JLabel("First Name");
        setLabelDesign(firstNamelbl);
        lastNamelbl = new JLabel("Last Name");
        setLabelDesign(lastNamelbl);
        emaillbl = new JLabel("Email");
        setLabelDesign(emaillbl);
        passwordlbl = new JLabel("Password");
        setLabelDesign(passwordlbl);

    }

    private void setLabelDesign(JLabel lbl) {
        lbl.setForeground(menuColor);
        lbl.setBackground(Color.WHITE);
        lbl.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    private void setButtonDesign(JButton btn) {
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

            public void mousePressed(MouseEvent evt) {
                btn.setBackground(menuColor);
            }
        });
    }


    public JList<String> getUserList() {
        return userList;
    }

    public void setUserList(String[] userList) {
       this.userList.setListData(userList);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void addActionListener(JButton btn) {
        btn.addActionListener(this);
    }

    public void addListener() {
        userList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                int index = userList.getSelectedIndex();
                if (index > -1) {
                    //controller.guestListIndexChanged(index);
                }
            }
        });
    }
}