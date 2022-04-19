package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class CenterPanel extends JPanel {

    private JList <String> list;
    private Controller controller;

    public CenterPanel(Controller controller, int width, int height, int margin){
        this.controller = controller;
        setBorder(BorderFactory.createTitledBorder("All tickets will be shown here"));
        Border border = this.getBorder();
        Border emptyBorder = BorderFactory.createEmptyBorder(margin,margin,margin-100,margin);
        setBorder(new CompoundBorder(border, emptyBorder));
        setPreferredSize(new Dimension(width, height));

        list = new JList(); //data has type Object[]
        Font font = new Font("Courier New", Font.PLAIN, 12);
        list.setFont(font);

        JScrollPane s = new JScrollPane(list);
        s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        s.setPreferredSize(new Dimension(width+200, height-50));
        add(s);
    }


    public void showTicketsInView(String[] str){
        list.setListData(str);
    }
}
