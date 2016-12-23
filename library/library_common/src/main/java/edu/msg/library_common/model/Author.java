package edu.msg.library_common.model;

public class Author extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Author [name=" + name + "]";
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
