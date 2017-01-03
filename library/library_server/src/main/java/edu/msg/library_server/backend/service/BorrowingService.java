package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Newspaper;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;
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
	public boolean insertBorrowing(Borrowing borrow) throws RemoteException {
		sqlStatement = borrowTemp.getInsert();
		return SqlHandler.getInstance().executeSqlStatement(sqlStatement);
	}

	@Override
	public boolean updateBorrowing(Borrowing borrow) throws RemoteException {
		sqlStatement = borrowTemp.getUpdate();
		return SqlHandler.getInstance().executeSqlStatement(sqlStatement);
	}

	@Override
	public boolean deleteBorrow(Borrowing borrow) throws RemoteException {
		sqlStatement = borrowTemp.getDelete();
		return SqlHandler.getInstance().executeSqlStatement(sqlStatement);
	}

	@Override
	public Entity getBorrowByUUID(String uuid) throws RemoteException {
		sqlStatement = borrowTemp.getSelectByUUID(uuid);
		return SqlHandler.getInstance().executeSingleSelect(sqlStatement, "BORROW");
	}

	@Override
	public boolean returnPublication(Borrowing borrow) throws RemoteException {
		try {
			Date today = new Date();
			borrow.setReturnDate(today);
			if (borrow.getReturnDate().after(borrow.getDeadline())) {
				UserService us = new UserService();
				User user = (User)us.getUserByUUUID(borrow.getUserUuid());
				user.setLoyalityIndex(user.getLoyalityIndex() - 1);
				us.updateUser(user);
			}
			SearchService ss = new SearchService();
			List<Publication> publicationList = ss.searchPublicationByUUID(borrow.getPublicationUuid());
			if (publicationList.isEmpty()) {
				return false;
			} else {
				int type = publicationList.get(0).getType();
				switch (type) {
				case 1:
					BookService bs = new BookService();
					Book book = (Book)bs.getBookByUUID(borrow.getPublicationUuid());
					book.setCopiesLeft(book.getCopiesLeft() + 1);
					bs.updateBook(book);
					break;
				case 2:
					NewspaperService ns = new NewspaperService();
					Newspaper paper = (Newspaper)ns.getNewspaperByUUID(borrow.getPublicationUuid());
					paper.setCopiesLeft(paper.getCopiesLeft() + 1);
					ns.updateNewspaper(paper);
					break;
				case 3:
					MagazineService ms = new MagazineService();
					Magazine mag = (Magazine)ms.getMagazineByUUID(borrow.getPublicationUuid());
					mag.setCopiesLeft(mag.getCopiesLeft() + 1);
					ms.updateMagazine(mag);
					break;
				default:
					break;
				}
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
		
	} 

}