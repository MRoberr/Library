package edu.msg.library_client.desktop;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.BorrowingServiceRmi;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.MagazineServiceRmi;
import edu.msg.library_common.rmi.NewspaperServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public class RmiRegistry {
	
	public static LoginAccess loginAccess;
	public static UserServiceRmi userRmi;
	public static SearchServiceRmi searchRmi;
	public static BookServiceRmi bookServiceRmi;
	public static MagazineServiceRmi magazineServiceRmi;
	public static NewspaperServiceRmi newspaperServiceRmi;
	public static BorrowingServiceRmi borrowServiceRmi;

	static{
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
			userRmi = (UserServiceRmi) registry.lookup(UserServiceRmi.RMI_NAME);			
			searchRmi=(SearchServiceRmi)registry.lookup(SearchServiceRmi.RMI_NAME);
			bookServiceRmi = (BookServiceRmi) registry.lookup(BookServiceRmi.RMI_NAME);
			magazineServiceRmi = (MagazineServiceRmi) registry.lookup(MagazineServiceRmi.RMI_NAME);
			newspaperServiceRmi = (NewspaperServiceRmi) registry.lookup(NewspaperServiceRmi.RMI_NAME);
			borrowServiceRmi = (BorrowingServiceRmi) registry.lookup(BorrowingServiceRmi.RMI_NAME);
		
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}