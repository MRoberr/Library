package edu.msg.library_client.desktop;

import edu.msg.library_client.desktop.console.MainConsole;
import edu.msg.library_client.desktop.jfxgui.controller.MainWindowController;
import javafx.application.Application;

public abstract class UiFactory {

	public static void getUi() {

		Application.launch(MainWindowController.class);

	}

	public static void getConsole() {

		new MainConsole().startConsole();

	}
}
