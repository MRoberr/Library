package edu.msg.library_common.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AuthorTest {
	private Author author;

	public void createAuthor() {
		author = new Author();
		author.setUUID("12345");
		author.setName("Test");
	}

	@Test
	public void selectAllTest() {
		createAuthor();
		String selectAll = author.getSelectAll();
		System.out.println("select all: " + selectAll);
		assertEquals("select * from authors", selectAll);
	}

	@Test
	public void insertTest() {
		createAuthor();
		String insert = author.getInsert();
		System.out.println("insert: " + insert);
		assertEquals("insert into authors (uuid, name) values (12345,'Test')", insert);
	}

	@Test
	public void updateTest() {
		author.setUUID("12345");
		author.setName("TestUpdate");
		String update = author.getUpdate();
		System.out.println("update: " + update);
		assertEquals("update authors set name='TestUpdate' where uuid='12345'", update);
	}

	@Test
	public void deleteTest() {
		createAuthor();
		String delete = author.getDelete();
		System.out.println("delete: " + delete);
		assertEquals("delete from authors where uuid='12345'", delete);
	}

	@Test
	public void selectOneTest() {
		createAuthor();
		String selectOne = author.getSelectByUUID("12345");
		System.out.println("select one: " + selectOne);
		assertEquals("select * from authors where uuid='12345'", selectOne);
	}

}
