package edu.msg.library_server.backend.service;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.Date;

import org.junit.Test;

import edu.msg.library_common.model.Borrowing;
import junit.framework.Assert;

public class BorrowingServiceTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void insertPublicationTest () {
		Borrowing borrowingTemp = new Borrowing();
		borrowingTemp.setPublicationUuid("112");
		borrowingTemp.setUserUuid("23");
		Date date = new Date();
		borrowingTemp.setReturnDate(date);
		borrowingTemp.setBorrowingDate(date);
//		borrowingTemp.setReturnDate(date);
		BorrowingService bs;
		try {
			bs = new BorrowingService();
			assertTrue(bs.insertBorrowing (borrowingTemp));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
