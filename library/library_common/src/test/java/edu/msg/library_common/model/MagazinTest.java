package edu.msg.library_common.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class MagazinTest {
	private Magazin magazin;

	public void createMagazin() {
		magazin = new Magazin();
		magazin.setUUID("456456");
		magazin.setTitle("magazin");
		magazin.setArticle_title("cikk");
		magazin.setPublisher("publisher");
		magazin.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(2015, 12, 01)));
		magazin.setNumberOfCopies(5);
		magazin.setCopiesLeft(6);
	}

	@Test
	public void insertTest() {
		createMagazin();
		String insert = magazin.getInsert();
		System.out.println("insert: " + insert);
		assertEquals(
				"insert into magazines (uuid, title, article_title, publisher, release_date, nr_of_copies, copies_left)"
						+ "values(456456,'magazin','cikk','publisher','2015-12-01',5,6)",
				insert);
	}

	@Test
	public void selectAllTest() {
		createMagazin();
		String selectAll = magazin.getSelectAll();
		System.out.println("select all: " + selectAll);
		assertEquals("select * from magazines", selectAll);
	}

	@Test
	public void updateTest() {
		magazin = new Magazin();
		magazin.setUUID("456456");
		magazin.setTitle("ujMAgazin");
		magazin.setArticle_title("ujCikk");
		magazin.setPublisher("ujPub");
		magazin.setReleaseDate(java.sql.Date.valueOf(LocalDate.of(2013, 12, 01)));
		magazin.setNumberOfCopies(10);
		magazin.setCopiesLeft(3);

		String update = magazin.getUpdate();
		System.out.println("update: " + update);
		assertEquals("update magazines set title='ujMAgazin',article_title='ujCikk',publisher='ujPub',"
				+ "release_date='2013-12-01',nr_of_copies=10,copies_left=3 where uuid='456456'", update);
	}

	@Test
	public void deleteTest() {
		createMagazin();
		String delete = magazin.getDelete();
		System.out.println("delete: " + delete);
		assertEquals("delete from magazines where uuid='456456'", delete);
	}

	@Test
	public void selectByIdTest() {
		createMagazin();
		String selectOne = magazin.getSelectByUUID("999");
		System.out.println("select one: " + selectOne);
		assertEquals("select * from magazines where uuid='999'", selectOne);
	}

}
