package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class SearchService extends UnicastRemoteObject implements SearchServiceRmi{

	private static final long serialVersionUID = 1L;
	private String publicationTemp;
	private Publication publicationSQL;

	protected SearchService() throws RemoteException {
		super();
		publicationSQL = new Publication();
	}

	@Override
	public List<Publication> searchPublicationByTitles(String title) {		
		publicationSQL.setTitle(title);
		publicationTemp = publicationSQL.getSelectByTitle();
		return SqlHandler.getInstance().executePublicationSelect(publicationTemp);		
	}
}
