package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Entity;


public interface UserServiceRmi extends Remote {

	public static final String RMI_NAME = "Library";

	public static final int RMI_PORT = 1099;
	
	public List<Entity> getAllUsers() throws RemoteException; //sync
	
//
//	public Session belepes(String p_felhasznaloi_nev) throws RemoteException;
//
//	public void kilepes(Session p_session) throws RemoteException;

}