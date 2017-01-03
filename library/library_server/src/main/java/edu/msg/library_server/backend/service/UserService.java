package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.User;
import edu.msg.library_common.rmi.UserServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class UserService extends UnicastRemoteObject implements UserServiceRmi {

	private static final long serialVersionUID = 1L;

	protected UserService() throws RemoteException {
		super();

	}

	public synchronized List<User> getAllUsers() throws RemoteException {
		User user = new User();
//		return SqlHandler.getInstance().executeSelect(user.getSelectAll(), "USER");
		return SqlHandler.getInstance().executeUserSelect(user.getSelectAll());

	}

	public synchronized boolean insertUser(User user) throws RemoteException {

		return SqlHandler.getInstance().executeSqlStatement(user.getInsert());
	}

	@Override
	public synchronized boolean updateUser(User user) throws RemoteException {

		return SqlHandler.getInstance().executeSqlStatement(user.getUpdate());
	}

	@Override
	public synchronized boolean deleteUser(User user) throws RemoteException {
		return SqlHandler.getInstance().executeSqlStatement(user.getDelete());
	}

	@Override
	public synchronized Entity getUserByName(String name) throws RemoteException {
		User user = new User();
		return SqlHandler.getInstance().executeSingleSelect(user.getSelectByName(name), "USER");
		
	}

	@Override
	public synchronized Entity getUserByUUUID(String uuid) throws RemoteException {
		User user = new User();
		return SqlHandler.getInstance().executeSingleSelect(user.getSelectByUUID(uuid), "USER");
	}

	@Override
	public List<Entity> searchUser(String name) {
		return null;
		
	}
	

}
