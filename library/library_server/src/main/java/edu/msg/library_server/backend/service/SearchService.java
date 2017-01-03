package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Publication;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class SearchService extends UnicastRemoteObject implements SearchServiceRmi{

	private static final long serialVersionUID = 1L;

	protected SearchService() throws RemoteException {
		super();
	}

	@Override
	public List<Publication> searchPublicationByTitles(String title) {		
		
		return SqlHandler.getInstance().executePublicationSelect(Publication.getSelectByTitle(title));		
	}
	
	
	public List<Publication> searchPublicationByUUID(String uuid) {		
		publicationTemp = publicationSQL.getSelectByUUID(uuid);
		return SqlHandler.getInstance().executePublicationSelect(publicationTemp);		
	}
}
