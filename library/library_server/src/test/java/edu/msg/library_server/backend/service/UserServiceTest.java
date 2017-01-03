package edu.msg.library_server.backend.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.User;

public class UserServiceTest {
	User user;
	UserService userService;

	public UserServiceTest() {
		user = new User();
		user.setName("Zoli");
		user.setLoyalityIndex(10);
		user.setPassword("1234");
		user.setUserType(LoginAccess.ADMIN);
		try {
			userService = new UserService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testUser() {

		assertNotNull(user);
		assertNotNull(userService);

		try {
			assertTrue(userService.insertUser(user));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
		user.setName("badadum");
		try {
			assertTrue(userService.updateUser(user));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}

		try {
			
			User b = (User) (userService.getUserByUUUID(user.getUUID()));
			assertTrue(b.getName().equals(user.getName()));
		} catch (RemoteException e) {

			fail(e.getMessage());
		}
		
		List<User> dbList = new ArrayList<>();
		try {
			dbList = userService.getAllUsers();
		} catch (RemoteException e) {
		
			fail(e.getMessage());
		}
		assertTrue(dbList.size()>0);
		
		try {			
			assertTrue(userService.deleteUser(user));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}

	}

}
