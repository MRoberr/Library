 package edu.msg.library_common.model;

import java.util.List;

public class Book extends Publication {

	private static final long serialVersionUID = 1L;
	/**
	 * {@link book#title}
	 * The title of the book.
	 */
	private String title;
	/**
	 * {@link book#publisher}
	 * The publisher of the book.
	 */
	private String publisher;
	/**
	 * {@link book#authors}
	 * The authors of the book. A book has atleast one author.
	 */
	private List<Author> authors;
	/**
	 * {@link book#releaseDate}
	 * The year when the book was released.
	 */
	private int releaseDate;
	/**
	 * {@link book#numberOfCopies}
	 * Represents the maximum number of book copies the library has.
	 */
	private int numberOfCopies;
	/**
	 * {@link book#copiesLeft}
	 * Represents the number of book copies left in the library.
	 */
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
		return "select * from books";
	}

	@Override
	public String getInsert() {
		return "insert into books (uuid, title, publisher, release_date, nr_of_copies, copies_left) " + "values ('"
				+ getUUID() + "','" + this.title + "','" + this.publisher + "'," + this.releaseDate + ","
				+ this.numberOfCopies + "," + this.copiesLeft + ")";
	}

	@Override
	public String getUpdate() {
		return "update books set title='" + this.title + "'," + "publisher='" + this.publisher + "'," + "release_date="
				+ this.releaseDate + "," + "nr_of_copies=" + this.numberOfCopies + "," + "copies_left="
				+ this.copiesLeft + " where uuid='" + getUUID() + "'";
	}

	@Override
	public String getDelete() {
		return "delete from books where uuid='" + getUUID() + "'";
	}

	@Override
	public String getSelectByUUID(String uuid) {
		return "select * from books where uuid='" + uuid + "'";
	}
	
	
	/**
	 * 
	 * @return a string representation of the SQL statement which inserts the
	 *         authors' id and the books' id into the mapping table
	 */
	public String insertAuthors(){
		StringBuilder strBld = new StringBuilder();
		for(Author author : authors){
			strBld.append("insert into publications_authors (publications_uuid, authors_uuid) "
					+ "values('" + getUUID() + "','" + author.getUUID() + "');");
		}
		return strBld.toString();
	} 
}
