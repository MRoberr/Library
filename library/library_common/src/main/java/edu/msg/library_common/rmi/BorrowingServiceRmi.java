package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;

public interface BorrowingServiceRmi extends Remote {
	
	public static final String RMI_NAME = "Borrowing";

	public static final int RMI_PORT = 1099;
	
	/**
	 * 
	 * @return list of Entities containing Borrow objects
	 * @throws RemoteException
	 */
	
	public List<Entity> getAllBorrows() throws RemoteException;
	
	/**
	 * 
	 * @param borrow - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean insertPublication(Borrowing borrow) throws RemoteException;
	
	/**
	 * 
	 * @param borrow - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean updatePublication(Borrowing borrow) throws RemoteException;
	
	/**
	 * 
	 * @param borrow - Borrowing object
	 * @return true if the delete was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean deleteBook(Borrowing borrow) throws RemoteException;
	
	/**
	 * 
	 * @param uuid - the unique identifier of the given borrow
	 * @return returns a Borrow object
	 * @throws RemoteException
	 */
	public Entity getBorrowByUUID(String uuid) throws RemoteException; 
}
