package edu.msg.library_client.desktop.GUI.control;

import edu.msg.library_client.desktop.UiFactory;
import edu.msg.library_client.desktop.GUI.view.MainWindow;

public class MainWindowController extends UiFactory{

	private LoginController loginController;
	private AdminController adminController;
	private UserController userController;
	
	private MainWindow mainWindow;
	
	
	public MainWindowController() {
		
		mainWindow = new MainWindow();
		
		createControllers();
		addCards();
		
		mainWindow.showPanel("login");
	}
	
	private void createControllers() {
		
		loginController = new LoginController();
		adminController = new AdminController();
		userController = new UserController();
	}
	
	private void addCards() {
		
		mainWindow.addCard(loginController.getPanel(), "login");
		mainWindow.addCard(adminController.getPanel(), "admin");
		mainWindow.addCard(userController.getPanel(), "user");
	}
}
