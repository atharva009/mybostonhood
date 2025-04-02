package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import util.ADTStack.NavigationStack;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the dashboard
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminDashboard frame = new AdminDashboard();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        NavigationStack.push(this); // ✅ Add to navigation stack

        JLabel lblNewLabel = new JLabel("Admin Dashboard");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblNewLabel.setBounds(156, 19, 168, 16);
        contentPane.add(lblNewLabel);

        // Add Neighbourhood Button
        JButton btnAddNeighbourhood = new JButton("Add Neighbourhood");
        btnAddNeighbourhood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 👉 You can create AddNeighbourhoodPage and open here
                JOptionPane.showMessageDialog(null, "Add Neighbourhood clicked (To Implement)");
            }
        });
        btnAddNeighbourhood.setBounds(38, 70, 183, 29);
        contentPane.add(btnAddNeighbourhood);

        // Edit Neighbourhood Button
        JButton btnEditNeighbourhood = new JButton("Edit Neighbourhood");
        btnEditNeighbourhood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 👉 You can create EditNeighbourhoodPage and open here
                JOptionPane.showMessageDialog(null, "Edit Neighbourhood clicked (To Implement)");
            }
        });
        btnEditNeighbourhood.setBounds(233, 70, 168, 29);
        contentPane.add(btnEditNeighbourhood);

        // View All Neighbourhood Button
        JButton btnViewNeighbourhood = new JButton("View All Neighbourhood");
        btnViewNeighbourhood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 👉 You can create ViewNeighbourhoodPage and open here
                JOptionPane.showMessageDialog(null, "View All Neighbourhood clicked (To Implement)");
            }
        });
        btnViewNeighbourhood.setBounds(131, 134, 196, 29);
        contentPane.add(btnViewNeighbourhood);

        // BACK button
        JButton btnBack = new JButton(" <<< BACK");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ✅ Stack navigation back
                NavigationStack.back();
            }
        });
        btnBack.setBounds(6, 15, 117, 29);
        contentPane.add(btnBack);
    }
}
