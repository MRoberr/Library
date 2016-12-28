package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.BorrowingServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class BorrowingService extends UnicastRemoteObject implements BorrowingServiceRmi {

	private static final long serialVersionUID = 1L;

	private String sqlStatement;
	private Borrowing borrowTemp;
	
	protected BorrowingService() throws RemoteException {
		super();
		borrowTemp = new Borrowing();
	}

	@Override
	public List<Entity> getAllBorrows() throws RemoteException {
		sqlStatement = borrowTemp.getSelectAll();
		return SqlHandler.getInstance().executeSelect(sqlStatement, "BOORROW");
	}

	@Override
	public boolean insertPublication(Borrowing borrow) throws RemoteException {
		sqlStatement = borrowTemp.getInsert();
		return SqlHandler.getInstance().executeInsert(sqlStatement);
	}

	@Override
	public boolean updatePublication(Borrowing borrow) throws RemoteException {
		sqlStatement = borrowTemp.getUpdate();
		return SqlHandler.getInstance().executeUpdate(sqlStatement);
	}

	@Override
	public boolean deleteBook(Borrowing borrow) throws RemoteException {
		sqlStatement = borrowTemp.getDelete();
		return SqlHandler.getInstance().executeInsert(sqlStatement);
	}

	@Override
	public Entity getBorrowByUUID(String uuid) throws RemoteException {
		sqlStatement = borrowTemp.getSelectByUUID(uuid);
		return SqlHandler.getInstance().executeSingleSelect(sqlStatement, "BORROW");
	} 

}