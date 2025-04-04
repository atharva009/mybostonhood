package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import util.ADTBST.INeighborhoodSorter;
import util.ADTBST.NeighborhoodSorter;
import util.ADTBST.SortField;
import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;
import util.ADTStack.NavigationStack;

public class ExploreNeighborhoodPage extends JFrame {

    private NeighborhoodDirectory directory;
    private JPanel neighborhoodListPanel;
    private JComboBox<SortField> sortDropdown;
    private Map<SortField, NeighborhoodSorter> sorterMap;

    public ExploreNeighborhoodPage(NeighborhoodDirectory directory) {
        this.directory = directory;
        this.sorterMap = new HashMap<>();

        setTitle("Explore Boston Neighborhoods");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        NavigationStack.push(this);

        // Main layout
        setLayout(new BorderLayout());

        // Top Panel with dropdown
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Sort by:"));
        sortDropdown = new JComboBox<>(SortField.values());
        topPanel.add(sortDropdown);
        add(topPanel, BorderLayout.NORTH);

        // Center scroll panel to list neighborhoods
        neighborhoodListPanel = new JPanel();
        neighborhoodListPanel.setLayout(new BoxLayout(neighborhoodListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(neighborhoodListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Listener for sort option change
        sortDropdown.addActionListener(e -> refreshNeighborhoods());

        // Populate sorters and show initial list
        initializeSorters();
        refreshNeighborhoods();

        setVisible(true);
    }

    private void initializeSorters() {
        List<String> allNames = directory.getAllNeighborhoodNames();

        // Populate all sorters
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
        NeighborhoodSorter sorter = sorterMap.get(selectedField);
        List<Neighborhood> sortedList = sorter.getSortedNeighborhoods();

        for (Neighborhood n : sortedList) {
            JPanel card = createNeighborhoodCard(n);
            neighborhoodListPanel.add(card);
            neighborhoodListPanel.add(Box.createVerticalStrut(10));
        }

        neighborhoodListPanel.revalidate();
        neighborhoodListPanel.repaint();
    }

    private JPanel createNeighborhoodCard(Neighborhood n) {
        JPanel card = new JPanel(new GridLayout(0, 1));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.add(new JLabel("Name: " + n.getName()));
        card.add(new JLabel("Average Rent: $" + n.getAverageRent()));
        card.add(new JLabel("Crime Rate: " + n.getCrimeRate()));
        card.add(new JLabel("School Rating: " + n.getSchoolRating()));
        card.add(new JLabel("Green Space Score: " + n.getGreenSpaceScore()));
        card.add(new JLabel("Livability Score: " + n.getLivabilityScore()));

        return card;
    }
}