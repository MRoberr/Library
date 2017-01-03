package edu.msg.library_common.model;

public class Publication extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String title;
	private int type;//1-book, 2-news, 3-magazine
	
	
	public static String getCreateView(){
		return "CREATE OR REPLACE VIEW publications AS SELECT * FROM ("+
				"Select b.uuid as uuid, b.title as title,1 as type from books b UNION "+
				"Select n.uuid as uuid, n.title as title,2 as type from newspapers n UNION " +
				"SELECT m.uuid as uuid, m.title as title,3 as type from magazines m"+
				") as p";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getSelectAll() {		
		return "Select * from publications";
	}
	
	public String getSelectByTitle() {		
		return "Select * from publications where title like '% "+ title +" %'";
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
		return "Select * from publications where uuid = '" + uuid + "'";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
