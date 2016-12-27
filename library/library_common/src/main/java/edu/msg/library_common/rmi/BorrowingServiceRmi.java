package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import edu.msg.library_common.model.Entity;

public interface BorrowingServiceRmi extends Remote {
	
	public static final String RMI_NAME = "Borrowing";

	public static final int RMI_PORT = 1099;
	
//	public List<Entity> getAllPublications() throws RemoteException; 
	
	/**
	 * 
	 * @param ent - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean insertPublication(Entity ent) throws RemoteException;
	
	/**
	 * 
	 * @param ent - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean updatePublication(Entity ent) throws RemoteException;
}
