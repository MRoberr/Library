package edu.msg.library_common.model;

import java.util.List;

public class Book extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String title;
	private String publisher;
	private List<Author> authors;
	private int release_date;
	private int copies_left;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public int getRelease_date() {
		return release_date;
	}

	public void setRelease_date(int release_date) {
		this.release_date = release_date;
	}

	public int getCopies_left() {
		return copies_left;
	}

	public void setCopies_left(int copies_left) {
		this.copies_left = copies_left;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}
	
	@Override
	public String toString() {
		return "Book [name=" + title + "]";
	}

	@Override
	public String getSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDelete() {
		// TODO Auto-generated method stub
		return null;
	}
}
