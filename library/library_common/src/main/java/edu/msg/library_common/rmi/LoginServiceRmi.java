package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import edu.msg.library_common.model.LoginAccess;

public interface LoginServiceRmi extends Remote{
	public static final String RMI_NAME = "Login";

	public static final int RMI_PORT = 1099;
	
	public LoginAccess login(String user, String password) throws RemoteException;		
	
//	public synchronized String logout() throws RemoteException {
//		
//	}

}
