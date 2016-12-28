package edu.msg.library_server.backend.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.Magazin;
import edu.msg.library_common.model.User;

public class SqlHandler {
	private static final String DBURL = "jdbc:mysql://localhost:3306/library?useSSL=false";
	private static final String USER = "library_admin";
	private static final String PASSWORD = "library_admin_pass";
	private Connection connection;
	private static SqlHandler instance;

	private SqlHandler() {
		System.out.println("000");
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
			System.out.println("222");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized SqlHandler getInstance() {
		System.out.println("111");
		if (instance == null) {
			instance = new SqlHandler();
		}
		return instance;
	}

	public List<Entity> executeSelect(String select, String entityType) {
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
					if(userTpyeNum == 1) {
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
			case "":
				while (resultSet.next()) {
					// resultList.add();
				}
				break;
			default: {
				throw new SQLException("Invalid entitytype.");
			}
			}

			return resultList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Couldn't select users!");
			return null;
		}
	}
	
	public boolean executeInsert(String select) {
		try {
			int i = connection.createStatement().executeUpdate(select);
			if (i == 1) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;	
		}	
	}
	
	public boolean executeUpdate(String select) {
		try {
			int i = connection.createStatement().executeUpdate(select);
			if (i == 1) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;	
		}			
	}
	
	public boolean executeDelete(String select) {
		try {
			int i = connection.createStatement().executeUpdate(select);
			if (i == 1) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			return false;	
		}			
	}
	
	public Entity executeSingleSelect(String select, String entityType) {
		
		return null;		
	}
	
	public LoginAccess executeLoginSelect(String userName, String password) {
		
		return null;		
	}
	
	// entityType USER, BORROW, BOOK, MAGAZINE, NEWSPAPER, AUTHOR
}
