package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.Publication;

public interface SearchServiceRmi extends Remote {

	public static final String RMI_NAME = "Search";

	public static final int RMI_PORT = 1099;

	public List<Publication> searchPublicationByTitles(String title) throws RemoteException;
	
	public List<Publication> getAllPublications() throws RemoteException;
	
	public List<Publication> searchPublicationByRegexp(String regex) throws RemoteException;

	public List<Publication> searchPublicationByUUID(String uuid) throws RemoteException;
	
	public List<Author> getPublicationAuthors(String uuid) throws RemoteException;
}
