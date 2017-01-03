package edu.msg.library_client.desktop;

import edu.msg.library_client.desktop.jfxgui.controller.MainWindowController;
import javafx.application.Application;

public abstract class UiFactory {

	public static void getUi() {
		
		Application.launch(MainWindowController.class);
//		new MainConsole().startConsole();
		
	}
}
