package edu.msg.library_client.desktop;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import edu.msg.library_client.desktop.GUI.control.MainController;
import edu.msg.library_client.desktop.console.MainConsole;

public class ClientMain {	
	
	public static void main(String[] args) throws NotBoundException {				
		MainConsole console = new MainConsole();
		console.startConsole();
	
	}
}