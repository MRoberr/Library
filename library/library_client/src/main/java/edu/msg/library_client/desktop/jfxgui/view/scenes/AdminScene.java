package edu.msg.library_client.desktop.jfxgui.view.scenes;

import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.User;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AdminScene extends Scene{

	private Label title;
	
	private TabPane tabPane;
	
	//user tab
	private Tab userTab;
	private TextField searchField;
	private Button addUserButtonMenu;
	private Button editUserButton;
	private Button deleteUserButton;
	
	
	//user manager menu
	private GridPane userManagerMenu;
	private Text userManagerTitle;
	private TextField userNameField;
	private PasswordField userPassField;
	private ComboBox<String> userTypeCombo;
	private Button userManagerButton; //add or save
	
	
	//shelf tab
	private Tab shelfTab;
	
	private TableView<User> userTable;
	private TableColumn<User, String> nameColumn;
	private TableColumn<User, LoginAccess> userTypeColumn;
	private TableColumn<User, Integer> loyaltyColumn;
	
	
	
	public AdminScene(Parent root) {
		super(root, 600, 600);
		
		title = new Label("User Panel");
		title.setFont(new Font("Arial", 20));
		
		tabPane = new TabPane();
		createUserTab();
		createShelfTab();
		tabPane.getTabs().addAll(userTab, shelfTab);
		
		
		setRoot(tabPane);
	}

	private void createUserTab() {
		
		userTab = new Tab("Use Management");
		
		createTable();
		createSearchField();
		createUserButtons();
		
		
		BorderPane pane = new BorderPane();
		userTab.setContent(pane);
		
		VBox leftSide = new VBox();
		leftSide.setPadding(new Insets(10, 10, 20, 10));
		leftSide.setSpacing(15);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(0, 10, 0, 10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(addUserButtonMenu, editUserButton, deleteUserButton);
		
		leftSide.getChildren().addAll(title, userTable, searchField, hbox);
		pane.setLeft(leftSide);
		
		userManagerMenu = new GridPane();
		userManagerMenu.setVgap(15);
		userManagerMenu.setPadding(new Insets(10, 10, 20, 10));
		userManagerMenu.setAlignment(Pos.CENTER);
		
		userManagerMenu.setVisible(false);
		
		createUserManagerItems();
		
		userManagerMenu.add(userManagerTitle, 0, 0);
		userManagerMenu.add(userNameField, 0, 1);
		userManagerMenu.add(userPassField, 0, 2);
		userManagerMenu.add(userTypeCombo, 0, 3);
		userManagerMenu.add(userManagerButton, 0, 4);
		
		pane.setCenter(userManagerMenu);
	}
	
	private void createUserManagerItems() {
		
		userManagerTitle = new Text();
		userManagerTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		
		userNameField = new TextField();
		userNameField.setPromptText("Username");
		
		userPassField = new PasswordField();
		userPassField.setPromptText("Password");
		
		userTypeCombo = new ComboBox<String>(FXCollections.observableArrayList(
			"ADMIN",
			"USER"
		));
		userTypeCombo.setPromptText("User Type");
		
		userManagerButton = new Button();
	}
	
	private void createUserButtons() {
		
		addUserButtonMenu = new Button("Add Menu");
		editUserButton = new Button("Edit");
		deleteUserButton = new Button("Delete");
	}
	
	private void createSearchField() {
		
		searchField = new TextField();
		searchField.setPromptText("Search user");
		searchField.setMaxWidth(200);
	}
	
	private void createShelfTab() {
		
		shelfTab = new Tab("Library Shelf");
	}
	
	private void createTable() {
		
		userTable = new TableView<User>();
		userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		userTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		userTable.setEditable(false);
		
		//name
		nameColumn = new TableColumn<User, String>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//user type
		userTypeColumn = new TableColumn<User, LoginAccess>("User Type");
		userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
		
		//loyalty
		loyaltyColumn = new TableColumn<User, Integer>("Loyalty");
		loyaltyColumn.setCellValueFactory(new PropertyValueFactory<>("loyalityIndex"));
		
		userTable.getColumns().addAll(nameColumn, userTypeColumn, loyaltyColumn);		
		
	}
	
	public void showUserManagerMenu() {
		
		userManagerMenu.setVisible(true);
	}
	
	public void hideUserManegerMenu() {
		
		userManagerMenu.setVisible(false);
	}
	
	public TableView<User> getUserTable() {
		
		return userTable;
	}
	
	public TabPane getTabPane() {
		
		return tabPane;
	}
	
	public TextField getSearchField() {
		
		return searchField;
	}
	
	public void clearSearch() {
		
		searchField.clear();
	}
	
	public Button getAddUserButtonMenu() {
		
		return addUserButtonMenu;
	}
	
	public Button getAddUserButton() {
		
		return userManagerButton;
	}
	
	public String getUserNameText() {
		
		return userNameField.getText();
	}
	
	public String getUserPassText() {
		
		return userPassField.getText();
	}
	
	public LoginAccess getUserType() {
		
		return LoginAccess.valueOf(userTypeCombo.getSelectionModel().getSelectedItem());
	}
	public Button getEditButton() {
		
		return editUserButton;
	}
	
	public void setUserManagerTitle(String title) {
		
		userManagerTitle.setText(title);
	}
	
	public void setUserManagerButtonText(String text) {
		
		userManagerButton.setText(text);
	}
	
	public User getSelectedUser() throws NullPointerException {
		
		return userTable.getSelectionModel().getSelectedItem();
	}
	
	public void clearUserManagerMenu() {
		
		userNameField.clear();
		userPassField.clear();
		userTypeCombo.getSelectionModel().clearSelection();
	}
	
	public void setUserNameFieldText(String name) {
		
		userNameField.setText(name);
		System.out.println(name);
	}
	
	public void setUserTypeCombo(LoginAccess userType) {
		
		userTypeCombo.getSelectionModel().select(userType.toString());
	}
}
























