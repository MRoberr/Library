package edu.msg.library_common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.msg.library_common.model.Magazin;
import edu.msg.library_common.model.Entity;

public interface MagazineServiceRmi extends Remote {

	public static final String RMI_NAME = "Magazin";

	public static final int RMI_PORT = 1099;

	public List<Entity> getAllMagazines() throws RemoteException;

	public boolean insertMagazine(Magazin magazine) throws RemoteException;

	public boolean updateMagazine(Magazin magazine) throws RemoteException;

	public boolean deleteMagazine(Magazin magazine) throws RemoteException;

	public Entity getMagazineByUUID(String uuid) throws RemoteException;

}
