package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class SearchService extends UnicastRemoteObject implements SearchServiceRmi {

	private static final long serialVersionUID = 1L;

	protected SearchService() throws RemoteException {
		super();
	}

	@Override
	public List<Publication> searchPublicationByTitles(String title) {
		return SqlHandler.getInstance().executePublicationSelect(Publication.getSelectByTitle(title));
	}

	@Override
	public List<Publication> searchPublicationByUUID(String uuid) {
		return SqlHandler.getInstance().executePublicationSelect(Publication.getSelectByPublicationUUID(uuid));
	}

	@Override
	public List<Publication> searchPublicationByRegexp(String regexp) throws RemoteException {
		String reg = regexp.replace("+", "([a-zA-Z0-9 ]+)");
		reg = reg.replace("*", "([a-zA-Z0-9 ]*)");
		return SqlHandler.getInstance().executePublicationSelect(Publication.getSelectByRegexp(reg));
	}

	@Override
	public List<Publication> getAllPublications() throws RemoteException {
		return SqlHandler.getInstance().executePublicationSelect(Publication.getSelectAllPublications());
	}

	@Override
	public List<Author> getPublicationAuthors(String uuid) throws RemoteException {
		return SqlHandler.getInstance().executeAuthorSelect(Publication.getPublicationAuthors(uuid));
	}
}
