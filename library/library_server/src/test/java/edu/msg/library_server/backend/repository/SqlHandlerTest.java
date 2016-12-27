package edu.msg.library_server.backend.repository;

import org.junit.Test;

import edu.msg.library_common.model.User;
import edu.msg.library_server.backend.repository.SqlHandler;

public class SqlHandlerTest {
	@Test
	public void testSelect() {
		User user = new User();
		String userSelect = user.getSelectAll();
		SqlHandler sqlHandler = SqlHandler.getInstance();
		sqlHandler.executeSelect(userSelect, "USER").forEach(u -> System.out.println(u));
		// Assert.assertArrayEquals(new ArrayList<>(),
		// sqlHandler.executeSelect(userSelect, "USER"));

	}

}