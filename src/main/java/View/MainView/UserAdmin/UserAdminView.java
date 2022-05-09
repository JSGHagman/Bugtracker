package View.MainView.UserAdmin;


import Controller.Controller;
import View.MainView.MainFrame.MainFrame;

import javax.swing.*;
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
    private JPanel mainContentPanel, westPanel, eastPanel, southPanel;
    private JPasswordField txtPassword;
    private JTextField txtFirstName, txtLastName, txtEmail, txtDummy;
    private JComboBox role;
    private JButton btnSave, btnCancel, btnDelete;
    private JLabel lblFirstName, lblLastName, lblPassword, lblEmail;
    private Object[][] data;
    private String[] columnNames, roles;
    private JScrollPane scrollPane;
    private JTable userTable;


    public UserAdminView(Controller controller, MainFrame mainFrame, ArrayList users) {
        this.controller = controller;
        this.mainFrame = mainFrame;
        this.mainContentPanel = mainFrame.getContentPanel();
        roles = new String[]{"User", "Agent", "Admin"};
        initiateButtons();
        initiateLabels();
        initiateComboBox();
        initiateTextfield();
        setUserList(users);
        initiateUserTable();
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

    public void initiateUserTable() {

        userTable = new JTable(data, columnNames);
        userTable.setRowSelectionAllowed(true);
        userTable.setFillsViewportHeight(true);
        userTable.getTableHeader().setBackground(menuColor);
        userTable.getTableHeader().setForeground(Color.WHITE);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.getTableHeader().setBackground(menuColor);
        userTable.setAutoCreateRowSorter(true);
        userTable.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(userTable);
        int verticalPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
        scrollPane.setVerticalScrollBarPolicy(verticalPolicy);
        scrollPane.getVerticalScrollBar().setBackground(menuColor);
        userTable.setRowSelectionAllowed(true);


        addListener();


        for (int c = 0; c < userTable.getColumnCount(); c++) {
            Class<?> col_class = userTable.getColumnClass(c);
            userTable.setDefaultEditor(col_class, null);        // remove editor
        }


    }

    private void initiatePanels() {
        eastPanel = new JPanel(new GridLayout(1, 1));
        eastPanel.setName("Users");
        eastPanel.add(scrollPane);

        westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(6, 2));
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
        southPanel.setLayout(new GridLayout(0, 3));
        southPanel.add(btnSave);
        southPanel.add(btnCancel);
        southPanel.add(btnDelete);


        eastPanel.setBounds(mainContentPanel.getWidth() / 2, mainContentPanel.getY(), mainContentPanel.getWidth() / 3, mainContentPanel.getHeight() / 5 * 4);
        eastPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        westPanel.setBounds(mainContentPanel.getX() + mainContentPanel.getWidth() / 14 + 10, mainContentPanel.getY(), mainContentPanel.getWidth() / 3, mainContentPanel.getHeight() / 2);
        westPanel.setBorder(BorderFactory.createLineBorder(menuColor, 5, false));
        southPanel.setBounds(mainContentPanel.getX() + mainContentPanel.getWidth() / 14 + 10, westPanel.getHeight(), mainContentPanel.getWidth() / 3, mainContentPanel.getHeight() / 12);


    }

    public void initializeUserAdminView() {
        mainContentPanel.removeAll();
        mainContentPanel.add(westPanel);
        mainContentPanel.add(eastPanel);
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

    public void setUserList(ArrayList users) {
        columnNames = new String[]{"First Name", "Last Name", "Email", "Role"};

        data = new Object[users.size() / 4][4];
        for (int i = 0; i <= users.size() - 4; i += 4) {

            if (i == 0) {
                data[i][0] = users.get(i);
                data[i][1] = users.get(i + 1);
                data[i][2] = users.get(i + 2);
                data[i][3] = users.get(i + 3);

            } else {
                data[(i / 4)][0] = users.get(i);
                data[(i / 4)][1] = users.get(i + 1);
                data[(i / 4)][2] = users.get(i + 2);
                data[(i / 4)][3] = users.get(i + 3);
            }
        }

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
            } else {
            }
        }
    }

    private void addActionListener(JButton btn) {
        btn.addActionListener(this);
    }


    public void addListener() {
        ListSelectionModel rowSM = userTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                ListSelectionModel lsm = (ListSelectionModel) evt.getSource();
                if (lsm.getMinSelectionIndex() > -1) {
                    int row = lsm.getMinSelectionIndex();
                    setTxtFirstName(userTable.getValueAt(row, 0).toString());
                    setTxtLastName(userTable.getValueAt(row, 1).toString());
                    setTxtEmail(userTable.getValueAt(row, 2).toString());
                    setRole(userTable.getValueAt(row, 3).toString());
                }
            }

        });
    }


}
