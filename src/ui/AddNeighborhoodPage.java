package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;
import util.ADTStack.*;
import util.UIHelper;

public class AddNeighborhoodPage extends JFrame {

    private JPanel contentPane;
    private JTextField nameField, rentField, crimeField, schoolField, greenField;
    private JTextArea descArea;
    private NeighborhoodDirectory directory;

    public AddNeighborhoodPage(NeighborhoodDirectory directory) {
        this.directory = directory;

        setTitle("Add Neighborhood");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIHelper.maximize(this);
        //NavigationStack.back();
       if (!NavigationStack.isEmpty() && (NavigationStack.peek() instanceof AddNeighborhoodPage))
    	   NavigationStack.back();
    	NavigationStack.push(this);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Top Panel (Back + Cancel)
        JPanel topPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("← Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> {
        			this.dispose();
        			NavigationStack.back();});
        topPanel.add(backButton, BorderLayout.WEST);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(e -> {
			this.dispose();
			NavigationStack.back();});
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(cancelButton);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPane.add(topPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        nameField = new JTextField(20);
        descArea = new JTextArea(5, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        rentField = new JTextField(10);
        crimeField = new JTextField(10);
        schoolField = new JTextField(10);
        greenField = new JTextField(10);

        form.add(new JLabel("Neighborhood Name:"));
        form.add(nameField);
        form.add(new JLabel("Description:"));
        form.add(new JScrollPane(descArea));
        form.add(new JLabel("Average Rent ($):"));
        form.add(rentField);
        form.add(new JLabel("Crime Rate (0.0 - 10.0):"));
        form.add(crimeField);
        form.add(new JLabel("School Rating (0.0 - 10.0):"));
        form.add(schoolField);
        form.add(new JLabel("Green Space Score (0 - 100):"));
        form.add(greenField);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> handleSave());

        JPanel bottom = new JPanel();
        bottom.add(saveBtn);

        contentPane.add(form, BorderLayout.CENTER);
        contentPane.add(bottom, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    private void handleSave() {
        try {
            String name = nameField.getText().trim();
            String desc = descArea.getText().trim();
            double rent = Double.parseDouble(rentField.getText().trim());
            double crime = Double.parseDouble(crimeField.getText().trim());
            double school = Double.parseDouble(schoolField.getText().trim());
            int green = Integer.parseInt(greenField.getText().trim());

            if (name.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name or description cannot be empty.");
                return;
            }
            
            // Name should be only letters and spaces
            if (!name.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(this, "Name should contain letters only.");
                return;
            }
            
            // Description validation: allow alphanumeric + special characters (basic check)
            if (!desc.matches("[\\w\\s.,'\"!@#$%^&*()\\-+=:;?/]*")) {
                JOptionPane.showMessageDialog(this, "Description contains invalid characters.");
                return;
            }
            
            // Value Limits check
            if (crime < 0.0 || crime > 10.0) {
                JOptionPane.showMessageDialog(this, "Crime Rate must be between 0.0 and 10.0.");
                return;
            }

            if (school < 0.0 || school > 10.0) {
                JOptionPane.showMessageDialog(this, "School Rating must be between 0.0 and 10.0.");
                return;
            }

            if (green < 0 || green > 100) {
                JOptionPane.showMessageDialog(this, "Green Space Score must be between 0 and 100.");
                return;
            }

            Neighborhood nh = new Neighborhood(name, desc, rent, crime, school, green);
            directory.addNeighborhood(name, nh);
            JOptionPane.showMessageDialog(this, "Neighborhood added successfully.");
            
            this.dispose();
            NavigationStack.back(); // Close old dashboards
            AdminDashboard dashboard = new AdminDashboard(directory);
            dashboard.setVisible(true);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
        }
    }
}