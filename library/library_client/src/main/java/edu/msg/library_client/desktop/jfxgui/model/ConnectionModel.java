package edu.msg.library_client.desktop.jfxgui.model;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

import edu.msg.library_client.desktop.jfxgui.ConnectionException;
import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Newspaper;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.MagazineServiceRmi;
import edu.msg.library_common.rmi.NewspaperServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;
import edu.msg.library_common.rmi.UserServiceRmi;

public enum ConnectionModel {

	INSTANCE;

	private Registry registry;

	private LoginServiceRmi loginServiceRmi;
	private BookServiceRmi bookServiceRmi;
	private MagazineServiceRmi magazineServiceRmi;
	private NewspaperServiceRmi newspaperServiceRmi;
	
	private UserServiceRmi userServiceRmi;
	private SearchServiceRmi searchServiceRmi;
	
	
	{
		connect();

		try {

			loginServiceRmi = (LoginServiceRmi) registry.lookup(LoginServiceRmi.RMI_NAME);
			bookServiceRmi = (BookServiceRmi) registry.lookup(BookServiceRmi.RMI_NAME);
			magazineServiceRmi = (MagazineServiceRmi) registry.lookup(MagazineServiceRmi.RMI_NAME);
			newspaperServiceRmi = (NewspaperServiceRmi) registry.lookup(NewspaperServiceRmi.RMI_NAME);
			
			userServiceRmi = (UserServiceRmi) registry.lookup(UserServiceRmi.RMI_NAME);
			searchServiceRmi = (SearchServiceRmi) registry.lookup(SearchServiceRmi.RMI_NAME);

		} catch (RemoteException | NotBoundException e) {
//			e.printStackTrace();
			throw new ConnectionException("Connection error", e);
		}

	}

	private void connect() {

		try {

			registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
		} catch (RemoteException e) {

			System.out.println("fail connect");
		}
	}

	public LoginAccess login(String user, String pass) {

		try {

			return loginServiceRmi.login(user, pass);
		} catch (RemoteException | SQLException e) {

			return LoginAccess.DENIED;
		}
	}

	public List<Book> getAllBooks() throws ConnectionException {

		try {

			return bookServiceRmi.getAllBooks();
		} catch (RemoteException e) {

			throw new ConnectionException("Connection error", e);
		}
	}

	public List<Magazine> getAllMagazines() throws ConnectionException {
		
		try {
			
			return magazineServiceRmi.getAllMagazines();
		} catch (RemoteException e) {
			
			throw new ConnectionException("Connection error", e);
		}
		
	}
	
	public List<Newspaper> getAllNewspapers() throws ConnectionException {
		
		try {
			
			return newspaperServiceRmi.getAllNewspapers();
		} catch (RemoteException e) {
			
			throw new ConnectionException("Connection error", e);
		}
	}
	
	public List<User> getAllUsers() throws ConnectionException {
		
		try {
			
			return userServiceRmi.getAllUsers();
		} catch (RemoteException e) {
			
			throw new ConnectionException("Connection error", e);
		}
	}
	
	public void addUser(String name, String password, LoginAccess userType) throws ConnectionException {
		
		User user = new User(name, userType, password);
		
		try {
			userServiceRmi.insertUser(user);
		} catch (RemoteException e) {

			throw new ConnectionException("Connction error", e);
		}
	}
	
	public void editUser(User user) throws ConnectionException {
		
		try {

			userServiceRmi.updateUser(user);
		} catch (RemoteException e) {
			
			throw new ConnectionException("Connection error", e);
		}
	}
	
	public void deleteUser(User user) throws ConnectionException {
		
		try {
			
			userServiceRmi.deleteUser(user);
		} catch (RemoteException e) {
			
			throw new ConnectionException("Connection error", e);
		}
	}
	
	public List<Publication> getAllPublications() throws ConnectionException {
		
		try {
			
			return searchServiceRmi.getAllPublications();
		} catch (RemoteException e) {
			
			throw new ConnectionException("Connection error", e);
		}
	}

}
