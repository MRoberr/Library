package edu.msg.library_client.desktop.jfxgui.controller;

import edu.msg.library_client.desktop.jfxgui.listeners.SceneSwitchListener;
import edu.msg.library_client.desktop.jfxgui.model.ConnectionModel;
import edu.msg.library_client.desktop.jfxgui.view.scenes.LoginScene;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

	private LoginScene loginScene;

	public LoginController(Group parent, SceneSwitchListener listener) {
		
		loginScene = new LoginScene(parent);
		
		//model.connect();
		
		loginScene.getLoginButton().setOnAction(e -> {
			
			switch(ConnectionModel.INSTANCE.login(loginScene.getUserNameText(), loginScene.getPasswordText())) {
			
			case ADMIN:
				listener.switchScene(new AdminController(parent).getScene());
				break;
			
			case USER:
				listener.switchScene(new UserController(parent).getScene());				
				break;
				
			case DENIED:
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Invalid login data");

				alert.showAndWait();
				break;
			}
			
		});
		
	}

	public LoginScene getScene() {

		return loginScene;
	}
}
