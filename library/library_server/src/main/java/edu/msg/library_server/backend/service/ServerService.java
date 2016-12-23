package edu.msg.library_server.backend.service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.library_common.rmi.UserServiceRmi;

public class ServerService {

	public static void initServer(){
		try {
			Registry registry = LocateRegistry.createRegistry(UserServiceRmi.RMI_PORT);
			UserService uService = new UserService();
			registry.rebind(UserServiceRmi.RMI_NAME, uService);
			BookService bService = new BookService();
			registry.rebind(BookService.RMI_NAME, bService);

			System.out.println("Server online");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
