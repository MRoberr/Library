package edu.msg.library_common.model;

import java.util.Date;

/**
 * 
 * @author gallb
 *
 * The representation of book borrowing, assign a book to a user, and stores the relevant dates.
 */

public class Borrowing extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String publicationUuid;
	private String userUuid;
	private Date borrowingDate;
	private Date deadline;
	private Date returnDate;
	
//his will be my wok
	
	
	public String getPublicationUuid() {
		return publicationUuid;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public void setPublicationUuid(String publicationUuid) {
		this.publicationUuid = publicationUuid;
	}

	public Date getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String getSelectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsert() {
		return "insert into publication_borrowings	 (publications_uuid, user_uuid, uuid, borrowing_date, deadline, returning_date) " + 
													 "values ('"+ this.getPublicationUuid() + ", '" + this.getUserUuid() + "', '" +
													 			  this.getUUID() + "', "+ this.getBorrowingDate() + ", " + 
													 			  this.getDeadline() + ", " + this.getReturnDate() + ")";
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
