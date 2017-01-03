package edu.msg.library_client.desktop.jfxgui.view;

import javafx.scene.Group;
import javafx.stage.Stage;

public class MainWindow {

	private Stage window;
	private Group root;
	
	
	public MainWindow(Stage stage) {
		
		window = stage;
	
		root = new Group();
	
		setupWindow();
	}

	private void setupWindow() {
		
		window.setTitle("Bibliotheca");
		window.setResizable(false);
		window.show();
	}
	
	public Group getGroup() {
		
		return root;
	}
	
	public Stage getWindow() {
		
		return window;
	}
	
}
