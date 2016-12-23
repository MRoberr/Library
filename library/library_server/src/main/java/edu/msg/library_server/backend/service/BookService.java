package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public class BookService extends UnicastRemoteObject implements BookServiceRmi{

	protected BookService() throws RemoteException {
		super();
	}

	@Override
	public List<Entity> getAllBooks() throws RemoteException {
		System.out.println("getBooks");
		return null;
	}

}
