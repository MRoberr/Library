package edu.msg.library_client.desktop.GUI.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.msg.library_client.desktop.GUI.view.cards.AdminPanel;
import edu.msg.library_client.desktop.GUI.view.cards.LoginPanel;
import edu.msg.library_client.desktop.GUI.view.cards.UserPanel;

public class MainWindow extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private JPanel cards;	
	
	private Dimension screenSize;
	
	public MainWindow() {
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		configureWindow();
		
		cards = new JPanel(new CardLayout());
		
		add(cards);
	}
	
	public void addCard(JPanel card, String cardName) {
		
		cards.add(card, cardName);
	}
	
	private void configureWindow() {
		
		setTitle("Bibliotheca");		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void showPanel(String panel) {
		
		int x = (int)screenSize.getWidth();
		int y = (int)screenSize.getHeight();
		
		CardLayout cardLayout = (CardLayout)cards.getLayout();
		
		switch(panel) {
		
		case "login":
			cardLayout.show(cards, "login");
			setBounds(x/3, y/3, x/3, y/3);
			break;
		case "admin":
			cardLayout.show(cards, "admin");
			setBounds(x/2, y/2, x/2, y/2);
			break;
		case "user":
			cardLayout.show(cards, "user");
			setBounds(x/2, y/2, x/2, y/2);
		}
	}

	
}
