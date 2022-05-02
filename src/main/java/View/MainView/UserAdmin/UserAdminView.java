package View.MainView.UserAdmin;

import Controller.Controller;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class UserAdminView extends JComponent implements ActionListener {

    private Color menuColor = new Color(65, 105, 225);
    private Color hoverColor = new Color(65, 145, 225);

    private Controller controller;
    private MainFrame mainFrame;
    private JList<String> userList;
    private JPanel mainContentPanel, westPanel, eastPanel, southPanel;
    private JPasswordField txtPassword;
    private JTextField txtFirstName, txtLastName, txtEmail, txtDummy;
    private JComboBox role;
    private JButton btnSave, btnCancel, btnDelete;
    private JLabel lblFirstName, lblLastName, lblPassword, lblEmail;
    private String[] roles;
    private JScrollPane scrollPane;
    private JTable userTable;
    private DefaultTableModel tableModel;


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
        btnSave = new JButton("Save");
        setButtonDesign(btnSave);
        btnCancel = new JButton("Cancel");
        setButtonDesign(btnCancel);
        btnDelete = new JButton("Delete");
        setButtonDesign(btnDelete);

    }

    public void initiateUserList() {
        userList = new JList<>();
        userList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        userList.setForeground(menuColor);
        userList.setBackground(Color.WHITE);
        userList.setFont(new Font("Dialog", Font.BOLD, 12));
        scrollPane = new JScrollPane(userList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addListener();
        /*
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Role");
        userTable = new JTable(tableModel);
        tableModel.addRow(new Object[] {"TEST", "TEST", "TEST"});
        scrollPane = new JScrollPane(userTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addListener();
*/

    }

    private void initiatePanels() {
        eastPanel = new JPanel();
        eastPanel.setName("Users");
        eastPanel.add(scrollPane);
        //eastPanel.add(userTable);

        westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(6,2));
        westPanel.setName("Edit user");
        westPanel.add(lblFirstName);
        westPanel.add(txtFirstName);
        westPanel.add(lblLastName);
        westPanel.add(txtLastName);

        westPanel.add(lblEmail);
        westPanel.add(txtEmail);
        westPanel.add(lblPassword);
        westPanel.add(txtPassword);
        westPanel.add(role);
        westPanel.add(txtDummy);


        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(0,3));
        southPanel.add(btnSave);
        southPanel.add(btnCancel);
        southPanel.add(btnDelete);


        eastPanel.setBounds(mainContentPanel.getWidth()/2, mainContentPanel.getY(), mainContentPanel.getWidth()/3, mainContentPanel.getHeight()/5*4);
        eastPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        westPanel.setBounds(mainContentPanel.getX()+ mainContentPanel.getWidth()/14+10 , mainContentPanel.getY() , mainContentPanel.getWidth() /3, mainContentPanel.getHeight()/2);
        westPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        southPanel.setBounds(mainContentPanel.getX()+ mainContentPanel.getWidth()/14+10, westPanel.getHeight(), mainContentPanel.getWidth()/3, mainContentPanel.getHeight()/12);

       scrollPane.setBounds(mainContentPanel.getWidth()/2, mainContentPanel.getY(), mainContentPanel.getWidth()/3, mainContentPanel.getHeight()/5*4);
       scrollPane.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));


    }

    public void initializeUserAdminView() {
        mainContentPanel.removeAll();
        mainContentPanel.add(westPanel);
        mainContentPanel.add(scrollPane);
        mainContentPanel.add(southPanel);

        mainFrame.getFrame().revalidate();
        mainFrame.getFrame().repaint();

    }

    private void initiateTextfield() {
        txtFirstName = new JTextField();
        setTextBoxDesign(txtFirstName);
        txtLastName = new JTextField();
        setTextBoxDesign(txtLastName);
        txtPassword = new JPasswordField();
        setTextBoxDesign(txtPassword);
        txtEmail = new JTextField();
        setTextBoxDesign(txtEmail);
        txtDummy = new JTextField("");
        setTextBoxDesign(txtDummy);
    }

    private void initiateComboBox() {
        role = new JComboBox(roles);
        role.setSelectedIndex(0);
        role.setForeground(menuColor);
        role.setBackground(Color.WHITE);
        role.setFont(new Font("Dialog", Font.BOLD, 12));
        role.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String chosenRole = role.getSelectedItem().toString();
            }
        });
    }

    public void initiateLabels() {
        lblFirstName = new JLabel("First Name");
        setLabelDesign(lblFirstName);
        lblLastName = new JLabel("Last Name");
        setLabelDesign(lblLastName);
        lblEmail = new JLabel("Email");
        setLabelDesign(lblEmail);
        lblPassword = new JLabel("Password");
        setLabelDesign(lblPassword);

    }
    private void setTextBoxDesign(JTextField txt) {
        txt.setForeground(menuColor);
        txt.setBackground(Color.WHITE);
        txt.setFont(new Font("Dialog", Font.BOLD, 12));
        txt.setBorder(BorderFactory.createLineBorder(menuColor, 1));

    }
    private void setLabelDesign(JLabel lbl) {
        lbl.setForeground(menuColor);
        lbl.setBackground(Color.WHITE);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl.setBorder(BorderFactory.createLineBorder(menuColor, 1));
    }

    private void setButtonDesign(JButton btn) {
        btn.setBackground(menuColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Dialog", Font.BOLD, 12));
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

    public void setUserTable(String[] userList) {
      //  this.userTable.addEle
    }

    public JTextField getTxtFirstName() {
        return txtFirstName;
    }

    public void setTxtFirstName(String txtFirstName) {
        this.txtFirstName.setText(txtFirstName);
    }

    public JTextField getTxtLastName() {
        return txtLastName;
    }

    public void setTxtLastName(String txtLastName) {
        this.txtLastName.setText(txtLastName);
    }

    public JTextField getPasswordtxt() {
        return txtPassword;
    }

    public void setPasswordtxt(String passwordtxt) {
        this.txtPassword.setText(passwordtxt);
        this.txtPassword.setEchoChar('\u25CF');
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail.setText(txtEmail);
    }

    public JComboBox getRole() {
        return role;
    }

    public void setRole(String role) {

        if (role.equals("Admin")) {
            this.role.setSelectedIndex(2);
        }
        if (role.equals("User")) {
            this.role.setSelectedIndex(0);
        }
        if (role.equals("Agent")) {
            this.role.setSelectedIndex(1);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            setPasswordtxt("");
            setTxtEmail("");
            setRole("User");
            setTxtFirstName("");
            setTxtLastName("");
            userList.setSelectedIndex(-1);

        }

        if (e.getSource() == btnSave) {
            controller.updateUserDB(getTxtFirstName().getText(), getTxtLastName().getText(),
                    getTxtEmail().getText(), getPasswordtxt().getText(), getRole().getSelectedItem().toString());

        }

        if (e.getSource() == btnDelete) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete User: " + getTxtEmail().getText(),
            "Delete user", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                controller.deleteUser(getTxtEmail().getText());
            }
            else {
            }
        }
    }

    private void addActionListener(JButton btn) {
        btn.addActionListener(this);
    }

    public void addListener() {
        userList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                int index = userList.getSelectedIndex();
                if (index > -1) {
                   controller.selectUserinList(index);
                }
            }
        });
    }
}