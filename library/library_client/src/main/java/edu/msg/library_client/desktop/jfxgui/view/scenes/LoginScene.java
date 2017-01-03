package edu.msg.library_client.desktop.jfxgui.view.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginScene extends Scene{

	
	private GridPane grid;
	
	private Text title;
	private Label userName;
	private Label password;
	
	private TextField userTextField;
	private PasswordField passwordField;
	
	private Button loginButton;
	private HBox hBox;
	
	public LoginScene(Parent root) {
		super(root, 350, 275);

		createGrid();
		
		createLabels();
		createTextFields();

		title = new Text("Login");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		
		createButton();
		
		setupGrid();
		
		setRoot(grid);
	}
	
	private void createGrid() {
		
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
	}
	
	private void createLabels() {
		
		userName = new Label("User Name:");
		password = new Label("Password");
		
	}
	
	private void createTextFields() {
		
		userTextField = new TextField();
		passwordField = new PasswordField();
	}
	
	private void createButton() {
		
		loginButton = new Button("Login");
		
		
		hBox = new HBox(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().add(loginButton);
		
	}
	
	private void setupGrid() {
		
		grid.add(title, 0, 0, 2, 1);
		grid.add(userName, 0, 2);
		grid.add(password, 0, 3);
		grid.add(userTextField, 1, 2);
		grid.add(passwordField, 1, 3);
		grid.add(hBox, 1, 6);
		
	}
	
	public Button getLoginButton() {
		
		return loginButton;
	}
	
	public String getUserNameText() {
		
		return userTextField.getText();
	}

	public String getPasswordText() {
		
		return passwordField.getText();
	}
}
