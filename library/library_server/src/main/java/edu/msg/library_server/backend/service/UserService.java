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

	public synchronized List<Entity> getAllUsers() throws RemoteException {
		User user = new User();
		return SqlHandler.getInstance().executeSelect(user.getSelectAll(), "USER");

	}

	public synchronized boolean insertUser(User user) throws RemoteException {

		return SqlHandler.getInstance().executeInsert(user.getInsert());
	}

	@Override
	public synchronized boolean updateUser(User user) throws RemoteException {

		return SqlHandler.getInstance().executeUpdate(user.getUpdate());
	}

	@Override
	public boolean deleteUser(User user) throws RemoteException {
		return SqlHandler.getInstance().executeDelete(user.getDelete());
	}

	@Override
	public Entity getUserByName(String name) throws RemoteException {
		User user = new User();
		return SqlHandler.getInstance().executeSingleSelect(user.getSelectByName(name), "USER");
		
	}

}
