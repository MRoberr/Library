package edu.msg.library_server.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.msg.library_server.backend.service.ServerService;

public class ServerMain {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerMain.class);

	public static void main(String[] args) {	
		
		LOGGER.error("Server start LOG!");
		ServerService.initServer();
	}
}
