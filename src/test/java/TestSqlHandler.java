package test.java;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import main.java.edu.msg.library.backend.model.User;
import main.java.edu.msg.library.backend.repository.SqlHandler;

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
