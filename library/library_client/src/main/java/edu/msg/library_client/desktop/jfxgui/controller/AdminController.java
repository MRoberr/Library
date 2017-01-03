package edu.msg.library_client.desktop.jfxgui.controller;

import java.util.Optional;

import edu.msg.library_client.desktop.jfxgui.model.ConnectionModel;
import edu.msg.library_client.desktop.jfxgui.view.scenes.AdminScene;
import edu.msg.library_common.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AdminController {

	private enum Menu {

		add, edit, delete, none
	}

	private AdminScene adminScene;
	private ObservableList<User> userData;
	private FilteredList<User> filteredUsers;

	private Menu displayed;
	private boolean userManagerMenuVisible;

	public AdminController(Parent root) {

		adminScene = new AdminScene(root);

		setTabPaneEvents();
		setupSearchField();

		displayed = Menu.none;
		userManagerMenuVisible = false;
		createButtonEvents();

		userData = FXCollections.observableArrayList();
		loadUsers();
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
		});
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
					System.out.println(oldValue);
					switch (newValue.intValue()) {

					case 0:
						adminScene.clearSearch();
						loadUsers();

						break;
					case 1:
						break;
					}

				});

		adminScene.getUserTable().focusedProperty().addListener((obsValue, oldValue, newValue) -> {

			if (newValue == false) {

				// adminScene.getUserTable().getSelectionModel().select(null);
			}

		});

	}

	private void loadUsers() {

		userData.clear();
		userData.addAll(ConnectionModel.INSTANCE.getAllUsers());

		filteredUsers = new FilteredList<User>(userData, p -> true);
		adminScene.getUserTable().setItems(filteredUsers);
	}

	private void setupSearchField() {

		adminScene.getSearchField().textProperty().addListener((observable, oldValue, newValue) -> {

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

	}

	public AdminScene getScene() {

		return adminScene;
	}
}
