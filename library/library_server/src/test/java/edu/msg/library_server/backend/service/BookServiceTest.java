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
		book.setUUID("d24v741c-0494-4b62-9af8-1c0bc27299cb");
		book.setTitle("Moby Dick");
		book.setPublisher("Herman Melville");
		book.setReleaseDate(1851);
		book.setNumberOfCopies(45);
		book.setCopiesLeft(34);

		try {
			bookService = new BookService();
			bookService.insertBook(book);
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
			Book b = (Book)(bookService.getBookByUUID(book.getUUID()));			
			assertTrue((b.getUUID().equals(book.getUUID())) && (b.getNumberOfCopies() == book.getNumberOfCopies()));		
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test5GetAllBooks() {
		try {
			List<Book> dbList = new ArrayList<>();
			dbList = bookService.getAllBooks();
			assertTrue(dbList.size()>0);
			assertTrue(dbList.stream()
					.map(p->(Book)p)
					.filter(p-> (p.getUUID().equals(book.getUUID()) && (p.getNumberOfCopies() == book.getNumberOfCopies()))
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