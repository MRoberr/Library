package edu.msg.library_server.backend.service;

import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.msg.library_common.model.Book;
import edu.msg.library_common.model.Entity;

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
	public void initBook() {
		assertNotNull(book);
		assertNotNull(bookService);
	}

	@Test
	public void testInsertBook() {
		try {
			assertTrue(bookService.insertBook(book));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdateBook() {
		try {
			assertTrue(bookService.updateBook(book));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteBook() {
		try {
			assertTrue(bookService.deleteBook(book));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetBookByUUID() {
		try {
			assertEquals(book, bookService.getBookByUUID(book.getUUID()));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetAllBooks() {
		try {
			List<Entity> resultList = new ArrayList<>();
			resultList.add(book);
			assertNotNull(bookService.getAllBooks());
			assertEquals(resultList, bookService.getAllBooks());
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

}