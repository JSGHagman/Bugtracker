/**
 * This class is used for creating the MainPanel
 * Which in turn will create the other two panels SouthPanel and CenterPanel
 * @author Jakob Hagman
 *
 */

package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.sql.SQLException;

public class MainPanel extends JPanel {
    private int width;
    private int height;
    private SouthPanel southPanel;
    private CenterPanel centerPanel;
    private BorderLayout layout;
    private Controller controller;

    public MainPanel(Controller controller, int width, int height) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setUpPanel();
    }

    public void setUpPanel() {
        layout = new BorderLayout();
        setLayout(layout);

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(6,6,6,6);
        setBorder(new CompoundBorder(border, margin));

        centerPanel = new CenterPanel(controller,6*width/10, (8*height/10)-100, 6);
        southPanel = new SouthPanel(centerPanel, controller, width,(height/8)+200, 6);
        add(southPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    public SouthPanel getSouthPanel(){
        return southPanel;
    }

}
