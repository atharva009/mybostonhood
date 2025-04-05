
package ui;

import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;

import javax.swing.*;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;

public class NeighborhoodAnalyticsPage {

    private NeighborhoodDirectory directory;

    public NeighborhoodAnalyticsPage(NeighborhoodDirectory directory) {
        this.directory = directory;
    }

    public void showCharts(JFrame parentFrame) {
        List<String> names = directory.getAllNeighborhoodNames();

        DefaultCategoryDataset crimeDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset rentDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset schoolDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset greenDataset = new DefaultCategoryDataset();

        for (String name : names) {
            Neighborhood n = directory.getNeighborhood(name);
            crimeDataset.addValue(n.getCrimeRate(), "Crime Rate", name);
            rentDataset.addValue(n.getAverageRent(), "Rent", name);
            schoolDataset.addValue(n.getSchoolRating(), "School Rating", name);
            greenDataset.addValue(n.getGreenSpaceScore(), "Green Space", name);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new ChartPanel(createBarChart("Crime Rate", "Neighborhood", "Rate", crimeDataset)));
        panel.add(new ChartPanel(createBarChart("Average Rent", "Neighborhood", "$", rentDataset)));
        panel.add(new ChartPanel(createBarChart("School Rating", "Neighborhood", "Rating", schoolDataset)));
        panel.add(new ChartPanel(createBarChart("Green Space", "Neighborhood", "Score", greenDataset)));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        JOptionPane.showMessageDialog(parentFrame, scrollPane, "Neighborhood Analytics", JOptionPane.PLAIN_MESSAGE);
    }

    private JFreeChart createBarChart(String title, String xAxisLabel, String yAxisLabel, DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, false, true, false);
    }
}
