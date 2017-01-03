package edu.msg.library_client.desktop.GUI.view.cards;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;

	private JLabel title;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	
	private JTextField userNameField;
	private JPasswordField passwordField;
	
	private JButton loginButton;
	
	public LoginPanel() {
		
		createLabels();
		createTextFields();
		
		loginButton = new JButton("Login");
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		
		setLayout(new GridLayout(3, 2, 10, 10));
		
		fillPanel();
	}
	
	private void createLabels(){ 
		
		title = new JLabel("Login");
		title.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		userNameLabel = new JLabel("User Name:");
		passwordLabel = new JLabel("Password:");
	}
	
	private void createTextFields() {
		
		userNameField = new JTextField();
		passwordField = new JPasswordField();
	}
	
	private void fillPanel() {
		
		add(title);
		add(userNameLabel);
		add(userNameField);
	}
}
