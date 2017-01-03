package edu.msg.library_client.desktop.jfxgui.controller;

import edu.msg.library_client.desktop.jfxgui.listeners.SceneSwitchListener;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowController extends Application implements SceneSwitchListener{

	
	private Stage window;
	private Group group;
	private LoginController loginController;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		window = stage;
		group = new Group();
		
		loginController = new LoginController(group, this);
		
		switchScene(loginController.getScene());
		
		setupStage();
	}
	
	private void setupStage() {
		
		window.setTitle("Bibliotheca");
		window.setResizable(false);
		window.show();
		
	}

	@Override
	public void switchScene(Scene scene) {

		window.setScene(scene);		
	}
}
