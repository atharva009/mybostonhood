package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;

public class ExploreNeighborhoodPage extends JFrame {

    private NeighborhoodDirectory directory;

    public ExploreNeighborhoodPage(NeighborhoodDirectory directory) {
        this.directory = directory;

        setTitle("Explore Boston Neighborhoods");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Get all neighborhood names
        List<String> names = directory.getAllNeighborhoodNames();

        for (String name : names) {
            Neighborhood n = directory.getNeighborhood(name);
            JPanel card = createNeighborhoodCard(n);
            mainPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);
        setVisible(true);
    }

    private JPanel createNeighborhoodCard(Neighborhood neighborhood) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setPreferredSize(new Dimension(700, 100));
        card.setMaximumSize(new Dimension(700, 100));

        JLabel nameLabel = new JLabel("<html><b>" + neighborhood.getName() + "</b><br/>" +
                "Rent: $" + neighborhood.getAverageRent() + "<br/>" +
                "Crime Rate: " + neighborhood.getCrimeRate() + "<br/>" +
                "School Rating: " + neighborhood.getSchoolRating() + "<br/>" +
                "Green Space: " + neighborhood.getGreenSpaceScore() + "</html>");

        JButton compareButton = new JButton("Add to Compare");
        compareButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, neighborhood.getName() + " added to compare (TODO)");
            // TODO: Implement comparison logic
        });

        card.add(nameLabel, BorderLayout.CENTER);
        card.add(compareButton, BorderLayout.EAST);

        return card;
    }
}