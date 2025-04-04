package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import util.ADTBST.NeighborhoodSorter;
import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;
import util.ADTBST.SortField;
import util.ADTStack.NavigationStack;

public class ExploreNeighborhoodPage extends JFrame {

    private NeighborhoodDirectory directory;
    private JPanel neighborhoodListPanel;
    private JComboBox<SortField> sortDropdown;
    private Map<SortField, NeighborhoodSorter> sorterMap;

    // Compare logic
    private List<Neighborhood> compareList = new ArrayList<>(2);

    public ExploreNeighborhoodPage(NeighborhoodDirectory directory) {
        this.directory = directory;
        this.sorterMap = new HashMap<>();

        setTitle("Explore Boston Neighborhoods");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        NavigationStack.push(this);

        setLayout(new BorderLayout());

        // ----- Top Panel: Back, Dropdown, View Comparison -----
        JPanel topPanel = new JPanel(new BorderLayout());

        // Back Button (Top Left)
        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> NavigationStack.back());
        topPanel.add(backButton, BorderLayout.WEST);

        // Sort Dropdown (Center)
        JPanel centerSortPanel = new JPanel();
        centerSortPanel.add(new JLabel("Sort by:"));
        sortDropdown = new JComboBox<>(SortField.values());
        centerSortPanel.add(sortDropdown);
        topPanel.add(centerSortPanel, BorderLayout.CENTER);

        // View Comparison Button (Top Right)
        JButton viewCompareButton = new JButton("View My Comparisons");
        viewCompareButton.addActionListener(e -> {
            if (compareList.size() < 2) {
                JOptionPane.showMessageDialog(this, "Select 2 neighborhoods to compare.");
                return;
            }
            JOptionPane.showMessageDialog(this,
                "Compare: " + compareList.get(0).getName() + " vs " + compareList.get(1).getName());
        });
        topPanel.add(viewCompareButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ----- Scrollable Center Panel -----
        neighborhoodListPanel = new JPanel();
        neighborhoodListPanel.setLayout(new BoxLayout(neighborhoodListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(neighborhoodListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Sort dropdown listener
        sortDropdown.addActionListener(e -> refreshNeighborhoods());

        // Init
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

        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(n.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        infoPanel.add(nameLabel);

        JLabel descLabel = new JLabel("<html><p style='width:450px'>" + n.getDescription() + "</p></html>");
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoPanel.add(descLabel);

        card.add(infoPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton viewBtn = new JButton("View");
        JButton compareBtn = new JButton("Add to Compare");

        viewBtn.addActionListener(e -> showNeighborhoodDetails(n));

        compareBtn.addActionListener(e -> {
            if (compareList.contains(n)) {
                JOptionPane.showMessageDialog(this, "Already added.");
            } else if (compareList.size() >= 2) {
                JOptionPane.showMessageDialog(this, "You can only compare 2 neighborhoods at a time.");
            } else {
                compareList.add(n);
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
            "<p><b>Livability Score:</b> %.2f</p></html>",
            n.getName(),
            n.getDescription(),
            n.getAverageRent(),
            n.getCrimeRate(),
            n.getSchoolRating(),
            n.getGreenSpaceScore(),
            n.getLivabilityScore()
        );

        JOptionPane.showMessageDialog(this, message, "Neighborhood Details", JOptionPane.INFORMATION_MESSAGE);
    }
}