package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;

/**
 * The interface represents the borrowing of a publication by a reader(library user)
 * @author gallb
 * 
 */
public interface BorrowingServiceRmi extends Remote {
	
	public static final String RMI_NAME = "Borrowing";

	public static final int RMI_PORT = 1099;
	
	/**
	 * get all borrow objects from persistency layer
	 * @return list of Entities containing Borrow objects
	 * @throws RemoteException
	 */
	
	public List<Entity> getAllBorrows() throws RemoteException;
	
	/**
	 * inserts a borrowing object into persistency layer
	 * @param borrow - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean insertBorrowing(Borrowing borrow) throws RemoteException;
	
	/**
	 * update one borrowing objects values in the persistency layer
	 * @param borrow - Borrowing object
	 * @return true if the insert was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean updateBorrowing(Borrowing borrow) throws RemoteException;
	
	/**
	 * delete borrow object from persistency layer
	 * @param borrow - Borrowing object
	 * @return true if the delete was successful, or false if not
	 * @throws RemoteException
	 */
	public boolean deleteBorrow(Borrowing borrow) throws RemoteException;
	
	/**
	 * returns one borrow entity by its UUID
	 * @param uuid - the unique identifier of the given borrow
	 * @return returns a Borrow object
	 * @throws RemoteException
	 */
	public Entity getBorrowByUUID(String uuid) throws RemoteException; 
	
	/**
	 * Changes the return date of the borrow object in the persistency layer. 
	 * Updates the number of copies field of the publication in th epersistency layer
	 * Updates if the users trust index must be changed and updates it if necesarry
	 * @param borrow - Borrowing object
	 * @return true if publication has been returned correctly, false if not
	 * @throws RemoteException
	 */
	public boolean returnPublication (Borrowing borrow) throws RemoteException;
	
}
