package View.StaticsView;

import Controller.Controller;
import View.MainView.MainFrame.MainFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.Color;
import java.awt.EventQueue;

public class StatisticView extends JComponent {

    private ChartPanel chartPanel;
    private CategoryDataset dataset;
    private MainFrame mainFrame;
    private Controller controller;
    private JPanel mainContentPanel, contentPanel;

    public StatisticView(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        this.mainContentPanel = mainFrame.getContentPanel();
        dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        contentPanel = new JPanel();

       // chartPanel.setBounds(mainContentPanel.getWidth() / 2, mainContentPanel.getY(), mainContentPanel.getWidth() / 3, mainContentPanel.getHeight() / 5 * 4);
        contentPanel.setBounds(mainContentPanel.getWidth() / 2, mainContentPanel.getY(), mainContentPanel.getWidth() / 3, mainContentPanel.getHeight() / 5 * 4);

        contentPanel.add(chartPanel);
        mainContentPanel.removeAll();
        mainContentPanel.add(contentPanel);
        mainContentPanel.revalidate();
        mainContentPanel.repaint();

   //     pack();
     //   setTitle("Bar chart");


   //     setLocationRelativeTo(null);

    }
    private CategoryDataset createDataset() {

        var dataset = new DefaultCategoryDataset();
        dataset.setValue(46, "Gold medals", "USA");
        dataset.setValue(38, "Gold medals", "China");
        dataset.setValue(29, "Gold medals", "UK");
        dataset.setValue(22, "Gold medals", "Russia");
        dataset.setValue(13, "Gold medals", "South Korea");
        dataset.setValue(11, "Gold medals", "Germany");

        return dataset;

    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Olympic gold medals in London",
                "",
                "Gold medals",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

}
