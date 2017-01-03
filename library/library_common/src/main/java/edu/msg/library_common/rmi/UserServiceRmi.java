package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.User;


public interface UserServiceRmi extends Remote {

	public static final String RMI_NAME = "User";

	public static final int RMI_PORT = 1099;
	
	public List<User> getAllUsers() throws RemoteException; 
	
	public boolean insertUser(User user) throws RemoteException;
	
	public boolean updateUser(User user) throws RemoteException;
	
	public boolean deleteUser(User user) throws RemoteException;
	
	public Entity getUserByName(String name) throws RemoteException;
	
	public Entity getUserByUUUID(String uuid) throws RemoteException; 
	
	public List<Entity> searchUser(String name) throws RemoteException;
	
//
//	public Session belepes(String p_felhasznaloi_nev) throws RemoteException;
//
//	public void kilepes(Session p_session) throws RemoteException;

}