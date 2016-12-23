package edu.msg.library_common.model;

public class User extends BaseEntity {

	
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelect() {
		return "select * from library_users";
	}

	public String getInsert() {
		return "insert into library_users (uuid, name) " 
				+ "values (" + getUUID() + "," + this.name + ")";
	}

	public String getUpdate() {	
		return "update library_users set name="  + this.name + 
				" where uuid=" + getUUID();
	}

	public String getDelete() {		
		return "delete from library_users where uuid=" + getUUID();
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

	
}
