package edu.msg.library_server.backend.service;

import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.msg.library_common.model.Magazine;
import edu.msg.library_common.model.Entity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MagazineServiceTest {
	Magazine magazine;
	MagazineService magazineService;

	public MagazineServiceTest() {
		magazine = new Magazine();
		magazine.setUUID("d21d441c-0494-4b6e-9af8-1c0bc27299cm");
		magazine.setTitle("Magazine");
		magazine.setPublisher("NA");
		magazine.setArticle_title("cikk");
		magazine.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(2015, 12, 01)));
		magazine.setNumberOfCopies(5);
		magazine.setCopiesLeft(4);

		try {
			magazineService = new MagazineService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void init1Magazine() {
		assertNotNull(magazine);
		assertNotNull(magazineService);
	}

	@Test
	public void test2InsertMagazine() {
		try {
			assertTrue(magazineService.insertMagazine(magazine));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test3UpdateMagazine() {
		try {
			magazine.setTitle("TES");
			assertTrue(magazineService.updateMagazine(magazine));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test4GetMagazineByUUID() {
		try {
			Magazine b = (Magazine)(magazineService.getMagazineByUUID(magazine.getUUID()));			
			assertTrue((b.getUUID().equals(magazine.getUUID())) && (b.getNumberOfCopies() == magazine.getNumberOfCopies()));		
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test5GetAllMagazines() {
		try {
			List<Magazine> dbList = new ArrayList<>();
			dbList = magazineService.getAllMagazines();
			assertTrue(dbList.size()>0);
			assertTrue(dbList.stream()
					.map(p->(Magazine)p)
					.filter(p-> (p.getUUID().equals(magazine.getUUID()) && (p.getNumberOfCopies() == magazine.getNumberOfCopies()))
			) != null);
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test6DeleteMagazine() {
		try {			
			assertTrue(magazineService.deleteMagazine(magazine));
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

}