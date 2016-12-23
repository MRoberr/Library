package edu.msg.library_common.model;

public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	// private String password;
	private int user_type;
	private int loyality_index;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLoyality_index() {
		return loyality_index;
	}

	public void setLoyality_index(int loyality_index) {
		this.loyality_index = loyality_index;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public String getSelect() {
		return "select * from library_users";
	}

	public String getInsert() {
		return "insert into library_users (uuid, name) " + "values (" + getUUID() + "," + this.name + ")";
	}

	public String getUpdate() {
		return "update library_users set name=" + this.name + " where uuid=" + getUUID();
	}

	public String getDelete() {
		return "delete from library_users where uuid=" + getUUID();
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

}
