package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import util.ADTStack.NavigationStack;
import util.ADTHashMap.NeighborhoodDirectory;

public class LandingPage extends JFrame {

    private NeighborhoodDirectory neighborhoodDirectory;

    public LandingPage(NeighborhoodDirectory directory) {
        this.neighborhoodDirectory = directory;

        setTitle("MyBostonHood - Welcome");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        NavigationStack.push(this); // Push this frame onto the stack

        // ----- Main Panel -----
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to My Boston Hood", JLabel.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Explore Button
        JButton exploreButton = new JButton("Continue to Explore Neighborhood");
        exploreButton.setPreferredSize(new Dimension(280, 40));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exploreButton);

        // Admin Label as clickable link
        JLabel adminLabel = new JLabel("<HTML><U>Are you an admin?</U></HTML>");
        adminLabel.setForeground(Color.BLUE.darker());
        adminLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminPanel.add(adminLabel);

        // South Panel for buttons and admin link
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        southPanel.add(adminPanel, BorderLayout.SOUTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // ----- Action Listeners -----

        // Explore Neighborhoods
        exploreButton.addActionListener(e -> {
            this.setVisible(false);
            new ExploreNeighborhoodPage(neighborhoodDirectory); // Pass directory to next page
        });

        // Admin Login
        adminLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new AuthPage().setVisible(true);
                setVisible(false);
            }
        });

        // ----- Final Frame Setup -----
        add(mainPanel);
        setVisible(true);
    }
}