package edu.msg.library_client.desktop;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Newspaper;
import edu.msg.library_common.model.Publication;

public class PublicationService {

	public List<Publication> getPublications(String title) {
		try {
			return RmiRegistry.searchRmi.searchPublicationByRegexp(title);
		} catch (RemoteException e) {
			System.out.println("hiba");
		}
		return null;
	}

	public boolean insertBook(String title, String publisher, int release_date, int number_of_copies, int copies_left,List<Author> authors) {
		Book book = new Book();
		book.setTitle(title);
		book.setPublisher(publisher);
		book.setReleaseDate(release_date);
		book.setNumberOfCopies(number_of_copies);
		book.setCopiesLeft(copies_left);
		book.setAuthors(authors);

		try {
			return RmiRegistry.bookServiceRmi.insertBook(book);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insertMagazin(String title, String article_title, String publisher, int year, int month,
			int number_of_copies, int copies_left) {
		Magazine magazine = new Magazine();

		magazine.setTitle(title);
		magazine.setArticle_title(article_title);
		magazine.setPublisher(publisher);
		magazine.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(year, month, 01)));
		magazine.setNumberOfCopies(number_of_copies);
		magazine.setCopiesLeft(copies_left);

		try {
			return RmiRegistry.magazineServiceRmi.insertMagazine(magazine);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insertNewspapaer(String title, String article_title, String publisher, int year, int month, int day,
			int number_of_copies, int copies_left) {
		Newspaper newspaper = new Newspaper();
		newspaper.setTitle(title);
		newspaper.setArticle_title(article_title);
		newspaper.setPublisher(publisher);
		newspaper.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(year, month, day)));
		newspaper.setNumberOfCopies(number_of_copies);
		newspaper.setCopiesLeft(copies_left);

		try {
			return RmiRegistry.newspaperServiceRmi.insertNewspaper(newspaper);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Book> getBooks() {
		try {
			return RmiRegistry.bookServiceRmi.getAllBooks();
		} catch (Exception e) {

		}
		return null;
	}

	public boolean updateBook(String seletedBook, String newBooktitle, String newPublisher, int newRelaseDate,
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
					return RmiRegistry.bookServiceRmi.updateBook(book);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Magazine> getMagazin() {
		try {
			return RmiRegistry.magazineServiceRmi.getAllMagazines();
		} catch (Exception e) {

		}
		return null;
	}

	public boolean updateMagazin(String seletedMagazin, String newMagazintitle, String newArticleTitle,
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
					return RmiRegistry.magazineServiceRmi.updateMagazine(magazin);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Newspaper> getNewspapers() {
		try {
			return RmiRegistry.newspaperServiceRmi.getAllNewspapers();
		} catch (Exception e) {

		}
		return null;
	}

	public boolean updateNewspaper(String selectedNwespapaer, String newNwespapaertitle, String newArticleTitle,
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
					return RmiRegistry.newspaperServiceRmi.updateNewspaper(newspaper);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deletePublication(Publication publication) {
		if (publication instanceof Book) {
			try {
				return RmiRegistry.bookServiceRmi.deleteBook((Book) publication);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (publication instanceof Magazine) {
			try {
				return RmiRegistry.magazineServiceRmi.deleteMagazine((Magazine) publication);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (publication instanceof Newspaper) {
			try {
				return RmiRegistry.newspaperServiceRmi.deleteNewspaper((Newspaper) publication);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<Publication> getPublications() {
		try {
			return RmiRegistry.searchRmi.getAllPublications();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
