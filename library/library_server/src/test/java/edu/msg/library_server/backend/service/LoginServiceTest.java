package edu.msg.library_server.backend.service;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.junit.Test;

import edu.msg.library_common.model.LoginAccess;

/**
 * @author nagyz
 *
 */
public class LoginServiceTest {
	private LoginService loginservice;

	/**
	 * Tests if the user who try's to connect is a User enum
	 * At the moment is testing in local database, any time is used from a different computer the test field it need's to be changed
	 */
	@Test
	public void testLogin() {
		try {
			loginservice = new LoginService();
			try {
				LoginAccess loginAcces = loginservice.login("user", "user");
				assertEquals(loginAcces, LoginAccess.USER);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} catch (SQLException | RemoteException e) {
			e.printStackTrace();
		}
	}
}
