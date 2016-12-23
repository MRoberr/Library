package edu.msg.library_common.model;

import java.util.Date;

public class Newspaper extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String title;
	private String article_title;
	private String publisher;
	private Date releaseDate;
	private int numberOfCopies;
	private int copiesLeft;

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
