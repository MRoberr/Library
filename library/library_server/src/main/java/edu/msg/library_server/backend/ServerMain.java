package edu.msg.library_server.backend;

import edu.msg.library_common.model.LoginAcces;
import edu.msg.library_server.backend.service.ServerService;

public class ServerMain {

	public static void main(String[] args) {
		System.out.println(LoginAcces.ADMIN.toString());
		ServerService.initServer();
	}
}
