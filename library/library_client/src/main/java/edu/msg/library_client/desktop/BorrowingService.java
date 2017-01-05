package edu.msg.library_client.desktop;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;

public class BorrowingService {

	public List<Publication> getAllPublications() throws RemoteException {
		return RmiRegistry.searchRmi.getAllPublications();
	}

	public boolean borrow(Borrowing borrow) throws RemoteException {
		return RmiRegistry.borrowServiceRmi.borrowPublication(borrow);
	}

	public boolean getBackPublication(User user) {
		return false;
	}

	public List<Publication> getBackBorrowingList(User user) {
		List<Publication> listId = new ArrayList<>();
		Publication tmpPub;
		try {
			List<Entity> listEntity = RmiRegistry.borrowServiceRmi.getAllBorrows();

			for (int i = 0; i < listEntity.size(); i++) {
				Borrowing borrowtemp = (Borrowing) listEntity.get(i);
				if (borrowtemp.getUserUuid().equals(user.getUUID())) {

					tmpPub = RmiRegistry.searchRmi.searchPublicationByUUID(borrowtemp.getPublicationUuid()).get(0);
					listId.add(tmpPub);
				}
			}
			return listId;

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return listId;
	}

	public boolean returnBookInLibrary(User user, Publication publication) {
		// List<Borrowing> listId= new ArrayList<>();
		Borrowing borrowingToReturn = new Borrowing();
		try {
			List<Entity> listEntity = RmiRegistry.borrowServiceRmi.getAllBorrows();
			for (int i = 0; i < listEntity.size(); i++) {
				Borrowing borrowtemp = (Borrowing) listEntity.get(i);
				if (borrowtemp.getUserUuid().equals(user.getUUID())
						&& (borrowtemp.getPublicationUuid().equals(publication.getUUID()))) {
					borrowtemp.setReturnDate(new Date());
					borrowingToReturn = borrowtemp;
				}
			}
			return RmiRegistry.borrowServiceRmi.returnPublication(borrowingToReturn);

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;

	}
}
