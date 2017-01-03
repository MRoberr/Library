package edu.msg.library_client.desktop.GUI.control;

import edu.msg.library_client.desktop.GUI.view.cards.LoginPanel;

public class LoginController {

	
	private LoginPanel loginPanel;
	
	
	public LoginController() {
	
		
		loginPanel = new LoginPanel();
	}
	
	public LoginPanel getPanel() {
		
		return loginPanel;
	}
}
