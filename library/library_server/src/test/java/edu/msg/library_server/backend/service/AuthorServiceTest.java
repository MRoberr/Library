package edu.msg.library_server.backend.service;

import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Entity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorServiceTest {
	Author author;
	AuthorService authorService;

	public AuthorServiceTest() {
		author = new Author();
		author.setUUID("d21d441c-0494-4b6e-9af8-1c0bc27299ca");
		author.setName("Author");
		try {
			authorService = new AuthorService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void init1Author() {
		assertNotNull(author);
		assertNotNull(authorService);
	}

	@Test
	public void test2InsertAuthor() {
		try {
			assertTrue(authorService.insertAuthor(author));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test3UpdateAuthor() {
		try {
			author.setName("TES");
			assertTrue(authorService.updateAuthor(author));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test4GetAuthorByUUID() {
		try {
			Author b = (Author)(authorService.getAuthorByUUID(author.getUUID()));			
			assertTrue((b.getUUID().equals(author.getUUID())));		
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test5GetAllAuthors() {
		try {
			List<Entity> dbList = new ArrayList<>();
			dbList = authorService.getAllAuthors();
			assertTrue(dbList.size()>0);
			assertTrue(dbList.stream()
					.map(p->(Author)p)
					.filter(p-> (p.getUUID().equals(author.getUUID()))
			) != null);
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test6DeleteAuthor() {
		try {			
			assertTrue(authorService.deleteAuthor(author));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

}