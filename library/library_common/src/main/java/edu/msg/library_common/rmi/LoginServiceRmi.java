package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import edu.msg.library_common.model.Entity;

public interface LoginServiceRmi extends Remote{
	public static final String RMI_NAME = "Login";

	public static final int RMI_PORT = 1099;
	
	public String login(String user, String password) throws RemoteException;
		
	
//	public synchronized String logout() throws RemoteException {
//		
//	}

}
