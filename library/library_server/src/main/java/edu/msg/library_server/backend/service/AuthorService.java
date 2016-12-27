package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.AuthorServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class AuthorService extends UnicastRemoteObject implements AuthorServiceRmi {

	private static final long serialVersionUID = 1L;
	private Author authorSQL;
	private String authorTemp;

	protected AuthorService() throws RemoteException {
		super();
		authorSQL = new Author();
	}

	@Override
	public List<Entity> getAllAuthors() throws RemoteException {
		authorTemp = authorSQL.getSelectAll();
		return SqlHandler.getInstance().executeSelect(authorTemp, "AUTHOR");
	}

	@Override
	public boolean insertAuthor(Author author) throws RemoteException {
		authorTemp = author.getInsert();
		return SqlHandler.getInstance().executeInsert(authorTemp);
	}

	@Override
	public boolean updateAuthor(Author author) throws RemoteException {
		authorTemp = author.getUpdate();
		return SqlHandler.getInstance().executeUpdate(authorTemp);
	}

	@Override
	public boolean deleteAuthor(Author author) throws RemoteException {
		authorTemp = author.getDelete();
		return SqlHandler.getInstance().executeDelete(authorTemp);
	}

	@Override
	public Entity getAuthorByUUID(String uuid) throws RemoteException {
		authorTemp = authorSQL.getSelectByUUID(uuid);
		return SqlHandler.getInstance().executeSingleSelect(authorTemp, "AUTHOR");
	}

}
