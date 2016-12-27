package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Magazin;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.MagazineServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class MagazineService extends UnicastRemoteObject implements MagazineServiceRmi{

	private static final long serialVersionUID = 1L;
	private String magazineTemp;
	private Magazin magazineSQL;

	protected MagazineService() throws RemoteException {
		super();
		magazineSQL = new Magazin();
	}

	@Override
	public synchronized List<Entity> getAllMagazines() throws RemoteException {		
		magazineTemp = magazineSQL.getSelectAll();
		return SqlHandler.getInstance().executeSelect(magazineTemp, "MAGAZINE");
	}

	@Override
	public synchronized boolean insertMagazine(Magazin magazine) throws RemoteException {
		magazineTemp = magazine.getInsert();
		return SqlHandler.getInstance().executeInsert(magazineTemp, "MAGAZINE");
	}

	@Override
	public synchronized boolean updateMagazine(Magazin magazine) throws RemoteException {
		magazineTemp = magazine.getUpdate();
		return SqlHandler.getInstance().executeUpdate(magazineTemp, "MAGAZINE");
	}

	@Override
	public synchronized boolean deleteMagazine(Magazin magazine) throws RemoteException {
		magazineTemp = magazine.getDelete();
		return SqlHandler.getInstance().executeDelete(magazineTemp, "MAGAZINE");
	}

	@Override
	public synchronized Entity getMagazineByUUID(String uuid) throws RemoteException {
		magazineTemp = magazineSQL.getSelectByUUID(uuid);
		return SqlHandler.getInstance().executeSingleSelect(magazineTemp, "MAGAZINE");
	}
}

