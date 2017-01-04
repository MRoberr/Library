/*
 * not used anymore
 * use anything from here if you need it
 * 
 */

package edu.msg.library_client.desktop;

import java.io.Console;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import edu.msg.library_client.desktop.jfxgui.model.ConnectionModel;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public class ClientService {

	private static String username;
	private static String password;
	private static LoginAccess loginAccess;
	private static UserServiceRmi uRmi;
	private static SearchServiceRmi searchRmi;
	private Scanner scanner;

	public ClientService() {
		try {
			scanner = new Scanner(System.in);
			Registry registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
			try {
				uRmi = (UserServiceRmi) registry.lookup(UserServiceRmi.RMI_NAME);
				
				searchRmi=(SearchServiceRmi)registry.lookup(SearchServiceRmi.RMI_NAME);
			} catch (NotBoundException e) {

				e.printStackTrace();
			}
		} catch (RemoteException e) {

			e.printStackTrace();
		}
	}

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

	// public static void initClient() {
	//
	//// LoginCheck();
	//
	// try {
	// Registry registry = LocateRegistry.getRegistry("localhost",
	// LoginServiceRmi.RMI_PORT);
	//
	// LoginServiceRmi lRmi = (LoginServiceRmi)
	// registry.lookup(LoginServiceRmi.RMI_NAME);
	// UserServiceRmi uRmi = (UserServiceRmi)
	// registry.lookup(UserServiceRmi.RMI_NAME);
	// bRmi = (BookServiceRmi) registry.lookup(BookServiceRmi.RMI_NAME);
	//
	// loginAccess = lRmi.login("Zoli", "1234");
	// System.out.println("itt");
	// System.out.println(loginAccess);
	//
	// if (loginAccess == LoginAccess.ADMIN) {
	// uRmi.getAllUsers().forEach(u -> System.out.println(((User)
	// u).getName()));
	// } else if (loginAccess == LoginAccess.USER) {
	//
	// } else if (loginAccess == LoginAccess.DENIED) {
	// Console console = System.console();
	// console.printf("!!!!! Please enter your name and password again: ");
	// initClient();
	// } else {
	// System.out.println("Error");
	// System.exit(0);
	// }
	// bRmi.getAllBooks();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	
	
	public void newClientCreate(String name,LoginAccess user_type,int index, String password) {
		User user = new User();
		user.setName(name);
		
		user.setUserType(user_type);
		user.setLoyalityIndex(index);
		user.setPassword(password);

		try {
			uRmi.insertUser(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void clientUpdate(String selectedUser, String newUserName) {
		try {

			List<User> userList = getAllUsers();
			for (Entity entity : userList) {
				User user = (User) entity;
				if (user.getName().equals(selectedUser)) {
					user.setName(newUserName);
					uRmi.updateUser(user);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void clientDelete(String selectedUser) {
		try {

			List<User> userList = getAllUsers();
			for (Entity entity : userList) {
				User user = (User) entity;
				if (user.getName().equals(selectedUser)) {

					uRmi.deleteUser(user);
				}
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		try {
			return uRmi.getAllUsers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void searchClient(String selectedUser) {

		try {
			List<User> users=uRmi.searchUser(selectedUser);
			users.forEach(u->System.out.println(u.toString()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void searchPublication(String title) {
		try {
			List<Publication> pubs=searchRmi.searchPublicationByRegexp(title);
			for(Publication p:pubs){
				System.out.println(p.toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
