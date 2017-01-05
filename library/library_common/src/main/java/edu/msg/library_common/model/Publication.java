package edu.msg.library_common.model;

public abstract class Publication extends BaseEntity{

	private static final long serialVersionUID = 1L;
	protected String title;
	protected int numberOfCopies;
	protected int copiesLeft;
	
	public static String getCreateView(){
		return "CREATE OR REPLACE VIEW publications AS SELECT * FROM ("+
				"Select b.uuid as uuid, b.title as title, b.copies_left as stock, 1 as type from books b UNION "+
				"Select n.uuid as uuid, n.title as title, n.copies_left as stock, 2 as type from newspapers n UNION " +
				"SELECT m.uuid as uuid, m.title as title, m.copies_left as stock, 3 as type from magazines m"+
				") as p";
	}
	
	public String getTitle() {
		
		return title;
	}

	public abstract void setTitle(String title);
	
	public abstract void setCopiesLeft(int copies);
	
	public abstract int getCopiesLeft();
	
	public abstract int getNumberOfCopies();
	
	@Override
	public String getSelectAll() {		
		//Don't implement
		return null;
	}
	
	public static String getSelectByTitle(String title) {		
		
		return "Select * from publications where title like '%"+ title +"%'";
	}
	
	public static String getSelectByRegexp(String regexp){
		return "select * from publications where title REGEXP '"+regexp+"'";
	}

	public static String getSelectAllPublications() {
		
		return "Select * from publications";
	}
	
	@Override
	public String getInsert() {
		//Don t implement
		return null;
	}

	@Override
	public String getUpdate() {
		//Don t implement
		return null;
	}

	@Override
	public String getDelete() {
		//Don t implement
		return null;
	}

	@Override
	public String getSelectByUUID(String uuid) {
		//Don't implement
		return null;
	}

	public static String getSelectByPublicationUUID(String uuid) {
		return "Select * from publications where uuid = '" + uuid + "'";
	}

	public abstract String publicationToString();
	
	

}