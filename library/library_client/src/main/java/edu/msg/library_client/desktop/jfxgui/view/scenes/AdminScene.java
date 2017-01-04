package edu.msg.library_client.desktop.jfxgui.view.scenes;

import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class AdminScene extends Scene{

	
	private TabPane tabPane;
	
	//user tab
	private Label userTabTitle;
	private Tab userTab;
	private TextField searchUserField;
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
	
	private TableView<User> userTable;
	private TableColumn<User, String> nameColumn;
	private TableColumn<User, LoginAccess> userTypeColumn;
	private TableColumn<User, Integer> loyaltyColumn;
	
	
	
	//shelf tab
	private Label shelfTabTitle;
	private Tab shelfTab;
	private TextField searchPublicationField;
	
	private TableView<Publication> publicationsTable;
	private TableColumn<Publication, String> titleColumn;
	private TableColumn<Publication, String> pubTypeColumn;
	
	
	
	
	public AdminScene(Parent root) {
		super(root, 600, 600);
		
		
		
		tabPane = new TabPane();
		
		createUserTab();
		
		createShelfTab();
		
		tabPane.getTabs().addAll(userTab, shelfTab);
		
		
		setRoot(tabPane);
	}

	private void createUserTab() {
		
		userTab = new Tab("Use Management");
		userTabTitle = new Label("User Panel");
		userTabTitle.setFont(new Font("Arial", 20));
		
		createUserTable();
		createUserSearchField();
		createUserButtons();
		
		
		BorderPane userTabPane = new BorderPane();
		userTab.setContent(userTabPane);
		
		VBox userTabLeftSide = new VBox();
		userTabLeftSide.setPadding(new Insets(10, 10, 20, 10));
		userTabLeftSide.setSpacing(15);
		
		HBox bottomButtons = new HBox();
		bottomButtons.setPadding(new Insets(0, 10, 0, 10));
		bottomButtons.setSpacing(10);
		bottomButtons.getChildren().addAll(addUserButtonMenu, editUserButton, deleteUserButton);
		
		userTabLeftSide.getChildren().addAll(userTabTitle, userTable, searchUserField, bottomButtons);
		userTabPane.setLeft(userTabLeftSide);
		
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
		
		userTabPane.setCenter(userManagerMenu);
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
	
	private void createUserSearchField() {
		
		searchUserField = new TextField();
		searchUserField.setPromptText("Search user");
		searchUserField.setMaxWidth(200);
	}
	
	private void createShelfTab() {
		
		shelfTab = new Tab("Library Shelf");
		shelfTabTitle = new Label("Library Shelf");
		shelfTabTitle.setFont(new Font("Arial", 20));
		
		createShelfTable();
		createPublicationsSearchField();
		
		VBox shelfLeftSide = new VBox();
		shelfLeftSide.setPadding(new Insets(10, 10, 20, 10));
		shelfLeftSide.setSpacing(15);
		
		shelfLeftSide.getChildren().addAll(shelfTabTitle, publicationsTable, searchPublicationField);
		
		BorderPane shelfTabPane = new BorderPane();
		shelfTab.setContent(shelfTabPane);
		shelfTabPane.setLeft(shelfLeftSide);
		
		
	}
	
	private void createPublicationsSearchField() {
		
		searchPublicationField = new TextField();
		searchPublicationField.setPromptText("Search on shelf");
		searchPublicationField.setMaxWidth(200);
	}
	
	private void createUserTable() {
		
		userTable = new TableView<User>();
		userTable.setMinWidth(300);
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
	
	private void createShelfTable() {
		
		publicationsTable = new TableView<Publication>();
		publicationsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		publicationsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		publicationsTable.setEditable(false);
		
		titleColumn = new TableColumn<Publication, String>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		
		pubTypeColumn = new TableColumn<Publication, String>("Type");
		pubTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Publication,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Publication, String> cd) {
				
				System.out.println();
				return new ReadOnlyStringWrapper(cd.getValue().getClass().getSimpleName());
				
			}
		});
		
		publicationsTable.getColumns().addAll(titleColumn, pubTypeColumn);
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
	
	public TextField getUserSearchField() {
		
		return searchUserField;
	}
	
	public TextField getPublicationSearchField() {
		
		return searchPublicationField;
	}
	
	public void clearUserSearch() {
		
		searchUserField.clear();
	}
	
	public void clearPublicationSearch() {
		
		searchPublicationField.clear();
	}
	
	public Button getAddUserButtonMenu() {
		
		return addUserButtonMenu;
	}
	
	public Button getUserManagerButton() {
		
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
	
	public Button getDeleteUserButton() {
		
		return deleteUserButton;
	}
	
	public TableView<Publication> getPublicationTable() {
		
		return publicationsTable;
	}
}
























