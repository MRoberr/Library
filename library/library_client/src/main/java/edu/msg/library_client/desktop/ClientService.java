/*
 * not used anymore
 * use anything from here if you need it
 * 
 */






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
		console.printf("Please enter your password: ");
		char passwordArray[] = console.readPassword("");
		password = new String(passwordArray);
	}

	public static void initClient() {
	
//		LoginCheck();
		
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
			
			LoginServiceRmi lRmi = (LoginServiceRmi) registry.lookup(LoginServiceRmi.RMI_NAME);
			UserServiceRmi uRmi = (UserServiceRmi) registry.lookup(UserServiceRmi.RMI_NAME);
			BookServiceRmi bRmi = (BookServiceRmi) registry.lookup(BookServiceRmi.RMI_NAME);
			
			loginAccess = lRmi.login("Zoli", "1234");
			System.out.println("itt");
//			System.out.println(loginAccess);
//			
//			if (loginAccess == LoginAccess.ADMIN) {
//				uRmi.getAllUsers().forEach(u -> System.out.println(((User) u).getName()));
//			} else if (loginAccess == LoginAccess.USER) {
//
//			} else if (loginAccess == LoginAccess.DENIED) {
//				Console console = System.console();
//				console.printf("!!!!! Please enter your name and password again: ");
//				initClient();
//			} else {
//				System.out.println("Error");
//				System.exit(0);
//			}
			// bRmi.getAllBooks();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void menuforAdmin() {
		Console console = System.console();
		console.printf("Type");
		console.printf("1-Kiadvany utani kereses");
		console.printf("2-Uj felhasznalo letrehozasa");
		console.printf("3-Felhasznalo adatainak modositasa");
		console.printf("4-Felhasznalo torlese");
		console.printf("5-Felhasznalo utani kereses");
		console.printf("6-Uj kiadvany letrehozasa");
		console.printf("7-Meglevo kiadvany adatainak modositasa");
		console.printf("8-Meglevo kiadvany torlese");
		console.printf("9-Kiadvany kolcsonzes");
		console.printf("10-Kiadvany visszavetele");
	
	}
	public static void menuforUser() {
		Console console = System.console();
		console.printf("Type");
		console.printf("1-Kiadvany utani kereses");
	}
}
