package edu.msg.library_client.desktop;

import edu.msg.library_client.desktop.GUI.control.MainController;
//import edu.msg.library_client.desktop.console.MainConsole;

public abstract class UiFactory {

	public static UiFactory getUiFatory() {
		
		return new MainController();
//		return new MainConsole();
		
	}
}