package edu.msg.library_server.backend.service;

import org.junit.Test;

import edu.msg.library_server.backend.model.User;
import edu.msg.library_server.backend.repository.SqlHandler;

public class TestSqlHandler {
	@Test
	public void testSelect() {
		User user = new User();
		String userSelect = user.getSelect();
		SqlHandler sqlHandler = SqlHandler.getInstance();
		sqlHandler.executeSelect(userSelect, "USER").forEach(u -> System.out.println(u));
		// Assert.assertArrayEquals(new ArrayList<>(),
		// sqlHandler.executeSelect(userSelect, "USER"));

	}

}