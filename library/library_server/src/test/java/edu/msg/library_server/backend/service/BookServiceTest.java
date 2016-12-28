package edu.msg.library_server.backend.service;

import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Entity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceTest {
	Book book;
	BookService bookService;

	public BookServiceTest() {
		book = new Book();
		book.setUUID("998");
		book.setTitle("Tests");
		book.setPublisher("ujsd");
		book.setReleaseDate(2016);
		book.setNumberOfCopies(5);
		book.setCopiesLeft(4);

		try {
			bookService = new BookService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void init1Book() {
		assertNotNull(book);
		assertNotNull(bookService);
	}

	@Test
	public void test2InsertBook() {
		try {
			assertTrue(bookService.insertBook(book));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test3UpdateBook() {
		try {
			book.setTitle("TES");
			assertTrue(bookService.updateBook(book));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test4GetBookByUUID() {
		try {
			assertEquals(book, bookService.getBookByUUID(book.getUUID()));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test5GetAllBooks() {
		try {
			List<Entity> dbList = new ArrayList<>();
			dbList = bookService.getAllBooks();
			assertTrue(dbList.size()>0);
			assertTrue(dbList.stream()
					.map(p->(Book)p)
					.filter(p-> (p.getUUID() == book.getUUID() && (p.getNumberOfCopies() == book.getNumberOfCopies()))
			) != null);
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test6DeleteBook() {
		try {			
			assertTrue(bookService.deleteBook(book));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

}