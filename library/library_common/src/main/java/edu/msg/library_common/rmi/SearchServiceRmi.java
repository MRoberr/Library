package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Entity;

public interface SearchServiceRmi extends Remote {

	public static final String RMI_NAME = "Search";

	public static final int RMI_PORT = 1099;

	// Entity = book, news, magazines
	public List<Entity> searchPublicationByTitles(String title) throws RemoteException;
}
