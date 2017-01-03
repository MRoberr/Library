package edu.msg.library_client.desktop;

//import edu.msg.library_client.desktop.console.MainConsole;
import edu.msg.library_client.desktop.console.MainConsole;

public abstract class UiFactory {

	public static void getUi() {
		
//		new MainController();
		new MainConsole().startConsole();
		
	}
}
