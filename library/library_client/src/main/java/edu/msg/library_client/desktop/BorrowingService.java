package edu.msg.library_client.desktop;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.rmi.BorrowingServiceRmi;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;

public class BorrowingService {

	private SearchServiceRmi searchServiceRmi;
	private BorrowingServiceRmi borrowServiceRmi;
	
	public BorrowingService(){
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
			searchServiceRmi= (SearchServiceRmi) registry.lookup(SearchServiceRmi.RMI_NAME);
			borrowServiceRmi= (BorrowingServiceRmi) registry.lookup(BorrowingServiceRmi.RMI_NAME);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	
	}
	
	public List<Publication> getAllPublications() throws RemoteException{
		return searchServiceRmi.getAllPublications();
	}
	
	public boolean borrow(Borrowing borrow) throws RemoteException{
		return borrowServiceRmi.borrowPublication(borrow);
	}
	
}


