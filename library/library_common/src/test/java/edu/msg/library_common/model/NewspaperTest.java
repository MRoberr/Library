package edu.msg.library_common.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class NewspaperTest {
	private Newspaper newspaper;

	public void createNewspaper() {
		newspaper = new Newspaper();
		newspaper.setUUID("456456");
		newspaper.setTitle("newspaper");
		newspaper.setArticle_title("cikk");
		newspaper.setPublisher("publisher");
		newspaper.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(2015, 12, 01)));
		newspaper.setNumberOfCopies(5);
		newspaper.setCopiesLeft(6);
	}

	@Test
	public void insertTest() {
		createNewspaper();
		String insert = newspaper.getInsert();
		System.out.println("insert: " + insert);
		assertEquals(
				"insert into newspapers (uuid, title, article_title, publisher, release_date, nr_of_copies, copies_left)"
						+ "values(456456,'newspaper','cikk','publisher','2015-12-01',5,6)",
				insert);
	}

	@Test
	public void selectAllTest() {
		createNewspaper();
		String selectAll = newspaper.getSelectAll();
		System.out.println("select all: " + selectAll);
		assertEquals("select * from newspapers", selectAll);
	}

	@Test
	public void updateTest() {
		newspaper = new Newspaper();
		newspaper.setUUID("456456");
		newspaper.setTitle("ujnewspaper");
		newspaper.setArticle_title("ujCikk");
		newspaper.setPublisher("ujPub");
		newspaper.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(2013, 12, 01)));
		newspaper.setNumberOfCopies(10);
		newspaper.setCopiesLeft(3);

		String update = newspaper.getUpdate();
		System.out.println("update: " + update);
		assertEquals("update newspapers set title='ujnewspaper',article_title='ujCikk',publisher='ujPub',"
				+ "release_date='2013-12-01',nr_of_copies=10,copies_left=3 where uuid='456456'", update);
	}

	@Test
	public void deleteTest() {
		createNewspaper();
		String delete = newspaper.getDelete();
		System.out.println("delete: " + delete);
		assertEquals("delete from newspapers where uuid='456456'", delete);
	}

	@Test
	public void selectByIdTest() {
		createNewspaper();
		String selectOne = newspaper.getSelectByUUID("999");
		System.out.println("select one: " + selectOne);
		assertEquals("select * from newspapers where uuid='999'", selectOne);
	}

}
