package edu.msg.library_server.backend.repository;

import org.junit.Test;

import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.User;
import edu.msg.library_server.backend.repository.SqlHandler;


public class SqlHandlerTest {
	@Test
	public void testSelect() {
		User user = new User();
		String userSelect = user.getSelectAll();
		SqlHandler sqlHandler = SqlHandler.getInstance();
		//sqlHandler.executeSelect(userSelect, "USER").forEach(u -> System.out.println(u));
		// Assert.assertArrayEquals(new ArrayList<>(),
		// sqlHandler.executeSelect(userSelect, "USER"));
		
		User user2 = new User();
		user2.setName("Pistuka");
		user2.setUUID(user2.getUUID());
		user2.setUserType(LoginAccess.ADMIN);
		user2.setLoyalityIndex(2);
		user2.setPassword("almafa12345");
		sqlHandler.executeSqlStatement(user2.getInsert());
		sqlHandler.executeSelect(userSelect, "USER").forEach(u -> System.out.println(u));
		

	}

}