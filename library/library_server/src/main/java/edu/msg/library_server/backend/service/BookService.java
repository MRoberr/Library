package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

/**
 * @author simoz
 *
 */
public class BookService extends UnicastRemoteObject implements BookServiceRmi{

	private static final long serialVersionUID = 1L;
	private String bookTemp;
	private Book bookSQL;
	private boolean ret;

	protected BookService() throws RemoteException {
		super();
		bookSQL = new Book();
	}

	@Override
	public synchronized List<Book> getAllBooks() throws RemoteException {		
		bookTemp = bookSQL.getSelectAll();
		return SqlHandler.getInstance().executeBookSelect(bookTemp);
	}

	@Override
	public synchronized boolean insertBook(Book book) throws RemoteException {
		bookTemp = book.getInsert();
		ret = SqlHandler.getInstance().executeSqlStatement(bookTemp);
		bookTemp = book.insertAuthors();	
		return ret && SqlHandler.getInstance().executeSqlStatement(bookTemp);
	}

	@Override
	public synchronized boolean updateBook(Book book) throws RemoteException {
		bookTemp = book.getUpdate();
		return SqlHandler.getInstance().executeSqlStatement(bookTemp);
	}

	@Override
	public synchronized boolean deleteBook(Book book) throws RemoteException {
		bookTemp = book.getDelete();
		ret = SqlHandler.getInstance().executeSqlStatement(bookTemp);
		bookTemp = book.deleteAuthors();	
		return ret && SqlHandler.getInstance().executeSqlStatement(bookTemp);
	}

	@Override
	public synchronized Entity getBookByUUID(String uuid) throws RemoteException {
		bookTemp = bookSQL.getSelectByUUID(uuid);		
		return SqlHandler.getInstance().executeSingleSelect(bookTemp, "BOOK");
	}
}
