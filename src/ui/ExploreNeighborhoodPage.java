
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import util.ADTBST.NeighborhoodSorter;
import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;
import util.ADTScore.ScoringAlgorithm;
import util.ADTScore.WeightedScoringEngine;
import util.ADTBST.SortField;
import util.ADTStack.NavigationStack;
import util.ADTCompare.NeighborhoodCompareList;

public class ExploreNeighborhoodPage extends JFrame {

    private NeighborhoodDirectory directory;
    private JPanel neighborhoodListPanel;
    private JComboBox<SortField> sortDropdown;
    private Map<SortField, NeighborhoodSorter> sorterMap;

    private NeighborhoodCompareList compareList = new NeighborhoodCompareList();

    public ExploreNeighborhoodPage(NeighborhoodDirectory directory) {
        this.directory = directory;
        this.sorterMap = new HashMap<>();

        setTitle("Explore Boston Neighborhoods");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        NavigationStack.push(this);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> NavigationStack.back());
        topPanel.add(backButton, BorderLayout.WEST);

        JPanel centerSortPanel = new JPanel();
        centerSortPanel.add(new JLabel("Sort by:"));
        sortDropdown = new JComboBox<>(SortField.values());
        centerSortPanel.add(sortDropdown);
        topPanel.add(centerSortPanel, BorderLayout.CENTER);

