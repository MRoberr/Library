package edu.msg.library_client.desktop.console;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.msg.library_client.desktop.BorrowingService;
import edu.msg.library_client.desktop.ClientService;
import edu.msg.library_client.desktop.PublicationService;
import edu.msg.library_client.desktop.UiFactory;
import edu.msg.library_client.desktop.jfxgui.model.ConnectionModel;
import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Borrowing;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.LoginAccess;
import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.model.User;

public class MainConsole extends UiFactory {
	private ClientService clientService = new ClientService();
	private PublicationService publicationService = new PublicationService();

	Scanner scanner = new Scanner(System.in);

	public MainConsole() {

	}

	public void startConsole() {
		System.out.println("Please enter your name and password!");
		login();

	}

	private void login() {
		LoginAccess login = ConnectionModel.INSTANCE.login(scanner.next(), scanner.next());
		if (login.equals(LoginAccess.DENIED)) {
			System.out.println("Invalid user name or password, please try again!");
			login();
		} else if (login.equals(LoginAccess.ADMIN)) {

			System.out.println("Logged in as admin...");

			menuforAdmin();
			while (true) {
				try {
					handleAdminCommand();
					System.out.println("Type the number of the next command!");
				} catch (InputMismatchException e) {
					System.out.println("Invalid command, try again...");
					scanner.nextLine();
					System.out.println();
				}
			}
		} else {

			System.out.println("Logged in as user...");

			menuforUser();
			while (true) {
				handleUserCommand();
			}
		}
	}

	private void handleAdminCommand() {
		int cmd = scanner.nextInt();
		switch (cmd) {
		case 1:
			System.out.println("Enter title!");
			searchPublications();
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
			newPublicationHandle();
			break;
		case 7:
			publicationUpdateHandle();
			break;
		case 8:
			deletePublication();
			break;
		case 9:
			try {
				if (borrowing()) {
					System.out.println("Borrow successful!");
				} else
					System.out.println("Borrow not successful, please try again!");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("You are not allowed to borrow!");
			}
			break;
		case 10:
			returning();
			break;
		case 11:
			listUsers();
			break;
		}

	}

