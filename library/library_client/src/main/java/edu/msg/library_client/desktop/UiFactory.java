package edu.msg.library_client.desktop;


import edu.msg.library_client.desktop.jfxgui.controller.MainWindowController;
import javafx.application.Application;

//import edu.msg.library_client.desktop.console.MainConsole;

public abstract class UiFactory {

	public static void getUiFatory() {
		
		Application.launch(MainWindowController.class);
//		new MainWindowController();
//		new MainConsole();
		
	}
}
