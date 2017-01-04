package edu.msg.library_client.desktop.console;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.msg.library_client.desktop.ClientService;
import edu.msg.library_client.desktop.UiFactory;
import edu.msg.library_client.desktop.jfxgui.model.ConnectionModel;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.User;

public class MainConsole extends UiFactory {
	private ClientService clientService = new ClientService();
	Scanner scanner = new Scanner(System.in);

	public MainConsole() {

	}

	public void startConsole() {
		System.out.println("Please enter your name and password!");
		login();
	}
	
	private void login(){
		LoginAccess login=ConnectionModel.INSTANCE.login(scanner.next(), scanner.next());
		if(login.equals(LoginAccess.DENIED)){
			System.out.println("Invalid user name or password, please try again!");
			login();
		}
		else if(login.equals(LoginAccess.ADMIN)){
			System.out.println("logged in as admin");
			menuforAdmin();
			while (true) {
				handleAdminCommand();
				System.out.println("next command");
			}
		}else{
			System.out.println("logged in as user");
			menuforUser();
			while (true) {
			//	handleUserCommand();
			}
		}
	}

	private void handleAdminCommand() {
		try {
			int cmd = scanner.nextInt();
			switch (cmd) {
			case 1:
				//kiadvany utani kerese
				searchPublication();
				break;
			case 2:
				createNewUser();
				break;
			
			case 3:
				updateClient();
				break;
			case 4:
				deleteClient();
				break;
			case 5:
				searchClient();
				break;
			case 6:
				createNewPublication();//please implement
				break;
			case 11:
				listUsers();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("invalid command, try again...");
		}
	}

	private void listUsers() {
		List<User> users = clientService.getAllUsers();
		for (Entity user : users) {
			System.out.println(user);
		}
	}

	private void createNewUser() {
		System.out.println("Enter name and password!");
		clientService.newClientCreate(scanner.next(), scanner.next());
	}
	private void updateClient(){
		System.out.println("Enter old name and new name!");
		clientService.clientUpdate(scanner.next(), scanner.next());
	}
	private void deleteClient(){
		System.out.println("Enter name!");
		clientService.clientDelete(scanner.next());
	}
	
	private void createNewPublication() {
		System.out.println("Please specify the type(1-Book, 2-Magazine, 3-Newspaper)");
		switch (scanner.nextInt()) {
		case 1:
			//create new book...
			break;
		case 2:
				
				break;
		case 3:
					
					break;

		default:
			break;
		}
		System.out.println("Enter name and password!");
		//clientService.newClientCreate(scanner.next(), scanner.next());
	}
	/*private void updateClient(){
		System.out.println("Enter old name and new name!");
		clientService.clientUpdate(scanner.next(), scanner.next());
	}
	private void deleteClient(){
		System.out.println("Enter name!");
		clientService.clientDelete(scanner.next());
	}*/
	private void searchClient(){
		System.out.println("Enter name!");
		clientService.searchClient(scanner.next());
	}
	private void searchPublication(){
		System.out.println("Enter title!");
		clientService.searchPublication(scanner.next());
	}
	private void menuforAdmin() {
		System.out.println("Please choose one option!");
		System.out.println("1-Kiadvany utani kereses");
		System.out.println("2-Uj felhasznalo letrehozasa");
		System.out.println("3-Felhasznalo adatainak modositasa");
		System.out.println("4-Felhasznalo torlese");
		System.out.println("5-Felhasznalo utani kereses");
		System.out.println("6-Uj kiadvany letrehozasa");
		System.out.println("7-Meglevo kiadvany adatainak modositasa");
		System.out.println("8-Meglevo kiadvany torlese");
		System.out.println("9-Kiadvany kolcsonzes");
		System.out.println("10-Kiadvany visszavetele");
		System.out.println("11-az osszes felhasznalo lekerese");
	}

	private void menuforUser() {
		System.out.println("Type");
		System.out.println("1-Kiadvany utani kereses");
	}
}
