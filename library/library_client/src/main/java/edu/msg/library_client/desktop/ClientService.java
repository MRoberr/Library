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

	public boolean newClientCreate(String name,LoginAccess user_type,int index, String password) {
		User user = new User();
		user.setName(name);
		
		user.setUserType(user_type);
		user.setLoyalityIndex(index);
		user.setPassword(password);
		try {
			return RmiRegistry.userRmi.insertUser(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clientUpdate(String selectedUser, String newUserName) {
		try {

			List<User> userList = getAllUsers();
			for (Entity entity : userList) {
				User user = (User) entity;
				if (user.getName().equals(selectedUser)) {
					user.setName(newUserName);
					return RmiRegistry.userRmi.updateUser(user);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean clientDelete(String selectedUser) {
		try {

			List<User> userList = getAllUsers();
			for (Entity entity : userList) {
				User user = (User) entity;
				if (user.getName().equals(selectedUser)) {

					return RmiRegistry.userRmi.deleteUser(user);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
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
			System.out.println("Can't find client!");
		}
	}
}
