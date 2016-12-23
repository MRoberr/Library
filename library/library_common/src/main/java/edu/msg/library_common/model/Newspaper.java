package edu.msg.library_common.model;

import java.util.Date;

public class Newspaper extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String title;
	private String article_title;
	private String publisher;
	private Date release_date;
	private int copies_left;	

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

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getCopies_left() {
		return copies_left;
	}

	public void setCopies_left(int copies_left) {
		this.copies_left = copies_left;
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
