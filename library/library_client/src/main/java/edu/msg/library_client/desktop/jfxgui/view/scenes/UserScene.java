package edu.msg.library_client.desktop.jfxgui.view.scenes;

import java.sql.Date;

import edu.msg.library_common.model.Publication;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UserScene extends Scene {

	private Label title;

	private Button bookButton;
	private Button newspaperButton;
	private Button magazineButton;

	private TextField searchField;

	private TableView<Publication> table;

	private TableColumn<Publication, String> titleColumn;
	private TableColumn<Publication, String> articleColumn;
	private TableColumn<Publication, String> publisherColumn;
	private TableColumn<Publication, Date> releaseDateColumn;
	private TableColumn<Publication, Integer> inStockColumn;

	public UserScene(Parent root) {
		super(root, 600, 600);

		title = new Label("Library");
		title.setFont(new Font("Arial", 20));

		createButtons();
		createTable();
		createSearch();

		HBox hBox = new HBox();
		hBox.setSpacing(5);
		hBox.setPadding(new Insets(10, 0, 0, 10));
		hBox.getChildren().addAll(bookButton, newspaperButton, magazineButton);

		VBox vbox = new VBox();
		VBox.setVgrow(table, Priority.ALWAYS);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 20, 10));
		vbox.getChildren().addAll(title, hBox, table, searchField);

		setRoot(vbox);
	}

	private void createButtons() {

		bookButton = new Button("View Books");
		newspaperButton = new Button("View Newspapers");
		magazineButton = new Button("View Magazines");

	}

	private void createTable() {

		table = new TableView<Publication>();
		table.setEditable(false);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// title
		titleColumn = new TableColumn<Publication, String>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

		// article title
		articleColumn = new TableColumn<Publication, String>("Article");
		articleColumn.setCellValueFactory(new PropertyValueFactory<>("article_title"));

		// publisher
		publisherColumn = new TableColumn<Publication, String>("Publisher");
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

		// release date
		releaseDateColumn = new TableColumn<Publication, Date>("Release date");
		releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

		// copiest left
		inStockColumn = new TableColumn<Publication, Integer>("In Stock");
		inStockColumn.setCellValueFactory(new PropertyValueFactory<>("copiesLeft"));

		table.getColumns().addAll(titleColumn, articleColumn, publisherColumn, releaseDateColumn, inStockColumn);

	}

	private void createSearch() {

		searchField = new TextField();
		searchField.setPromptText("Disabled");
		searchField.setDisable(true);
		searchField.setMaxWidth(200);
		
	}
	
	public void clearSearch() {
		
		searchField.clear();
	}

	public TextField getSearchField() {
		
		return searchField;
	}
	
	public void setSearchPromtText(String text) {

		if (searchField.isDisabled()) {
			
			searchField.setDisable(false);
		}
		searchField.setPromptText(text);
	}

	public TableView<Publication> getTable() {

		return table;
	}

	public void showArticleColumn() {

		articleColumn.setVisible(true);
	}

	public void hideArticleColumn() {

		articleColumn.setVisible(false);
	}

	public Button getBookButton() {

		return bookButton;
	}

	public Button getNewspaperButton() {

		return newspaperButton;
	}

	public Button getMagazineButton() {

		return magazineButton;
	}

}
