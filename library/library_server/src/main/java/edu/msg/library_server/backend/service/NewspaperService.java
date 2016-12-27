package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Newspaper;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.NewspaperServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class NewspaperService extends UnicastRemoteObject implements NewspaperServiceRmi{

	private static final long serialVersionUID = 1L;
	private String newspaperTemp;
	private Newspaper newspaperSQL;

	protected NewspaperService() throws RemoteException {
		super();
		newspaperSQL = new Newspaper();
	}

	@Override
	public synchronized List<Entity> getAllNewspapers() throws RemoteException {		
		newspaperTemp = newspaperSQL.getSelectAll();
		return SqlHandler.getInstance().executeSelect(newspaperTemp, "NEWSPAPER");
	}

	@Override
	public synchronized boolean insertNewspaper(Newspaper newspaper) throws RemoteException {
		newspaperTemp = newspaper.getInsert();
		// SqlHandler.getInstance().executeInsert(newspaperTemp, "NEWSPAPER")
		return false;
	}

	@Override
	public synchronized boolean updateNewspaper(Newspaper newspaper) throws RemoteException {
		newspaperTemp = newspaper.getUpdate();
		// SqlHandler.getInstance().executeUpdate(newspaperTemp, "NEWSPAPER")
		return false;
	}

	@Override
	public synchronized boolean deleteNewspaper(Newspaper newspaper) throws RemoteException {
		newspaperTemp = newspaper.getDelete();
		// SqlHandler.getInstance().executeDelete(newspaperTemp, "NEWSPAPER");
		return false;
	}

	@Override
	public synchronized List<Entity> getNewspaperByUUID(String uuid) throws RemoteException {
		newspaperTemp = newspaperSQL.getSelectByUUID(uuid);
		// SqlHandler.getInstance().executeSingleSelect(newspaperTemp, "NEWSPAPER");
		return null;
	}
}
