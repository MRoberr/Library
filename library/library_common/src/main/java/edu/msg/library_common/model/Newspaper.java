package edu.msg.library_common.model;

import java.util.Date;

public class Newspaper extends Publication {

	private static final long serialVersionUID = 1L;

	
	/**
	 * {@link newspaper#article_title} The title of the article.
	 */
	private String article_title;
	/**
	 * {@link newspaper#publisher} The publisher of the newspaper.
	 */
	private String publisher;
	/**
	 * {@link newspaper#releaseDate} The date when the newspaper was released.
	 */
	private Date releaseDate;
	/**
	 * {@link newspaper#copiesLeft} Represents the number of newspapers copies
	 * left in the library.
	 */
	private int numberOfCopies;
	/**
	 * {@link newspaper#copiesLeft} Represents the number of newspapers copies
	 * left in the library.
	 */
	private int copiesLeft;
	
	@Override
	public String toString() {
		return "Newspaper [title=" + title + ", article_title=" + article_title + ", publisher=" + publisher
				+ ", releaseDate=" + releaseDate + ", numberOfCopies=" + numberOfCopies + ", copiesLeft=" + copiesLeft
				+ "]";
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public int getCopiesLeft() {
		return copiesLeft;
	}

	public void setCopiesLeft(int copiesLeft) {
		this.copiesLeft = copiesLeft;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String getSelectAll() {
		return "select * from newspapers";
	}

	@Override
	public String getInsert() {
		return "insert into newspapers (uuid, title, article_title, publisher, release_date, nr_of_copies, copies_left)"
				+ "values('" + getUUID() + "','" + this.title + "','" + this.article_title + "','" + this.publisher
				+ "','" + this.releaseDate + "'," + this.numberOfCopies + "," + this.copiesLeft + ")";
	}

	@Override
	public String getUpdate() {
		return "update newspapers set title='" + this.title + "'," + "article_title='" + this.article_title + "',"
				+ "publisher='" + this.publisher + "'," + "release_date='" + this.releaseDate + "'," + "nr_of_copies="
				+ this.numberOfCopies + "," + "copies_left=" + this.copiesLeft + " where uuid='" + getUUID() + "'";
	}

	@Override
	public String getDelete() {
		return "delete from newspapers where uuid='" + getUUID() + "'";
	}

	@Override
	public String getSelectByUUID(String uuid) {
		return "select * from newspapers where uuid='" + uuid + "'";
	}

}
