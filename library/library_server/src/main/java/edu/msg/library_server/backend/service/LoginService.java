
package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class LoginService extends UnicastRemoteObject implements LoginServiceRmi {

	private static final long serialVersionUID = 1L;

	protected LoginService() throws RemoteException {
		super();
	}

	@Override
	public LoginAccess login(String user, String password) throws RemoteException, SQLException {
		return SqlHandler.getInstance().executeLoginSelect(user, password);
		//return LoginAccess.ADMIN;
	}

	public synchronized String logout() throws RemoteException {
		// return SqlHandler.getInstance().executeLogOut();
		return null;
	}

}
