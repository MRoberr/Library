package edu.msg.library_common.model;

public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private int userType;
	private int loyalityIndex;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getLoyalityIndex() {
		return loyalityIndex;
	}

	public void setLoyalityIndex(int loyalityIndex) {
		this.loyalityIndex = loyalityIndex;
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
