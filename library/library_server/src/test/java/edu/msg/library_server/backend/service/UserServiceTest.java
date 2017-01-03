package edu.msg.library_server.backend.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.User;
import edu.msg.library_server.backend.repository.SqlHandler;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
  User user;
  UserService userService;
  
  public UserServiceTest() {
	  user=new User();
	  user.setName("zolika666");
	  user.setUUID(user.getUUID());
	  user.setPassword("sddsdssd");
	  user.setLoyalityIndex(10);
	  user.setUserType(LoginAccess.ADMIN);
	  
	  try {
			userService = new UserService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
}
  
  @Test
	public void init1User() {
		assertNotNull(user);
		assertNotNull(userService);
	}
  
  @Test
	public void test2InsertUser() {
		try {
			assertTrue(userService.insertUser(user));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}
  
//  @Test
//	public void test3UpdateUser() {
//		try { 
//			user.setName("badabumm");
//			
//			asserttr(userService.updateUser(user));
//		} catch (RemoteException e) {
//			fail(e.getMessage());
//		}
//	}

 
}
