package edu.msg.library_server.backend.service;

import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.library_common.model.Newspaper;
import edu.msg.library_common.model.Entity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewspaperServiceTest {
	Newspaper newspaper;
	NewspaperService newspaperService;

	public NewspaperServiceTest() {
		newspaper = new Newspaper();
		newspaper.setUUID("d21d441c-0494-4b6e-9af8-1c0bc27299cn");
		newspaper.setTitle("Newspaper");
		newspaper.setPublisher("NA");
		newspaper.setArticle_title("cikk");
		newspaper.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(2015, 12, 01)));
		newspaper.setNumberOfCopies(5);
		newspaper.setCopiesLeft(4);

		try {
			newspaperService = new NewspaperService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void init1Newspaper() {
		assertNotNull(newspaper);
		assertNotNull(newspaperService);
	}

	@Test
	public void test2InsertNewspaper() {
		try {
			assertTrue(newspaperService.insertNewspaper(newspaper));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test3UpdateNewspaper() {
		try {
			newspaper.setTitle("TES");
			assertTrue(newspaperService.updateNewspaper(newspaper));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test4GetNewspaperByUUID() {
		try {
			Newspaper b = (Newspaper)(newspaperService.getNewspaperByUUID(newspaper.getUUID()));			
			assertTrue((b.getUUID().equals(newspaper.getUUID())) && (b.getNumberOfCopies() == newspaper.getNumberOfCopies()));		
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test5GetAllNewspapers() {
		try {
			List<Entity> dbList = new ArrayList<>();
			dbList = newspaperService.getAllNewspapers();
			assertTrue(dbList.size()>0);
			assertTrue(dbList.stream()
					.map(p->(Newspaper)p)
					.filter(p-> (p.getUUID().equals(newspaper.getUUID()) && (p.getNumberOfCopies() == newspaper.getNumberOfCopies()))
			) != null);
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test6DeleteNewspaper() {
		try {			
			assertTrue(newspaperService.deleteNewspaper(newspaper));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

}