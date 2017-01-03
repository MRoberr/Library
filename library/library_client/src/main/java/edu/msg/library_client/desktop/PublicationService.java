package edu.msg.library_client.desktop;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import edu.msg.library_common.model.Publication;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public class PublicationService {
	
	private static String title;
	private Scanner scanner;
	private SearchServiceRmi searchServiceRmi;
	
	public PublicationService() {
		try {
			scanner = new Scanner(System.in);
			Registry registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
			try {
				searchServiceRmi = (SearchServiceRmi) registry.lookup(SearchServiceRmi.RMI_NAME);
			} catch (NotBoundException e) {

				e.printStackTrace();
			}
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		
	}
	
	public void serachBook(){
		
	}
	

}
