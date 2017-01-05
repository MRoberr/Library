/*
 * not used anymore
 * use anything from here if you need it
 * 
 */

package edu.msg.library_client.desktop;

import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.User;

public class ClientService {

	public void newClientCreate(String name,LoginAccess user_type,int index, String password) {
		User user = new User();
		user.setName(name);
		
		user.setUserType(user_type);
		user.setLoyalityIndex(index);
		user.setPassword(password);
		try {
			RmiRegistry.userRmi.insertUser(user);
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
					RmiRegistry.userRmi.updateUser(user);
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

					RmiRegistry.userRmi.deleteUser(user);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		try {
			return RmiRegistry.userRmi.getAllUsers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void searchClient(String selectedUser) {

		try {
			List<User> users=RmiRegistry.userRmi.searchUser(selectedUser);
			users.forEach(u->System.out.println(u.toString()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
