package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.ADTStack.NavigationStack;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	// Dummy admin credentials
	private final String ADMIN_USERNAME = "admin";
	private final String ADMIN_PASSWORD = "admin123";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthPage frame = new AuthPage();
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
	public AuthPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		NavigationStack.push(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(224, 10, 1, 1);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
		
		txtPassword = new JTextField();
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
		    String password = txtPassword.getText();

		    if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
		        JOptionPane.showMessageDialog(this, "Login Successful!");
		        new AdminDashboard().setVisible(true);
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
	}
}
