package edu.msg.library_common.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Magazine extends Publication {

	private static final long serialVersionUID = 1L;
	private String title;
	private String article_title;
	private String publisher;
	private List<Author> authors;
	private Date releaseDate;
	private int numberOfCopies;
	private int copiesLeft;
	
	public Magazine() {
		authors = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Magazine [title=" + title + ", article_title=" + article_title + ", publisher=" + publisher
				+ ", authors=" + authors + ", releaseDate=" + releaseDate + ", numberOfCopies=" + numberOfCopies
				+ ", copiesLeft=" + copiesLeft + "]";
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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
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

	@Override
	public String getSelectAll() {
		return "select * from magazines";
	}

	@Override
	public String getInsert() {
		return "insert into magazines (uuid, title, article_title, publisher, release_date, nr_of_copies, copies_left)"
				+ "values('" + getUUID() + "','" + this.title + "','" + this.article_title + "','" + this.publisher
				+ "','" + this.releaseDate + "'," + this.numberOfCopies + "," + this.copiesLeft + ")";
	}

	@Override
	public String getUpdate() {
		return "update magazines set title='" + this.title + "'," + "article_title='" + this.article_title + "',"
				+ "publisher='" + this.publisher + "'," + "release_date='" + this.releaseDate + "'," + "nr_of_copies="
				+ this.numberOfCopies + "," + "copies_left=" + this.copiesLeft + " where uuid='" + getUUID() + "'";
	}

	@Override
	public String getDelete() {
		return "delete from magazines where uuid='" + getUUID() + "'";
	}

	@Override
	public String getSelectByUUID(String uuid) {
		return "select * from magazines where uuid='" + uuid + "'";
	}
	
	public String insertAuthors(){
		StringBuilder strBld = new StringBuilder();
		for(Author author : authors){
			strBld.append("insert into publications_authors (publications_uuid, authors_uuid) "
					+ "values('" + getUUID() + "','" + author.getUUID() + "');");
		}
		return strBld.toString();
	}

}
