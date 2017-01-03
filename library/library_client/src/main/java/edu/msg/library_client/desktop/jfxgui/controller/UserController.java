package edu.msg.library_client.desktop.jfxgui.controller;

import edu.msg.library_client.desktop.jfxgui.model.ConnectionModel;
import edu.msg.library_client.desktop.jfxgui.view.scenes.UserScene;
import edu.msg.library_common.model.Publication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Parent;

public class UserController {

	
	private UserScene userScene;
	private FilteredList<Publication> filteredData;
	private ObservableList<Publication> tableData;
	private boolean isearchSetUp;
	
	public UserController(Parent root) {
		
		userScene = new UserScene(root);

		tableData= FXCollections.observableArrayList();	
		
		isearchSetUp = false;
		
		setButtonActions();
	}
	
	
	
	private void setButtonActions() {
		
		userScene.getBookButton().setOnAction(e -> {
			
			userScene.clearSearch();
			userScene.hideArticleColumn();
			tableData.clear();
			userScene.setSearchPromtText("Search book");
					
			tableData.addAll(ConnectionModel.INSTANCE.getAllBooks());	
			filteredData = new FilteredList<Publication>(tableData, p -> true);	
			userScene.getTable().setItems(filteredData);
			
			if(!isearchSetUp) {
				isearchSetUp = true;
				setupSearchField();
			}
		});
		
		
		
		userScene.getNewspaperButton().setOnAction(e -> {
			
			userScene.clearSearch();
			userScene.showArticleColumn();
			tableData.clear();
			userScene.setSearchPromtText("Search newspaper");
			
			tableData.addAll(ConnectionModel.INSTANCE.getAllNewspapers());	
			filteredData = new FilteredList<Publication>(tableData, p -> true);
			userScene.getTable().setItems(filteredData);
			
			if(!isearchSetUp) {
				isearchSetUp = true;
				setupSearchField();
			}
		});
		
		
		userScene.getMagazineButton().setOnAction(e -> {
			
			userScene.clearSearch();
			userScene.showArticleColumn();
			tableData.clear();
			userScene.setSearchPromtText("Search magazine");
						
			tableData.addAll(ConnectionModel.INSTANCE.getAllMagazines());	
			filteredData = new FilteredList<Publication>(tableData, p -> true);
			userScene.getTable().setItems(filteredData);
			
			if(!isearchSetUp) {
				isearchSetUp = true;
				setupSearchField();
			}
		});
		
	}
	
	private void setupSearchField() {
		
		 
		
		userScene.getSearchField().textProperty().addListener((observable, oldValue, newValue) -> {
			
			filteredData.setPredicate((publication -> {
				
				if (newValue == null || newValue.isEmpty()) {
                    return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (publication.getTitle().toLowerCase().contains(lowerCaseFilter)) {
					
                    return true;                 
                }
                return false; 
			}));
		});
		
		
		
	}
	
	public UserScene getScene() {
		
		return userScene;
	}
}
































