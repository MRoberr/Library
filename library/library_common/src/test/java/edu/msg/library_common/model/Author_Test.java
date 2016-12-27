package edu.msg.library_common.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class Author_Test {

	@Test
	public void test() {
		Author author = new Author();
		author.setUUID("12345");
		author.setName("Test");		
		
		String insert = author.getInsert();
		System.out.println("insert: " + insert);
		assertEquals("insert into authors (uuid, name) values (12345,Test)", insert);
		
		String selectAll = author.getSelectAll();
		System.out.println("select all: " + selectAll);
		assertEquals("select * from authors", selectAll);
		
		author.setName("TestUpdate");
		String update = author.getUpdate();
		System.out.println("update: " + update);
		assertEquals("update authors set name=TestUpdate where uuid=12345", update);
		
		String delete = author.getDelete();
		System.out.println("delete: " + delete);
		assertEquals("delete from authors where uuid=12345", delete);
		
		String selectOne = author.getSelectByUUID("123");
		System.out.println("select one: " + selectOne);
		assertEquals("select * from authors where uuid=12345", selectOne);
	}

}
