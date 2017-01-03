package edu.msg.library_server.backend.service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.library_common.rmi.AuthorServiceRmi;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.BorrowingServiceRmi;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.MagazineServiceRmi;
import edu.msg.library_common.rmi.NewspaperServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public class ServerService {

	public static void initServer(){
		try {
			Registry registry = LocateRegistry.createRegistry(UserServiceRmi.RMI_PORT);
			
			LoginService lService = new LoginService();
			registry.rebind(LoginServiceRmi.RMI_NAME, lService);
			
			UserService uService = new UserService();
			registry.rebind(UserServiceRmi.RMI_NAME, uService);
			
			BookService bService = new BookService();
			registry.rebind(BookServiceRmi.RMI_NAME, bService);
			
			AuthorService aService = new AuthorService();
			registry.rebind(AuthorServiceRmi.RMI_NAME, aService);
			
			BorrowingService boService = new BorrowingService();
			registry.rebind(BorrowingServiceRmi.RMI_NAME, boService);
			
			MagazineService mService = new MagazineService();
			registry.rebind(MagazineServiceRmi.RMI_NAME, mService);
			
			NewspaperService nService = new NewspaperService();
			registry.rebind(NewspaperServiceRmi.RMI_NAME, nService);
			
			SearchService sService = new SearchService();
			registry.rebind(SearchServiceRmi.RMI_NAME, sService);				

			System.out.println("Server online");
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
}
