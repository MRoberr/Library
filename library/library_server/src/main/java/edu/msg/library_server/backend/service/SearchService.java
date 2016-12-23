package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;

public class SearchService extends UnicastRemoteObject implements SearchServiceRmi{

	private static final long serialVersionUID = 1L;

	protected SearchService() throws RemoteException {
		super();
	}

	@Override
	public List<Entity> searchPublicationByTitles(String title) {
		//
		return null;
	}
}
