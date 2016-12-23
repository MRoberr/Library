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

	public synchronized List<Entity> getAllUsers() throws RemoteException{
		
		User user = new User();
		String userSelect = user.getSelect();
		SqlHandler sqlHandler = SqlHandler.getInstance();
		return sqlHandler.executeSelect(userSelect, "USER");
		
	}
	
	public void insertUser(User user) {
		
	}

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(UserServiceRmi.RMI_PORT);
			UserService oKonyvtarKiszolgalo = new UserService();
			registry.rebind(UserServiceRmi.RMI_NAME, oKonyvtarKiszolgalo);
			System.out.println("Server online");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
