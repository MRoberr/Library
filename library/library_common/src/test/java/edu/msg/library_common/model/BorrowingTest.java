package edu.msg.library_common.model;

import static org.junit.Assert.*;

<<<<<<< HEAD
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
				"insert into publication_borrowings (publications_uuid, user_uuid, uuid, borrowing_date, deadline, return_date)"
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
=======
import org.junit.Test;

public class BorrowingTest {
	private Book book;
	private User user;
	private Borrowing bb;

	public void create() {
		book = new Book();
		user= new User();
		bb=new Borrowing();
		bb.setPublicationUuid(book.getUUID());
		bb.setUserUuid(user.getUUID());
	}

	@Test
	public void insertTest() {
		create();
		String insert = bb.getInsert();
		System.out.println("insert: " + insert);
		
	}

	/*
	@Test
	public void selectAllTest() {
		createBook();
		String selectAll = book.getSelectAll();
		System.out.println("select all: " + selectAll);
		assertEquals("select * from books", selectAll);
	}

	@Test
	public void updateTest() {
		book = new Book();
		book.setUUID("999");
		book.setTitle("uj");
		book.setPublisher("uj");
		book.setReleaseDate(2015);
		book.setNumberOfCopies(2);
		book.setCopiesLeft(3);
		String update = book.getUpdate();
		System.out.println("update: " + update);
		assertEquals(
				"update books set title='uj',publisher='uj',release_date=2015,nr_of_copies=2,copies_left=3 where uuid='999'",
				update);
	}

	@Test
	public void deleteTest() {
		createBook();
		String delete = book.getDelete();
		System.out.println("delete: " + delete);
		assertEquals("delete from books where uuid='999'", delete);
	}

	@Test
	public void selectOneTest() {
		createBook();
		String selectOne = book.getSelectByUUID("999");
		System.out.println("select one: " + selectOne);
		assertEquals("select * from books where uuid='999'", selectOne);
	}
*/
>>>>>>> branch 'master' of https://github.com/MRoberr/Library.git
}
