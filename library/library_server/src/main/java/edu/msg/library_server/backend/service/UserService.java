package edu.msg.library_server.backend.service;

import java.util.List;

import edu.msg.library_server.backend.model.Entity;
import edu.msg.library_server.backend.model.User;
import edu.msg.library_server.backend.repository.SqlHandler;

public class UserService {

	public synchronized List<Entity> getAllUsers() {
		
		User user = new User();
		String userSelect = user.getSelect();
		SqlHandler sqlHandler = SqlHandler.getInstance();
		return sqlHandler.executeSelect(userSelect, "USER");
		
	}
	
	public void insertUser(User user) {
		
		
	}
}
