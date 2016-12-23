package edu.msg.library_server.backend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.msg.library_common.model.Entity;
import edu.msg.library_common.rmi.BorrowingServiceRmi;

public class BorrowingService extends UnicastRemoteObject implements BorrowingServiceRmi {

	private static final long serialVersionUID = 1L;

	protected BorrowingService() throws RemoteException {
		super();
	} 

	@Override
	public boolean insertPublication(Entity ent) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePublication(Entity ent) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
}