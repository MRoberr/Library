package edu.msg.library_client.desktop;

import java.io.Console;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.User;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public class ClientService {

	private static String username;
	private static String password;	
	private static LoginAccess loginAccess;
	
	public static void LoginCheck() {		
		Console console = System.console();
	    if (console == null) {
	        System.out.println("Couldn't get Console instance");
	        System.exit(0);
	    }
	    
	    console.printf("Please enter your username: ");
        username = console.readLine();
        //console.printf(username + "\n");

        console.printf("Please enter your password: ");
	    char passwordArray[] = console.readPassword("Enter your secret password: ");
	    password = new String(passwordArray);
	}
	
	public static void initClient(){
		//LoginCheck();
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
			LoginServiceRmi lRmi = (LoginServiceRmi) registry.lookup(LoginServiceRmi.RMI_NAME);
			UserServiceRmi uRmi = (UserServiceRmi) registry.lookup(UserServiceRmi.RMI_NAME);			
			BookServiceRmi bRmi = (BookServiceRmi) registry.lookup(BookServiceRmi.RMI_NAME);
		//	loginAccess = lRmi.login(username, password);
			
			if (loginAccess == LoginAccess.ADMIN) {
				uRmi.getAllUsers().forEach(u -> System.out.println(((User) u).getName()));				
			} else if (loginAccess == LoginAccess.USER) {
				
			} else if (loginAccess == LoginAccess.DENIED) {
				
			} else {
				System.out.println("Error");
				System.exit(0);
			}					
			
			//bRmi.getAllBooks();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
