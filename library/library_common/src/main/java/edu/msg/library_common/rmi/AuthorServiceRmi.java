package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Entity;

public interface AuthorServiceRmi extends Remote {

	public static final String RMI_NAME = "Author";

	public static final int RMI_PORT = 1099;

	public List<Entity> getAllAuthors() throws RemoteException;

	public boolean insertAuthor(Author author) throws RemoteException;

	public boolean updateAuthor(Author author) throws RemoteException;

	public boolean deleteAuthor(Author author) throws RemoteException;

	public Entity getAuthorByUUID(String uuid) throws RemoteException;

}
