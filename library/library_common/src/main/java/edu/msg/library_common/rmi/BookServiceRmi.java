package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Entity;

public interface BookServiceRmi extends Remote {

	public static final String RMI_NAME = "Book";

	public static final int RMI_PORT = 1099;
	
	public List<Entity> getAllBooks() throws RemoteException; 
	
	public boolean insertBook(Book book) throws RemoteException;
	
	public boolean updateBook(Book book) throws RemoteException;
	
	public boolean deleteBook(Book book) throws RemoteException;
	
	public Entity getBookByUUID(String uuid) throws RemoteException; 

}