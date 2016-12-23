package edu.msg.library_common.model;

import java.util.List;

public class Book extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String title;
	private String publisher;
	private List<Author> authors;
	private int releaseDate;
	private int numberOfCopies;
	private int copiesLeft;

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

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int release_date) {
		this.releaseDate = release_date;
	}

	public int getCopiesLeft() {
		return copiesLeft;
	}

	public void setCopiesLeft(int copies_left) {
		this.copiesLeft = copies_left;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}
	
	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int number_of_copies) {
		this.numberOfCopies = number_of_copies;
	}

	@Override
	public String toString() {
		return "Book [name=" + title + "]";
	}

	@Override
	public String getSelectAll() {
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

	@Override
	public String getSelectByUUID(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
}
