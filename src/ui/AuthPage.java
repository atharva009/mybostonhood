package ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import util.ADTStack.NavigationStack;
import util.UIHelper;
import util.ADTHashMap.NeighborhoodDirectory;

import java.awt.Font;

public class AuthPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    // Dummy admin credentials
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin123";

    private NeighborhoodDirectory directory; // Added directory field

    public AuthPage(NeighborhoodDirectory directory) {
        this.directory = directory;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        NavigationStack.push(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUserName = new JLabel("Username :");
        lblUserName.setBounds(69, 80, 99, 16);
        contentPane.add(lblUserName);

        txtUsername = new JTextField();
        txtUsername.setBounds(184, 75, 130, 26);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password :");
        lblPassword.setBounds(69, 144, 87, 21);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(184, 141, 130, 26);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

        JLabel lblWelcomeText = new JLabel("Welcome Admin!");
        lblWelcomeText.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblWelcomeText.setBounds(163, 23, 146, 26);
        contentPane.add(lblWelcomeText);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new AdminDashboard(directory).setVisible(true); // Pass the directory
                dispose(); // Close auth page
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials. Try again.");
            }
        });

        btnLogin.setBounds(177, 211, 117, 29);
        contentPane.add(btnLogin);

        JButton btnBack = new JButton("<< Back ");
        btnBack.addActionListener(e -> {
            NavigationStack.back();
        });
        btnBack.setBounds(6, 10, 117, 29);
        contentPane.add(btnBack);
        
        UIHelper.maximize(this);
    }
}