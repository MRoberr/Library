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
}
