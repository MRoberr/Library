package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Newspaper;
import edu.msg.library_common.model.Entity;

public interface NewspaperServiceRmi extends Remote {

	public static final String RMI_NAME = "Magazin";

	public static final int RMI_PORT = 1099;

	public List<Entity> getAllNewspapers() throws RemoteException;

	public boolean insertNewspaper(Newspaper newspaper) throws RemoteException;

	public boolean updateNewspaper(Newspaper newspaper) throws RemoteException;

	public boolean deleteNewspaper(Newspaper newspaper) throws RemoteException;

	public List<Entity> getNewspaperByUUID(String uuid) throws RemoteException;

}

