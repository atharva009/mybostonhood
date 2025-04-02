package ui;

import javax.swing.*;
import util.ADTStack.NavigationStack;
import java.awt.*;
import java.awt.event.*;

public class LandingPage extends JFrame {

    public LandingPage() {
        setTitle("MyBostonHood - Welcome");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        NavigationStack.push(this);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top: Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to My Boston Hood", JLabel.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Center: Continue Button
        JButton exploreButton = new JButton("Continue to Explore Neighborhood");
        exploreButton.setPreferredSize(new Dimension(280, 40));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exploreButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Bottom right: Admin link styled as a label
        JLabel adminLabel = new JLabel("<HTML><U>Are you an admin?</U></HTML>");
        adminLabel.setForeground(Color.BLUE.darker());
        adminLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminPanel.add(adminLabel);
        mainPanel.add(adminPanel, BorderLayout.SOUTH);

        // Use layered panel to stack button and admin label in same region
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        southPanel.add(adminPanel, BorderLayout.SOUTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Add listeners
        exploreButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Landing to explore (build next)");
            // TODO: Launch Landing Cards Page
        });

        adminLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new AuthPage().setVisible(true);
                setVisible(false); // hide current page but keep in stack
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    /** 
     * Main Method - Application Entry Point
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LandingPage();
        });
    }
}
