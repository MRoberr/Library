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
		
		String selectAll = author.getSelectAll();
		System.out.println("select all: " + selectAll);
		
		author.setName("TestUpdate");
		String update = author.getUpdate();
		System.out.println("update: " + update);
		
		String delete = author.getDelete();
		System.out.println("delete: " + delete);
		
		String selectOne = author.getSelectByUUID("123");
		System.out.println("select one: " + selectOne);
	}

}
