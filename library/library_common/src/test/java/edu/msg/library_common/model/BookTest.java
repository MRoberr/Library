package edu.msg.library_common.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BookTest {
	private Book book;

	public void createBook() {
		book = new Book();
		book.setUUID("999");
		book.setTitle("Test");
		book.setPublisher("uj");
		book.setReleaseDate(2016);
		book.setNumberOfCopies(5);
		book.setCopiesLeft(4);
	}

	@Test
	public void insertTest() {
		createBook();
		String insert = book.getInsert();
		System.out.println("insert: " + insert);
		assertEquals(
				"insert into books (uuid, title, publisher, release_date, nr_of_copies, copies_left) values ('999','Test','uj',2016,5,4)",
				insert);
	}

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
	
	@Test
	public void insertAuthorsTest() {
		createBook();
		List<Author> authors = new ArrayList<>();
		Author author = new Author();
		author.setName("alma");
		author.setUUID("369");
		authors.add(author);
		Author author2 = new Author();
		author2.setName("almafa");
		author2.setUUID("3698");
		authors.add(author2);
		
		book.setAuthors(authors);
		String insertAuthor = book.insertAuthors();
		System.out.println("insert authors: " + insertAuthor);
		assertEquals("insert into publications_authors (publications_uuid, authors_uuid) "
					+ "values('999','369');"
					+ "insert into publications_authors (publications_uuid, authors_uuid) "
					+ "values('999','3698');", insertAuthor);
	}

}
