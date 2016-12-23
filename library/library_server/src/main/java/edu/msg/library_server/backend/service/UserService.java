package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.management.remote.rmi.RMIIIOPServerImpl;

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
		String userSelect = user.getSelectAll();
		SqlHandler sqlHandler = SqlHandler.getInstance();
		return sqlHandler.executeSelect(userSelect, "USER");

	}

	public synchronized boolean insertUser(User user) throws RemoteException {
		//to do
		return false;
	}

	@Override
	public synchronized boolean updateUser(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean deleteUser(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Entity> getUserByName(String Name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