	private void newPublicationHandle() {
		System.out.println("0-Go back");
		System.out.println("1-Create book");
		System.out.println("2-Create magazine");
		System.out.println("3-Create newspaper");
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
		default:
			break;

		}
	}

	private void publicationUpdateHandle() {
		System.out.println("0-Go back");
		System.out.println("1-Update book");
		System.out.println("2-Update magazine");
		System.out.println("3-Update newspaper");
		int admincmd = scanner.nextInt();
		switch (admincmd) {
		case 1:
			updateBook();
			break;
		case 2:
			updateMagazin();
			break;

		case 3:
			// updateNewspaper();
			break;
		case 0:
		default:
			break;

		}

	}

	private List<User> listUsers() {
		List<User> users = clientService.getAllUsers();
		int i = 0;
		for (User user : users) {
			System.out.println(++i + "-" + user.getName() + " " + user.getLoyalityIndex());
		}
		return users;
	}

	private void createNewUser() {
		System.out.println("Enter name, password and type!");
		String userName = scanner.next();

		String password = scanner.next();
		String type = scanner.next();
		LoginAccess loginAccess = null;
		if (type.equals("ADMIN")) {
			loginAccess = LoginAccess.ADMIN;
		} else if (type.equals("USER")) {
			loginAccess = LoginAccess.USER;
		}
		if (loginAccess == null) {
			System.out.println("Invalid login access!");
		} else {
			clientService.newClientCreate(userName, loginAccess, 10, password);

		}
	}

	private void updateClient() {
		System.out.println("Enter old name and new name!");
		clientService.clientUpdate(scanner.next(), scanner.next());
	}

	private void deleteClient() {
		System.out.println("Enter name!");
		clientService.clientDelete(scanner.next());
	}

	private void searchClient() {
		System.out.println("Enter name!");
		clientService.searchClient(scanner.next());
	}

	private void searchPublications() {
		String reg = "";
		while (reg.isEmpty()) {
			reg = scanner.nextLine();
		}
		List<Publication> publications = publicationService.getPublications(reg);
		if (publications.isEmpty()) {

			System.out.println("Couldn't find this publication!");
			return;
		}
		for (Publication publication : publications) {
			System.out.println(publication.getTitle());
		}
	}

	private void createNewBook() {
		publicationService.insertBook(scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt(),
				scanner.nextInt());
	}

	private void createMagazin() {
		publicationService.insertMagazin(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(),
				scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
	}

	private void createNewspaper() {
		publicationService.insertNewspapaer(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(),
				scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

	}

	private void updateBook() {
		List<Book> books = publicationService.getBooks();
		for (Book book : books) {
			System.out.println(book);
		}

		System.out.println("Enter old title and update all parameters!");
		publicationService.updateBook(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(),
				scanner.nextInt(), scanner.nextInt());

	}

	private void deletePublication() {
		List<Publication> publications = publicationService.getPublications();
		int i = 0;
		for (Publication publication : publications) {
			System.out.println(++i + "-" + publication.publicationToString());
		}
		System.out.println("Please enter the number corresponding to the publication:");
		int pubNr = scanner.nextInt();
		String publicationName = scanner.nextLine();
		for (Publication publication : publications) {
			if (pubNr > 0 && pubNr <= publications.size()) {
				if (publications.get(pubNr - 1).getTitle().equals(publication.getTitle())) {
					publicationService.deletePublication(publication);
				}
			}
		}
	}

	private void updateMagazin() {
		List<Magazine> magazines = publicationService.getMagazin();
		for (Magazine magazine : magazines) {
			System.out.println(magazines);
		}

		System.out.println("Enter old title and update all parameters!");
		publicationService.updateMagazin(scanner.next(), scanner.next(), scanner.next(), scanner.next(),
				scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
	}

	private void menuforAdmin() {
		System.out.println("Please choose one option!");
		System.out.println("1-Search for publication");
		System.out.println("2-Create user");
		System.out.println("3-Change user data");
		System.out.println("4-Delete user");
		System.out.println("5-Search for user");
		System.out.println("6-Create publication");
		System.out.println("7-Change publication data");
		System.out.println("8-Delete publication");
		System.out.println("9-Borrow publication");
		System.out.println("10-Return publication");
		System.out.println("11-Show all users");
	}

	private void handleUserCommand() {
		System.out.println("Enter title!");
		searchPublications();

	}

	private void menuforUser() {
		System.out.println("1-Search for publication");
	}

	public boolean borrowing() throws RemoteException {
		BorrowingService bs = new BorrowingService();
		List<User> users = listUsers();

		System.out.println("\n");
		List<Publication> publications = bs.getAllPublications();
		int i = 0;
		for (Publication publication : publications) {
			System.out.println(++i + "-" + publication.publicationToString());
		}

		System.out.println("\nPlease select a user and one publication from the lists above!(Type name and title)");
		int user = scanner.nextInt();
		int publication = scanner.nextInt();
		if (user > users.size() || publication > publications.size()) {
			return false;
		}
		User u = users.get(user - 1);
		Publication pub = publications.get(publication - 1);
		Borrowing borrow = new Borrowing();
		borrow.setUserUuid(u.getUUID());
		borrow.setPublicationUuid(pub.getUUID());
		borrow.setBorrowingDate(java.sql.Date.valueOf(LocalDate.now()));
		borrow.setDeadline(java.sql.Date.valueOf(LocalDate.now().plusDays(20)));
		return (bs.borrow(borrow));
	}

	public void returning() {
		System.out.println("Please type the readers username: ");

		List<User> users = clientService.getAllUsers();
		List<Publication> borrowingsOfUser = new ArrayList<>();
		User user = new User();
		BorrowingService bs = new BorrowingService();
		String userName = "";
		boolean userFlag = false;
		while (!userFlag) {
			userName = scanner.next();
			Borrowing borrowOne = new Borrowing();
			for (User u : users) {
				if (u.getName().equals(userName)) {
					borrowingsOfUser = bs.getBackBorrowingList(u);
					user = u;
					userFlag = true;
					break;
				}

			}
			if (!userFlag) {
				for (User u : users) {
					System.out.print(u.getName() + " ");
				}
				System.out.println("\nInvalid username, see possible usernames above.\nPlease retry: ");
			}
		}

		if (!borrowingsOfUser.isEmpty()) {
			int i = 0;
			for (Publication p : borrowingsOfUser) {
				System.out.println(i++ + "-" + p.getTitle());
			}
			System.out.println("Type the number of the book");
			int nr = scanner.nextInt();

			if (bs.returnBookInLibrary(user, borrowingsOfUser.get(nr)) == true) {
				System.out.println("Return was succesful!");
			} else {
				System.out.println("Return not succesful!");
			}
		} else {
			System.out.println(userName + " doesn't have any borrowed books.");
		}
	}
}
