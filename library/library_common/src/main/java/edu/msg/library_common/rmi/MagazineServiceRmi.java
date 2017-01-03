package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Entity;

public interface MagazineServiceRmi extends Remote {

	public static final String RMI_NAME = "Magazine";

	public static final int RMI_PORT = 1099;

	public List<Magazine> getAllMagazines() throws RemoteException;

	public boolean insertMagazine(Magazine magazine) throws RemoteException;

	public boolean updateMagazine(Magazine magazine) throws RemoteException;

	public boolean deleteMagazine(Magazine magazine) throws RemoteException;

	public Entity getMagazineByUUID(String uuid) throws RemoteException;

}