        JButton viewCompareButton = new JButton("View My Comparisons");
        viewCompareButton.addActionListener(e -> showComparison());
        topPanel.add(viewCompareButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        neighborhoodListPanel = new JPanel();
        neighborhoodListPanel.setLayout(new BoxLayout(neighborhoodListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(neighborhoodListPanel);
        add(scrollPane, BorderLayout.CENTER);

        sortDropdown.addActionListener(e -> refreshNeighborhoods());

        initializeSorters();
        refreshNeighborhoods();
        setVisible(true);
    }

    private void initializeSorters() {
        List<String> allNames = directory.getAllNeighborhoodNames();
        for (SortField field : SortField.values()) {
            NeighborhoodSorter sorter = new NeighborhoodSorter(field.getComparator());
            for (String name : allNames) {
                Neighborhood n = directory.getNeighborhood(name);
                sorter.insertNeighborhood(n);
            }
            sorterMap.put(field, sorter);
        }
    }

    private void refreshNeighborhoods() {
        neighborhoodListPanel.removeAll();
        SortField selectedField = (SortField) sortDropdown.getSelectedItem();
        List<Neighborhood> sortedList = sorterMap.get(selectedField).getSortedNeighborhoods();

        for (Neighborhood n : sortedList) {
            JPanel card = createNeighborhoodCard(n);
            neighborhoodListPanel.add(card);
            neighborhoodListPanel.add(Box.createVerticalStrut(10));
        }

        neighborhoodListPanel.revalidate();
        neighborhoodListPanel.repaint();
    }

    private JPanel createNeighborhoodCard(Neighborhood n) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(n.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        infoPanel.add(nameLabel);

        JLabel descLabel = new JLabel("<html><p style='width:450px'>" + n.getDescription() + "</p></html>");
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoPanel.add(descLabel);

        card.add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton viewBtn = new JButton("View");
        JButton compareBtn = new JButton("Add to Compare");

        viewBtn.addActionListener(e -> showNeighborhoodDetails(n));

        compareBtn.addActionListener(e -> {
            if (compareList.contains(n)) {
                JOptionPane.showMessageDialog(this, "Already added.");
            } else if (!compareList.add(n)) {
                JOptionPane.showMessageDialog(this, "You can only compare up to 3 neighborhoods.");
            } else {
                JOptionPane.showMessageDialog(this, n.getName() + " added to comparison.");
            }
        });

        buttonPanel.add(viewBtn);
        buttonPanel.add(compareBtn);
        card.add(buttonPanel, BorderLayout.EAST);

        return card;
    }

    private void showNeighborhoodDetails(Neighborhood n) {
        String message = String.format(
                "<html><h2>%s</h2>" +
                        "<p><b>Description:</b> %s</p>" +
                        "<p><b>Average Rent:</b> $%.2f</p>" +
                        "<p><b>Crime Rate:</b> %.2f</p>" +
                        "<p><b>School Rating:</b> %.1f</p>" +
                        "<p><b>Green Space Score:</b> %d</p>" +
                        "<p><b>Livability Score:</b> %.1f%%</p></html>",
                n.getName(),
                n.getDescription(),
                n.getAverageRent(),
                n.getCrimeRate(),
                n.getSchoolRating(),
                n.getGreenSpaceScore(),
                n.getLivabilityScore() * 100
        );

        JOptionPane.showMessageDialog(this, message, "Neighborhood Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showComparison() {
        if (compareList.size() < 2) {
            JOptionPane.showMessageDialog(this, "Select at least 2 neighborhoods to compare.");
            return;
        }

        ScoringAlgorithm scorer = new WeightedScoringEngine();
        Neighborhood[] selected = compareList.getAll();
        double bestScore = Double.MIN_VALUE;
        Neighborhood best = null;

        double maxSchool = Double.MIN_VALUE;
        double maxGreen = Double.MIN_VALUE;
        double maxRent = Double.MIN_VALUE;
        double maxCrime = Double.MIN_VALUE;
        double maxLivability = Double.MIN_VALUE;

        Map<Neighborhood, double[]> factorScores = new HashMap<>();

        for (Neighborhood n : selected) {
            double score = scorer.calculateScore(n);
            if (score > bestScore) {
                bestScore = score;
                best = n;
            }

            double schoolPct = normalize(n.getSchoolRating(), 0, 10) * 100;
            double greenPct = normalize(n.getGreenSpaceScore(), 0, 100) * 100;
            double rentPct = (1 - normalize(n.getAverageRent(), 1000, 4000)) * 100;
            double crimePct = (1 - normalize(n.getCrimeRate(), 10, 80)) * 100;
            factorScores.put(n, new double[]{schoolPct, greenPct, rentPct, crimePct});

            maxSchool = Math.max(maxSchool, schoolPct);
            maxGreen = Math.max(maxGreen, greenPct);
            maxRent = Math.max(maxRent, rentPct);
            maxCrime = Math.max(maxCrime, crimePct);
            maxLivability = Math.max(maxLivability, score);
        }

        JPanel tablePanel = new JPanel(new GridLayout(selected.length + 1, 6, 10, 10));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tablePanel.add(createCell("Neighborhood", true));
        tablePanel.add(createCell("School", true));
        tablePanel.add(createCell("Green Space", true));
        tablePanel.add(createCell("Rent", true));
        tablePanel.add(createCell("Crime", true));
        tablePanel.add(createCell("Livability", true));

        for (Neighborhood n : selected) {
            double[] scores = factorScores.get(n);
            double schoolPct = scores[0];
            double greenPct = scores[1];
            double rentPct = scores[2];
            double crimePct = scores[3];
            double totalScore = n.getLivabilityScore();
            boolean isBest = (n == best);

            tablePanel.add(createCellWithBold(n.getName(), false, false, isBest));
            tablePanel.add(createCellWithBold(getRatingLabel(schoolPct), schoolPct == maxSchool, false, isBest));
            tablePanel.add(createCellWithBold(getRatingLabel(greenPct), greenPct == maxGreen, false, isBest));
            tablePanel.add(createCellWithBold(getRatingLabel(rentPct), rentPct == maxRent, false, isBest));
            tablePanel.add(createCellWithBold(getRatingLabel(crimePct), crimePct == maxCrime, false, isBest));
            tablePanel.add(createCellWithBold(String.format("%.1f%%", totalScore * 100), totalScore == maxLivability, false, isBest));
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);
        scrollPane.setPreferredSize(new Dimension(700, 300));

        JLabel descriptionLabel = new JLabel("<html><br><b>Summary:</b> Based on all weighted factors, <span style='color:green;'>"
                + best.getName() + "</span> stands out as the most livable neighborhood with a score of <b>"
                + String.format("%.1f%%", best.getLivabilityScore() * 100) + "</b>. It excels particularly in areas like school rating and green space availability.</html>");
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scrollPane, BorderLayout.CENTER);
        wrapper.add(descriptionLabel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, wrapper, "Neighborhood Comparison", JOptionPane.PLAIN_MESSAGE);
    }

    private double normalize(double value, double min, double max) {
        if (value < min) return 0;
        if (value > max) return 1;
        return (value - min) / (max - min);
    }

    private String getRatingLabel(double percentage) {
        if (percentage >= 80) return "Excellent ✅ (" + (int) percentage + "%)";
        if (percentage >= 60) return "Good 👍 (" + (int) percentage + "%)";
        if (percentage >= 40) return "Average ⚖️ (" + (int) percentage + "%)";
        return "Needs Improvement ⚠️ (" + (int) percentage + "%)";
    }

    private JLabel createCell(String text, boolean isHeader) {
        return createCellWithBold(text, false, isHeader, false);
    }

    private JLabel createCellWithBold(String text, boolean isBold, boolean isHeader, boolean highlight) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setOpaque(true);
        label.setFont(new Font("SansSerif", (isHeader || isBold) ? Font.BOLD : Font.PLAIN, 13));
        label.setBackground(isHeader ? Color.LIGHT_GRAY :
                (highlight ? new Color(205, 255, 205) : Color.WHITE));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return label;
    }
}
