package main.java.edu.msg.library.backend.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.edu.msg.library.backend.model.Entity;
import main.java.edu.msg.library.backend.model.User;

public class SqlHandler {
	private static final String DBURL = "jdbc:mysql://localhost:3306/library";
	private static final String USER = "library_admin";
	private static final String PASSWORD = "library_admin_pass";
	private Connection connection;
	private static SqlHandler instance;

	private SqlHandler() {
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized SqlHandler getInstance() {
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
			case "USER": {
				while (resultSet.next()) {
					User user = new User();
					user.setUUID(resultSet.getString("uuid"));
					user.setName(resultSet.getString("name"));

					resultList.add(user);
				}
				break;
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
}
