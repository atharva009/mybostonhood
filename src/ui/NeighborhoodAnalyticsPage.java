package ui;

import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NeighborhoodAnalyticsPage {

    private NeighborhoodDirectory directory;

    public NeighborhoodAnalyticsPage(NeighborhoodDirectory directory) {
        this.directory = directory;
    }

    public void showCharts(JFrame parentFrame) {
        JPanel chartContainer = new JPanel();
        chartContainer.setLayout(new BoxLayout(chartContainer, BoxLayout.Y_AXIS));
        chartContainer.setBackground(Color.WHITE);
        chartContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        chartContainer.add(wrapChartPanel(createBarChart("Crime Rate by Neighborhood", "Neighborhood", "Crime Rate", buildCategoryDataset("crime"), new Color(220, 53, 69))));
        chartContainer.add(Box.createVerticalStrut(20));
        chartContainer.add(wrapChartPanel(createBarChart("Average Rent by Neighborhood", "Neighborhood", "Rent ($)", buildCategoryDataset("rent"), new Color(255, 193, 7))));
        chartContainer.add(Box.createVerticalStrut(20));
        chartContainer.add(wrapChartPanel(createBarChart("School Rating by Neighborhood", "Neighborhood", "Rating", buildCategoryDataset("school"), new Color(40, 167, 69))));
        chartContainer.add(Box.createVerticalStrut(20));
        chartContainer.add(wrapChartPanel(createBarChart("Green Space Score by Neighborhood", "Neighborhood", "Score", buildCategoryDataset("green"), new Color(23, 162, 184))));

        chartContainer.add(Box.createVerticalStrut(30));
        chartContainer.add(new JLabel("Pie Chart Insights", JLabel.CENTER));
        chartContainer.add(Box.createVerticalStrut(10));
        chartContainer.add(wrapChartPanel(createPieChart("Proportion of Average Rent", buildPieDataset("rent"))));
        chartContainer.add(Box.createVerticalStrut(10));
        chartContainer.add(wrapChartPanel(createPieChart("Proportion of Crime Rate", buildPieDataset("crime"))));
        chartContainer.add(Box.createVerticalStrut(10));
        chartContainer.add(wrapChartPanel(createPieChart("Proportion of School Rating", buildPieDataset("school"))));

        JScrollPane scrollPane = new JScrollPane(chartContainer);
        scrollPane.setPreferredSize(new Dimension(850, 800));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JOptionPane.showMessageDialog(parentFrame, scrollPane, "Neighborhood Analytics", JOptionPane.PLAIN_MESSAGE);
    }

    private DefaultCategoryDataset buildCategoryDataset(String type) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<String> names = directory.getAllNeighborhoodNames();

        for (String name : names) {
            Neighborhood n = directory.getNeighborhood(name);
            switch (type) {
                case "crime" -> dataset.addValue(n.getCrimeRate(), type, name);
                case "rent" -> dataset.addValue(n.getAverageRent(), type, name);
                case "school" -> dataset.addValue(n.getSchoolRating(), type, name);
                case "green" -> dataset.addValue(n.getGreenSpaceScore(), type, name);
            }
        }
        return dataset;
    }

    private DefaultPieDataset buildPieDataset(String type) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        List<String> names = directory.getAllNeighborhoodNames();

        for (String name : names) {
            Neighborhood n = directory.getNeighborhood(name);
            switch (type) {
                case "crime" -> dataset.setValue(name, n.getCrimeRate());
                case "rent" -> dataset.setValue(name, n.getAverageRent());
                case "school" -> dataset.setValue(name, n.getSchoolRating());
            }
        }
        return dataset;
    }

    private JFreeChart createBarChart(String title, String xAxisLabel, String yAxisLabel, DefaultCategoryDataset dataset, Color barColor) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, barColor);

        return chart;
    }

    private JFreeChart createPieChart(String title, DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setBackgroundPaint(Color.WHITE);
        chart.setTitle(new TextTitle(title, new Font("SansSerif", Font.BOLD, 16)));

        return chart;
    }

    private JPanel wrapChartPanel(JFreeChart chart) {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    }
}