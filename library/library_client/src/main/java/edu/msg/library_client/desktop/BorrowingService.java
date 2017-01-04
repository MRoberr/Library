package edu.msg.library_client.desktop;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;
import edu.msg.library_common.rmi.BorrowingServiceRmi;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public class BorrowingService {

	private SearchServiceRmi searchServiceRmi;
	private BorrowingServiceRmi borrowServiceRmi;

	public BorrowingService() {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
			searchServiceRmi = (SearchServiceRmi) registry.lookup(SearchServiceRmi.RMI_NAME);
			borrowServiceRmi = (BorrowingServiceRmi) registry.lookup(BorrowingServiceRmi.RMI_NAME);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

	}

	public List<Publication> getAllPublications() throws RemoteException {
		return searchServiceRmi.getAllPublications();
	}

	public boolean getBackPublication(User user) {
		return false;
	}

	public List<Publication> getBackBorrowingList(User user) {
		List<Publication> listId= new ArrayList<>();
		Publication tmpPub;
		try {
			List<Entity> listEntity= borrowServiceRmi.getAllBorrows();
			
			for (int i = 0; i < listEntity.size(); i++) {
				Borrowing borrowtemp=(Borrowing)listEntity.get(i);
				if (borrowtemp.getUserUuid().equals(user.getUUID())){
					
							tmpPub=searchServiceRmi.searchPublicationByUUID(borrowtemp.getPublicationUuid()).get(0);
							listId.add(tmpPub);
				}
			}
			return listId;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listId;
	}
}
