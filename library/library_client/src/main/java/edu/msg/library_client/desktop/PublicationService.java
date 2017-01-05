package edu.msg.library_client.desktop;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Newspaper;
import edu.msg.library_common.model.Publication;
import edu.msg.library_common.rmi.BookServiceRmi;
import edu.msg.library_common.rmi.LoginServiceRmi;
import edu.msg.library_common.rmi.MagazineServiceRmi;
import edu.msg.library_common.rmi.NewspaperServiceRmi;
import edu.msg.library_common.rmi.SearchServiceRmi;

public class PublicationService {

	private static String title;
	private Scanner scanner;
	private SearchServiceRmi searchServiceRmi;
	private BookServiceRmi bookServiceRmi;
	private MagazineServiceRmi magazineServiceRmi;
	private NewspaperServiceRmi newspaperServiceRmi;

	public PublicationService() {
		try {
			scanner = new Scanner(System.in);
			Registry registry = LocateRegistry.getRegistry("localhost", LoginServiceRmi.RMI_PORT);
			try {
				searchServiceRmi = (SearchServiceRmi) registry.lookup(SearchServiceRmi.RMI_NAME);
				bookServiceRmi = (BookServiceRmi) registry.lookup(BookServiceRmi.RMI_NAME);
				magazineServiceRmi = (MagazineServiceRmi) registry.lookup(MagazineServiceRmi.RMI_NAME);
				newspaperServiceRmi = (NewspaperServiceRmi) registry.lookup(NewspaperServiceRmi.RMI_NAME);
			} catch (NotBoundException e) {

				e.printStackTrace();
			}
		} catch (RemoteException e) {

			e.printStackTrace();
		}

	}

	public List<Publication> getPublications(String title) {
		try {
			return searchServiceRmi.searchPublicationByRegexp(title);
		} catch (RemoteException e) {
			System.out.println("hiba");
		}
		return null;
	}

	public void insertBook(String title, String publisher, int release_date, int number_of_copies, int copies_left) {
		Book book = new Book();
		book.setTitle(title);
		book.setPublisher(publisher);
		book.setReleaseDate(release_date);
		book.setNumberOfCopies(number_of_copies);
		book.setCopiesLeft(copies_left);

		try {
			bookServiceRmi.insertBook(book);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void insertMagazin(String title, String article_title, String publisher, int year, int month,
			int number_of_copies, int copies_left) {
		Magazine magazine = new Magazine();

		magazine.setTitle(title);
		magazine.setArticle_title(article_title);
		magazine.setPublisher(publisher);
		magazine.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(year, month, 01)));
		magazine.setNumberOfCopies(number_of_copies);
		magazine.setCopiesLeft(copies_left);

		try {
			magazineServiceRmi.insertMagazine(magazine);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void insertNewspapaer(String title, String article_title, String publisher, int year, int month, int day,
			int number_of_copies, int copies_left) {
		Newspaper newspaper = new Newspaper();
		newspaper.setTitle(title);
		newspaper.setArticle_title(article_title);
		newspaper.setPublisher(publisher);
		newspaper.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(year, month, day)));
		newspaper.setNumberOfCopies(number_of_copies);
		newspaper.setCopiesLeft(copies_left);

		try {
			newspaperServiceRmi.insertNewspaper(newspaper);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public List<Book> getBooks() {
		try {
			return bookServiceRmi.getAllBooks();
		} catch (Exception e) {

		}
		return null;
	}

	public void updateBook(String seletedBook, String newBooktitle, String newPublisher, int newRelaseDate,
			int newNrOfCopies, int newCopiesLeft) {
		try {

			List<Book> books = getBooks();
			for (Book book : books) {
				if (book.getTitle().equals(seletedBook)) {
					book.setName(newBooktitle);
					book.setPublisher(newPublisher);
					book.setReleaseDate(newRelaseDate);
					book.setNumberOfCopies(newNrOfCopies);
					book.setCopiesLeft(newCopiesLeft);
					bookServiceRmi.updateBook(book);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public List<Magazine> getMagazin() {
		try {
			return magazineServiceRmi.getAllMagazines();
		} catch (Exception e) {

		}
		return null;
	}

	public void updateMagazin(String seletedMagazin, String newMagazintitle, String newArticleTitle,
			String newPublisher, int year, int month, int newNrOfCopies, int newCopiesLeft) {
		try {

			List<Magazine> magazines = getMagazin();
			for (Magazine magazin : magazines) {
				if (magazin.getTitle().equals(seletedMagazin)) {
					magazin.setTitle(newMagazintitle);
					magazin.setArticle_title(newArticleTitle);
					magazin.setPublisher(newPublisher);
					magazin.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(year, month, 01)));
					magazin.setNumberOfCopies(newNrOfCopies);
					magazin.setCopiesLeft(newCopiesLeft);
					magazineServiceRmi.updateMagazine(magazin);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public List<Newspaper> getNewspapers() {
		try {
			return newspaperServiceRmi.getAllNewspapers();
		} catch (Exception e) {

		}
		return null;
	}

	public void updateNewspaper(String selectedNwespapaer, String newNwespapaertitle, String newArticleTitle,
			String newPublisher, int year, int month, int day, int newNrOfCopies, int newCopiesLeft) {
		try {

			List<Newspaper> newspapers = getNewspapers();
			for (Newspaper newspaper :newspapers ) {
				if (newspaper.getTitle().equals(selectedNwespapaer)) {
					newspaper.setTitle(newNwespapaertitle);
					newspaper.setArticle_title(newArticleTitle);
					newspaper.setPublisher(newPublisher);
					newspaper.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(year, month, day)));
					newspaper.setNumberOfCopies(newNrOfCopies);
					newspaper.setCopiesLeft(newCopiesLeft);
					newspaperServiceRmi.updateNewspaper(newspaper);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void deletePublication(Publication publication) {
		if (publication instanceof Book) {
			try {
				bookServiceRmi.deleteBook((Book) publication);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (publication instanceof Magazine) {
			try {
				magazineServiceRmi.deleteMagazine((Magazine) publication);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (publication instanceof Newspaper) {
			try {
				newspaperServiceRmi.deleteNewspaper((Newspaper) publication);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Publication> getPublications() {
		try {
			return searchServiceRmi.getAllPublications();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
