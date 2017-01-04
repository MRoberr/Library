package edu.msg.library_client.desktop;

import java.rmi.NotBoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientMain {	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientMain.class);
	
	public static void main(String[] args) throws NotBoundException {				
		
		LOGGER.error("Client start LOG!");
		UiFactory.getUi();		
	
	}
}