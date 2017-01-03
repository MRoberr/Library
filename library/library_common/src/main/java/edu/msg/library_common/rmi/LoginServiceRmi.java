package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import edu.msg.library_common.model.LoginAccess;

/**
 * @author nagyz
 * @throws RemoteException
 *
 */
public interface LoginServiceRmi extends Remote {
	public static final String RMI_NAME = "Login";

	public static final int RMI_PORT = 1099;

	/**
	 * @param user
	 * @param password
	 * @return list of enum containing user type: User, Admin or Denied ;
	 * @throws RemoteException
	 */
	public LoginAccess login(String user, String password) throws RemoteException;

	/**
	 * @return null
	 * @throws RemoteException
	 */
	public String logout() throws RemoteException;

}
