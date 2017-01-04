package edu.msg.library_client.desktop.console;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.msg.library_client.desktop.ClientService;
import edu.msg.library_client.desktop.PublicationService;
import edu.msg.library_client.desktop.UiFactory;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;

public class MainConsole extends UiFactory {
	private ClientService clientService = new ClientService();
	private PublicationService publicationService = new PublicationService();
	Scanner scanner = new Scanner(System.in);

	public MainConsole() {

	}

	public void startConsole() {
		// login ->
		// if admin -> menuforadmin
		// else -> menu for user
		menuforAdmin();
		while (true) {
			handleCommand();
		}
	}

	private void handleCommand() {
		try {
			int cmd = scanner.nextInt();
			switch (cmd) {
			case 1:
				searchPublications();
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
				System.out.println("0-viszalepes");
				System.out.println("1-Konyv letrehozasa");
				System.out.println("2-Magazin letrehozasa");
				System.out.println("3-Ujsag letrehozasa");
				int admincmd = scanner.nextInt();
				switch (admincmd) {
				case 1:
					createNewBook();
					break;
				case 2:
					createMagazin();
					break;
				case 3:
					createNewspaper();
					break;

				case 0:
					break;

				}

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
		clientService.newClientCreate(scanner.next(), scanner.next());
	}

	private void updateClient() {
		clientService.clientUpdate(scanner.next(), scanner.next());
	}

	private void deleteClient() {
		clientService.clientDelete(scanner.next());
	}

	private void searchClient() {
		clientService.searchClient(scanner.next());
	}

	private void searchPublications() {
		List<Publication> publications = publicationService.getPublications(scanner.next());
		if (publications.isEmpty()) {
			System.out.println("nem talahato ijen kony");
		}
		for (Publication publication : publications) {
			System.out.println(publication);
		}
	}

	private void createNewBook() {
		publicationService.insertBook(scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt(),
				scanner.nextInt());
	}
	private void createMagazin(){
		publicationService.insertMagazin(scanner.next(),scanner.next(),scanner.next(),scanner.nextInt(),scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
	}
	private void createNewspaper(){
		publicationService.insertNewspapaer(scanner.next(),scanner.next(),scanner.next(),scanner.nextInt(), scanner.nextInt(),scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
	}

	private void menuforAdmin() {
		System.out.println("Type");
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
