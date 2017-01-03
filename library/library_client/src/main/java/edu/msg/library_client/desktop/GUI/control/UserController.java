package edu.msg.library_client.desktop.GUI.control;

import edu.msg.library_client.desktop.GUI.view.cards.UserPanel;

public class UserController {

	
	private UserPanel userPanel;
	
	public UserController() {
		
		userPanel = new UserPanel();
	}
	
	public UserPanel getPanel() {
		
		return userPanel;
	}
}
