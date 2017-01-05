package edu.msg.library_client.desktop.jfxgui.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.msg.library_client.desktop.jfxgui.listeners.UserSelectedListener;
import edu.msg.library_client.desktop.jfxgui.model.ConnectionModel;
import edu.msg.library_client.desktop.jfxgui.view.scenes.AdminScene;
import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AdminController implements UserSelectedListener{

	private enum Menu {

		add, edit, delete, none
	}

	private AdminScene adminScene;
	
	private ObservableList<User> userData;
	private ObservableList<Publication> publicationsData;
	private ObservableList<Publication> userBorrowedData;
	private FilteredList<User> filteredUsers;
	private FilteredList<Publication> filteredPublications;
	private FilteredList<Publication> filteredUserBorrowed;

	private Menu displayed;
	private boolean userManagerMenuVisible;

	public AdminController(Parent root) {

		adminScene = new AdminScene(root);

		
		setTabPaneEvents();
		setupSearchFields();

		displayed = Menu.none;
		userManagerMenuVisible = false;
		createButtonEvents();

		userData = FXCollections.observableArrayList();
		loadUsers();
		publicationsData = FXCollections.observableArrayList();
		
		userBorrowedData = FXCollections.observableArrayList();
		adminScene.getSearchUserWithHintField().addUserSelectedListener(this);
//		TreeSet<User> users = new TreeSet<User>();
//		
//		for(User user: userData) {
//			
//			users.add(user)
//		}
		
		adminScene.populateSearchAutoComplete(userData.stream().map(user -> user).collect(Collectors.toSet()));
//		userData.stream().fi
		//loadPublications();
		
	}

	private void createButtonEvents() {

		adminScene.getAddUserButtonMenu().setOnAction(e -> {

			adminScene.clearUserManagerMenu();
			showOrHideUserManagerMenu(Menu.add);
			displayed = Menu.add;

			adminScene.setUserManagerTitle("Add User");
			adminScene.setUserManagerButtonText("Add");

		});

		adminScene.getUserManagerButton().setOnAction(e -> {

			switch (adminScene.getUserManagerButton().getText()) {

			case "Add":

				ConnectionModel.INSTANCE.addUser(adminScene.getUserNameText(), adminScene.getUserPassText(),
						adminScene.getUserType());
				break;
			case "Save":

				User user = new User();
				user.setUUID(adminScene.getSelectedUser().getUUID());
				user.setName(adminScene.getUserNameText());
				user.setPassword(adminScene.getUserPassText());
				user.setUserType(adminScene.getUserType());
				user.setLoyalityIndex(adminScene.getSelectedUser().getLoyalityIndex());

				ConnectionModel.INSTANCE.editUser(user);
				break;
			}

			loadUsers();
			userManagerMenuVisible = false;
			adminScene.hideUserManegerMenu();

		});

		adminScene.getEditButton().setOnAction(e -> {

			try {

				adminScene.clearUserManagerMenu();
				adminScene.setUserNameFieldText(adminScene.getSelectedUser().getName());
				adminScene.setUserTypeCombo(adminScene.getSelectedUser().getUserType());

				showOrHideUserManagerMenu(Menu.edit);
				displayed = Menu.edit;

				adminScene.setUserManagerTitle("Edit User");
				adminScene.setUserManagerButtonText("Save");

			} catch (NullPointerException ex) {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("No user is selected!");

				alert.showAndWait();
			}

		});

		adminScene.getDeleteUserButton().setOnAction(e -> {

			try {
				
				adminScene.getSelectedUser().getName();
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Delete User");
				alert.setHeaderText("Are you sure you want to delete this user?");

				ButtonType yes = new ButtonType("Yes");
				ButtonType no = new ButtonType("No");

				alert.getButtonTypes().setAll(yes, no);

				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == yes) {

					ConnectionModel.INSTANCE.deleteUser(adminScene.getSelectedUser());
				}

				if (result.get() == no) {
					
					alert.close();
				}
				
				loadUsers();
			} catch (NullPointerException ex) {
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("No user is selected!");

				alert.showAndWait();
			}
			
			
		});
		
		adminScene.getLendButton().setOnAction(e -> {
					
			try {
				
				Borrowing publication = new Borrowing();
				publication.setUserUuid(adminScene.getSearchUserWithHintField().getSelecteduser().getUUID());
				publication.setPublicationUuid(adminScene.getPublicationTable().getSelectionModel().getSelectedItem().getUUID());
				
				publication.setBorrowingDate(Date.valueOf(LocalDate.now()));
				publication.setDeadline(Date.valueOf(LocalDate.now().plusDays(20)));
				
				ConnectionModel.INSTANCE.borrow(publication);
				
				loadPublications();
				loadUserBorrows(adminScene.getSearchUserWithHintField().getSelecteduser());
			
			} catch (NullPointerException ex) {
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Select a publication and a user to lend to!");
				alert.showAndWait();
			
			}
		});
		
		adminScene.getTakeBackButton().setOnAction(e -> {
			
			try {
			
				Borrowing borrow = new Borrowing();
				borrow.setUserUuid(adminScene.getSearchUserWithHintField().getSelecteduser().getUUID());
				borrow.setPublicationUuid(adminScene.getUserBorrowingsTable().getSelectionModel().getSelectedItem().getUUID());
	
				ConnectionModel.INSTANCE.handBackPublication(searchForBorrow(borrow.getUserUuid(), borrow.getPublicationUuid()));
				
				loadPublications();
				loadUserBorrows(adminScene.getSearchUserWithHintField().getSelecteduser());
			
			} catch (NullPointerException ex) {
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Select a publication and a user to lend to!");
				alert.showAndWait();
				
			}

		});
	}
	
	private Borrowing searchForBorrow(String userUUID, String publicationUUID) {
		
		List<Entity> allBorrows = ConnectionModel.INSTANCE.getAllBorrows();

		for (int i = 0; i < allBorrows.size(); i++) {

			Borrowing borrowTmp = (Borrowing) allBorrows.get(i);

			if (borrowTmp.getUserUuid().equals(userUUID) && borrowTmp.getPublicationUuid().equals(publicationUUID)) {

				return borrowTmp;
			}
		}
		
		return null;
	}

	private void showOrHideUserManagerMenu(Menu newMenu) {

		if (userManagerMenuVisible) {

			if (displayed.equals(newMenu)) {

				if (!newMenu.equals(Menu.edit)) {

					userManagerMenuVisible = false;
					adminScene.hideUserManegerMenu();
				}
			}
		} else {

			userManagerMenuVisible = true;
			adminScene.showUserManagerMenu();
		}
	}

	private void setTabPaneEvents() {

		adminScene.getTabPane().getSelectionModel().selectedIndexProperty()
				.addListener((obsValue, oldValue, newValue) -> {

					switch (newValue.intValue()) {

					case 0:
						
						adminScene.clearUserSearch();
						loadUsers();

						break;
					case 1:
						
						adminScene.clearPublicationSearch();
						loadPublications();
						
						break;
					}

				});

		adminScene.getUserTable().focusedProperty().addListener((obsValue, oldValue, newValue) -> {

			if (newValue == false) {

				// adminScene.getUserTable().getSelectionModel().select(null);
			}

		});

	}

	private void loadPublications() {
		
		publicationsData.clear();
		publicationsData.addAll(ConnectionModel.INSTANCE.getAllPublications());
		
		filteredPublications = new FilteredList<Publication>(publicationsData, p -> true);
		adminScene.getPublicationTable().setItems(filteredPublications);
		
	}
	
	private void loadUsers() {

		userData.clear();
		userData.addAll(ConnectionModel.INSTANCE.getAllUsers());

		filteredUsers = new FilteredList<User>(userData, p -> true);
		adminScene.getUserTable().setItems(filteredUsers);
	}
	
	@Override
	public void loadUserBorrows(User user) {
		
		userBorrowedData.clear();

		List<Publication> currentUserBorrows = new ArrayList<>();		
			
		List<Entity> allBorrows = ConnectionModel.INSTANCE.getAllBorrows();

		for (int i = 0; i < allBorrows.size(); i++) {

			Borrowing borrowtemp = (Borrowing) allBorrows.get(i);

			if (borrowtemp.getUserUuid().equals(user.getUUID())) {

				Publication tmpPub;
				tmpPub = ConnectionModel.INSTANCE.serchPublicationByUUID(borrowtemp.getPublicationUuid()).get(0);
				currentUserBorrows.add(tmpPub);
			}
		}
			
		userBorrowedData.addAll(currentUserBorrows);
		filteredUserBorrowed = new FilteredList<Publication>(userBorrowedData, p -> true);
		adminScene.getUserBorrowingsTable().setItems(filteredUserBorrowed);
		
	}

	private void setupSearchFields() {

		adminScene.getUserSearchField().textProperty().addListener((observable, oldValue, newValue) -> {

			filteredUsers.setPredicate((user -> {

				if (newValue == null || newValue.isEmpty()) {
					
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (user.getName().toLowerCase().contains(lowerCaseFilter)) {

					return true;
				}
				return false;
			}));
		});
		
		adminScene.getPublicationSearchField().textProperty().addListener((observable, oldValue, newValue) -> {
			
			filteredPublications.setPredicate(publication -> {
				
				if (newValue == null || newValue.isEmpty()) {
					
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (publication.getTitle().toLowerCase().contains(lowerCaseFilter)) {
					
					return true;
				}
				
				return false;				
			});			
		});

	}

	public AdminScene getScene() {

		return adminScene;
	}
}
