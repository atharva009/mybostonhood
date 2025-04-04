package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import util.ADTHashMap.NeighborhoodDirectory;
import model.Neighborhood;
import util.ADTStack.NavigationStack;

public class AdminDashboard extends JFrame {

    private NeighborhoodDirectory directory;
    private JPanel cardsPanel;
    private JScrollPane scrollPane;

    public AdminDashboard(NeighborhoodDirectory directory) {
        this.directory = directory;

        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        NavigationStack.push(this);

        // ----- Main Container Panel -----
        JPanel mainPanel = new JPanel(new BorderLayout());

        // ----- Top Panel -----
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add New Neighborhood");
        topPanel.add(addButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // ----- Cards Panel (Scrollable) -----
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(cardsPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ----- Back Button -----
        JButton backButton = new JButton("<< Back");
        backButton.addActionListener(e -> NavigationStack.back());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // ----- Add Listeners -----
        addButton.addActionListener(e -> showAddDialog());

        // ----- Load Existing Neighborhoods -----
        refreshNeighborhoodList();

        // ----- Final Setup -----
        add(mainPanel);
    }

    private void refreshNeighborhoodList() {
        cardsPanel.removeAll();

        for (Neighborhood n : directory.getAllNeighborhoods()) {
            JPanel card = createNeighborhoodCard(n);
            cardsPanel.add(card);
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private JPanel createNeighborhoodCard(Neighborhood n) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel infoLabel = new JLabel("<html><b>" + n.getName() + "</b><br/>" + n.getDescription() + "</html>");
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.add(infoLabel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        // Edit action
        editBtn.addActionListener(e -> showEditDialog(n));

        // Delete action
        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this neighborhood?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                directory.removeNeighborhood(n.getName());
                refreshNeighborhoodList();
            }
        });

        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        card.add(buttonPanel, BorderLayout.EAST);

        return card;
    }

    private void showAddDialog() {
        JTextField nameField = new JTextField(20);
        JTextArea descArea = new JTextArea(3, 20);
        JScrollPane scroll = new JScrollPane(descArea);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);

        JTextField rentField = new JTextField(10);
        JTextField crimeField = new JTextField(10);
        JTextField schoolField = new JTextField(10);
        JTextField greenField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Neighborhood Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Description:"));
        panel.add(scroll);

        panel.add(new JLabel("Average Rent ($):"));
        panel.add(rentField);

        panel.add(new JLabel("Crime Rate (0.0 - 10.0):"));
        panel.add(crimeField);

        panel.add(new JLabel("School Rating (0.0 - 10.0):"));
        panel.add(schoolField);

        panel.add(new JLabel("Green Space Score (0 - 100):"));
        panel.add(greenField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Neighborhood", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String desc = descArea.getText().trim();
                double avgRent = Double.parseDouble(rentField.getText().trim());
                double crime = Double.parseDouble(crimeField.getText().trim());
                double school = Double.parseDouble(schoolField.getText().trim());
                int greenScore = Integer.parseInt(greenField.getText().trim());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name cannot be empty.");
                    return;
                }

                // Add neighborhood to the directory (update your method to accept these new fields)
                Neighborhood nh = new Neighborhood(name, desc, avgRent, crime, school, greenScore);
                directory.addNeighborhood(nh.getName(),nh);
                refreshNeighborhoodList();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for rent, crime rate, school rating, and green space.");
            }
        }
    }

    private void showEditDialog(Neighborhood n) {
        JTextField nameField = new JTextField(n.getName(), 20);
        JTextArea descArea = new JTextArea(n.getDescription(), 3, 20);
        JScrollPane scroll = new JScrollPane(descArea);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);

        JTextField rentField = new JTextField(String.valueOf(n.getAverageRent()), 10);
        JTextField crimeField = new JTextField(String.valueOf(n.getCrimeRate()), 10);
        JTextField schoolField = new JTextField(String.valueOf(n.getSchoolRating()), 10);
        JTextField greenField = new JTextField(String.valueOf(n.getGreenSpaceScore()), 10);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Neighborhood Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Description:"));
        panel.add(scroll);

        panel.add(new JLabel("Average Rent ($):"));
        panel.add(rentField);

        panel.add(new JLabel("Crime Rate (0.0 - 10.0):"));
        panel.add(crimeField);

        panel.add(new JLabel("School Rating (0.0 - 10.0):"));
        panel.add(schoolField);

        panel.add(new JLabel("Green Space Score (0 - 100):"));
        panel.add(greenField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Neighborhood", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String newName = nameField.getText().trim();
                String newDesc = descArea.getText().trim();
                double newRent = Double.parseDouble(rentField.getText().trim());
                double newCrime = Double.parseDouble(crimeField.getText().trim());
                double newSchool = Double.parseDouble(schoolField.getText().trim());
                int newGreen = Integer.parseInt(greenField.getText().trim());

                if (newName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name cannot be empty.");
                    return;
                }

                // Update neighborhood fields
                n.setName(newName);
                n.setDescription(newDesc);
                n.setAverageRent(newRent);
                n.setCrimeRate(newCrime);
                n.setSchoolRating(newSchool);
                n.setGreenSpaceScore(newGreen);

                refreshNeighborhoodList();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for rent, crime rate, school rating, and green space.");
            }
        }
    }
}