package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.MagazineServiceRmi;
import edu.msg.library_server.backend.repository.SqlHandler;

public class MagazineService extends UnicastRemoteObject implements MagazineServiceRmi{

	private static final long serialVersionUID = 1L;
	private String magazineTemp;
	private Magazine magazineSQL;
	private boolean ret;

	protected MagazineService() throws RemoteException {
		super();
		magazineSQL = new Magazine();
	}

	@Override
	public synchronized List<Magazine> getAllMagazines() throws RemoteException {	
		//getAllMagazine inkább
		magazineTemp = magazineSQL.getSelectAll();
//		return SqlHandler.getInstance().executeMagazineSelect(magazineTemp, "MAGAZINE");
		return SqlHandler.getInstance().executeMagazineSelect(magazineTemp);
	}

	@Override
	public synchronized boolean insertMagazine(Magazine magazine) throws RemoteException {		
		magazineTemp = magazine.getInsert();		
		ret = SqlHandler.getInstance().executeSqlStatement(magazineTemp);
		magazineTemp = magazine.insertAuthors();	
		return ret && SqlHandler.getInstance().executeSqlStatement(magazineTemp);
	}

	@Override
	public synchronized boolean updateMagazine(Magazine magazine) throws RemoteException {
		//updateMagazine inkább
		magazineTemp = magazine.getUpdate();
		return SqlHandler.getInstance().executeSqlStatement(magazineTemp);
	}

	@Override
	public synchronized boolean deleteMagazine(Magazine magazine) throws RemoteException {
		//deleteMagazine inkább
		magazineTemp = magazine.getDelete();
		//ret = SqlHandler.getInstance().executeSqlStatement(magazineTemp);
		//magazineTemp = magazine.getDeleteAuthors();
		return SqlHandler.getInstance().executeSqlStatement(magazineTemp);
	}

	@Override
	public synchronized Entity getMagazineByUUID(String uuid) throws RemoteException {
		magazineTemp = magazineSQL.getSelectByUUID(uuid);
		return SqlHandler.getInstance().executeSingleSelect(magazineTemp, "MAGAZINE");
	}
}

