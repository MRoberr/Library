
package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class LoginService extends UnicastRemoteObject implements LoginServiceRmi {

	private static final long serialVersionUID = 1L;

	/**
	 * @throws RemoteException 
	 */
	protected LoginService() throws RemoteException {
		super();
	}

	@Override
	public LoginAccess login(String user, String password) throws RemoteException, SQLException {
		return SqlHandler.getInstance().executeLoginSelect(user, password);
	}

	@Override
	public synchronized String logout() throws RemoteException {
		return null;
	}

}
