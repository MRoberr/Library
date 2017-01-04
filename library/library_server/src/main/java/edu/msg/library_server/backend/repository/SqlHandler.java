package edu.msg.library_server.backend.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Newspaper;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;

public class SqlHandler {
	private static final String DBURL = "jdbc:mysql://localhost:3306/library?useSSL=false";
	private static final String USER = "library_admin";
	private static final String PASSWORD = "library_admin_pass";
	private Connection connection;
	private static SqlHandler instance;

	private SqlHandler() {
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
			connection.createStatement().executeUpdate(Publication.getCreateView());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized SqlHandler getInstance() {
		if (instance == null) {
			instance = new SqlHandler();
		}
		return instance;
	}

	public List<User> executeUserSelect(String select) {
		ResultSet resultSet = null;
		List<User> resultList = new ArrayList<>();
		try {
			resultSet = connection.createStatement().executeQuery(select);
			while (resultSet.next()) {
				User user = new User();
				user.setUUID(resultSet.getString("uuid"));
				user.setName(resultSet.getString("name"));
				int userTpyeNum = (resultSet.getInt("user_type"));
				if (userTpyeNum == 1) {
					user.setUserType(LoginAccess.ADMIN);
				} else {
					user.setUserType(LoginAccess.USER);
				}
				user.setLoyalityIndex(resultSet.getInt("loyality_index"));
				resultList.add(user);
			}
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Couldn't select users!");
			return null;
		}
	}

	public List<Book> executeBookSelect(String select) {
		ResultSet resultSet = null;
		List<Book> resultList = new ArrayList<>();
		try {
			resultSet = connection.createStatement().executeQuery(select);
			while (resultSet.next()) {
				Book book = new Book();
				book.setUUID(resultSet.getString("uuid"));
				book.setTitle(resultSet.getString("title"));
				book.setPublisher(resultSet.getString("publisher"));
				book.setReleaseDate(resultSet.getInt("release_date"));
				book.setNumberOfCopies(resultSet.getInt("nr_of_copies"));
				book.setCopiesLeft(resultSet.getInt("copies_left"));
				resultList.add(book);
			}
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Couldn't select users!");
			return null;
		}
	}

	public List<Magazine> executeMagazineSelect(String select) {
		ResultSet resultSet = null;
		List<Magazine> resultList = new ArrayList<>();
		try {
			resultSet = connection.createStatement().executeQuery(select);
			while (resultSet.next()) {
				Magazine magazin = new Magazine();
				magazin.setUUID(resultSet.getString("uuid"));
				magazin.setTitle(resultSet.getString("title"));
				magazin.setArticle_title(resultSet.getString("article_title"));
				magazin.setPublisher(resultSet.getString("publisher"));
				magazin.setReleaseDate(resultSet.getDate("release_date"));
				magazin.setNumberOfCopies(resultSet.getInt("nr_of_copies"));
				magazin.setCopiesLeft(resultSet.getInt("copies_left"));
				resultList.add(magazin);
			}
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Couldn't select users!");
			return null;
		}
	}

	public List<Newspaper> executeNewspaperSelect(String select) {
		ResultSet resultSet = null;
		List<Newspaper> resultList = new ArrayList<>();
		try {
			resultSet = connection.createStatement().executeQuery(select);
			while (resultSet.next()) {
				Newspaper newspaper = new Newspaper();
				newspaper.setUUID(resultSet.getString("uuid"));
				newspaper.setTitle(resultSet.getString("title"));
				newspaper.setArticle_title(resultSet.getString("article_title"));
				newspaper.setPublisher(resultSet.getString("publisher"));
				newspaper.setReleaseDate(resultSet.getDate("release_date"));
				newspaper.setNumberOfCopies(resultSet.getInt("nr_of_copies"));
				newspaper.setCopiesLeft(resultSet.getInt("copies_left"));
				resultList.add(newspaper);
			}
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Couldn't select users!");
			return null;
		}
	}

	public List<Publication> executePublicationSelect(String select) {
		ResultSet resultSet = null;
		List<Publication> resultList = new ArrayList<>();
		try {
			resultSet = connection.createStatement().executeQuery(select);
			while (resultSet.next()) {
				Publication publication;
				switch (resultSet.getString("type")) {
				case "1":
					publication = new Book();
					break;
				case "2":
					publication = new Newspaper();
					break;
				default:
					publication = new Magazine();
					break;
				}
				publication.setUUID(resultSet.getString("uuid"));
				publication.setTitle(resultSet.getString("title"));
				publication.setCopiesLeft(resultSet.getInt("stock"));
				resultList.add(publication);
			}
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Couldn't select users!");
			return null;
		}
	}

	public List<Entity> returnEntityOfExecute(String select, String entityType) {
		ResultSet resultSet = null;
		List<Entity> resultList = new ArrayList<>();
		try {
			resultSet = connection.createStatement().executeQuery(select);
			switch (entityType) {
			case "USER":
				while (resultSet.next()) {
					User user = new User();
					user.setUUID(resultSet.getString("uuid"));
					user.setName(resultSet.getString("name"));
					int userTpyeNum = (resultSet.getInt("user_type"));
					if (userTpyeNum == 1) {
						user.setUserType(LoginAccess.ADMIN);
					} else {
						user.setUserType(LoginAccess.USER);
					}
					user.setLoyalityIndex(resultSet.getInt("loyality_index"));
					resultList.add(user);
				}
				break;

			case "AUTHOR":
				while (resultSet.next()) {
					Author author = new Author();
					author.setUUID(resultSet.getString("uuid"));
					author.setName(resultSet.getString("name"));
					resultList.add(author);
				}
				break;
			case "BOOK":
				while (resultSet.next()) {
					Book book = new Book();
					book.setUUID(resultSet.getString("uuid"));
					book.setTitle(resultSet.getString("title"));
					book.setPublisher(resultSet.getString("publisher"));
					book.setReleaseDate(resultSet.getInt("release_date"));
					book.setNumberOfCopies(resultSet.getInt("nr_of_copies"));
					book.setCopiesLeft(resultSet.getInt("copies_left"));
					resultList.add(book);
				}
				break;
			case "MAGAZINE":
				while (resultSet.next()) {
					Magazine magazin = new Magazine();
					magazin.setUUID(resultSet.getString("uuid"));
					magazin.setTitle(resultSet.getString("title"));
					magazin.setArticle_title(resultSet.getString("article_title"));
					magazin.setPublisher(resultSet.getString("publisher"));
					magazin.setReleaseDate(resultSet.getDate("release_date"));
					magazin.setNumberOfCopies(resultSet.getInt("nr_of_copies"));
					magazin.setCopiesLeft(resultSet.getInt("copies_left"));
					resultList.add(magazin);
				}
				break;
			case "NEWSPAPER":
				while (resultSet.next()) {
					Newspaper newspaper = new Newspaper();
					newspaper.setUUID(resultSet.getString("uuid"));
					newspaper.setTitle(resultSet.getString("title"));
					newspaper.setArticle_title(resultSet.getString("article_title"));
					newspaper.setPublisher(resultSet.getString("publisher"));
					newspaper.setReleaseDate(resultSet.getDate("release_date"));
					newspaper.setNumberOfCopies(resultSet.getInt("nr_of_copies"));
					newspaper.setCopiesLeft(resultSet.getInt("copies_left"));
					resultList.add(newspaper);
				}
				break;
			case "BORROWING":
				while (resultSet.next()) {
					Borrowing borrow = new Borrowing();
					borrow.setUUID(resultSet.getString("uuid"));
					borrow.setPublicationUuid(resultSet.getString("publications_uuid"));
					borrow.setUserUuid(resultSet.getString("user_uuid"));
					borrow.setBorrowingDate(resultSet.getDate("borrowing_date"));
					borrow.setDeadline(resultSet.getDate("deadline"));
					borrow.setReturnDate(resultSet.getDate("returning_date"));
					resultList.add(borrow);
				}
				break;
			default: {
				throw new SQLException("Invalid entitytype.");
			}
			}

			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Couldn't select users!");
			return null;
		}
	}

	/*
	 * public List<Entity> executeSelect(String select, String entityType) {
	 * return returnEntityOfExecute(select, entityType); }
	 */

	public boolean executeSqlStatement(String select) {
		try {
			int i = connection.createStatement().executeUpdate(select);
			if (i >= 0) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Entity executeSingleSelect(String select, String entityType) {
		if (returnEntityOfExecute(select, entityType).size() == 1) {
			return returnEntityOfExecute(select, entityType).get(0);
		} else {
			return null;
		}
	}

	public LoginAccess executeLoginSelect(String userName, String password) throws SQLException {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select user_type from library.library_users where name = ? and password = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			switch (resultSet.getInt(1)) {
			case 1:
				return LoginAccess.ADMIN;
			case 0:
				return LoginAccess.USER;
			default:
				return LoginAccess.DENIED;
			}
		} catch (SQLException e) {
			throw new SQLException();
		}

	}

	// entityType USER, BORROW, BOOK, MAGAZINE, NEWSPAPER, AUTHOR
}
