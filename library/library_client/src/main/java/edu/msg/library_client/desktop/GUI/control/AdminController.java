package edu.msg.library_client.desktop.GUI.control;

import edu.msg.library_client.desktop.GUI.view.cards.AdminPanel;

public class AdminController {

	private AdminPanel adminPanel;
	
	public AdminController() {
		
		adminPanel = new AdminPanel();
	}
	
	public AdminPanel getPanel() {
		
		return adminPanel;
	}
}
