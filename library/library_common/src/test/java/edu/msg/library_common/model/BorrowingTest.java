package edu.msg.library_common.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class BorrowingTest {
	private Borrowing borrowing;
	
	public void createBorrowing() {
		borrowing = new Borrowing();
		borrowing.setPublicationUuid("789456");
		borrowing.setUUID("456456");
		borrowing.setUserUuid("123");
		
		borrowing.setBorrowingDate(java.sql.Date.valueOf(LocalDate.of(2015, 12, 01)));
		borrowing.setDeadline(java.sql.Date.valueOf(LocalDate.of(2015, 12, 28)));
		//borrowing.setReturnDate(java.sql.Date.valueOf(LocalDate.of(2016, 01, 15)));
		
	}

	@Test
	public void insertTest() {
		createBorrowing();
		String insert = borrowing.getInsert();
		System.out.println("insert: " + insert);
		assertEquals(
				"insert into publication_borrowings (publications_uuid, user_uuid, uuid, borrowing_date, deadline, returning_date)"
						+ " values('789456','123','456456','2015-12-01','2015-12-28','null')",
				insert);
	}

	@Test
	public void selectAllTest() {
		createBorrowing();
		String selectAll = borrowing.getSelectAll();
		System.out.println("select all: " + selectAll);
		assertEquals("select * from publication_borrowings", selectAll);
	}

	@Test
	public void updateTest() {
		createBorrowing();

		borrowing.setReturnDate(java.sql.Date.valueOf(LocalDate.of(2016, 02, 15)));
		

		String update = borrowing.getUpdate();
		System.out.println("update: " + update);
		assertEquals("update publication_borrowings set publicationUuid='789456',userUuid='123',"
				+"borrowingDate='2015-12-01',deadline='2015-12-28',returnDate='2016-02-15'"		
				+ " where uuid='456456'", update);
		}

	@Test
	public void deleteTest() {
		createBorrowing();
		String delete = borrowing.getDelete();
		System.out.println("delete: " + delete);
		assertEquals("delete from publication_borrowings where uuid='456456'", delete);
	}

	@Test
	public void selectByIdTest() {
		createBorrowing();
		String selectOne = borrowing.getSelectByUUID("456456");
		System.out.println("select one: " + selectOne);
		assertEquals("select * from publication_borrowings where uuid='456456'", selectOne);
	}
}
