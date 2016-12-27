package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class BookService extends UnicastRemoteObject implements BookServiceRmi{

	private static final long serialVersionUID = 1L;
	private String bookTemp;
	private Book bookSQL;

	protected BookService() throws RemoteException {
		super();
		bookSQL = new Book();
	}

	@Override
	public synchronized List<Entity> getAllBooks() throws RemoteException {		
		bookTemp = bookSQL.getSelectAll();
		return SqlHandler.getInstance().executeSelect(bookTemp, "BOOK");
	}

	@Override
	public synchronized boolean insertBook(Book book) throws RemoteException {
		bookTemp = book.getInsert();		
		return SqlHandler.getInstance().executeInsert(bookTemp, "BOOK");
	}

	@Override
	public synchronized boolean updateBook(Book book) throws RemoteException {
		bookTemp = book.getUpdate();
		return SqlHandler.getInstance().executeUpdate(bookTemp, "BOOK");
	}

	@Override
	public synchronized boolean deleteBook(Book book) throws RemoteException {
		bookTemp = book.getDelete(); 
		return SqlHandler.getInstance().executeDelete(bookTemp, "BOOK");
	}

	@Override
	public synchronized Entity getBookByUUID(String uuid) throws RemoteException {
		bookTemp = bookSQL.getSelectByUUID(uuid);		
		return SqlHandler.getInstance().executeSingleSelect(bookTemp, "BOOK");
	}
}
