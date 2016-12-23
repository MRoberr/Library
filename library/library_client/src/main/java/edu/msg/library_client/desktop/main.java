package edu.msg.library_client.desktop;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.msg.library_common.model.User;
import edu.msg.library_common.rmi.UserServiceRmi;

public class main {

	public static void main(String[] args) {

		try {
			Registry registry = LocateRegistry.getRegistry("localhost", UserServiceRmi.RMI_PORT);
			UserServiceRmi oKonyvtar = (UserServiceRmi) registry.lookup(UserServiceRmi.RMI_NAME);
			oKonyvtar.getAllUsers().forEach(u->System.out.println(((User)u).getName()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
