package edu.msg.library_client.desktop;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.library_common.model.User;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public class ClientService {

	private String token;
	
	public static void initClient(String[] args){
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", UserServiceRmi.RMI_PORT);
			UserServiceRmi uRmi = (UserServiceRmi) registry.lookup(UserServiceRmi.RMI_NAME);			
			BookServiceRmi bRmi = (BookServiceRmi) registry.lookup(BookServiceRmi.RMI_NAME);
			
			uRmi.getAllUsers().forEach(u -> System.out.println(((User) u).getName()));
			bRmi.getAllBooks();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
