package edu.msg.library_common.model;

public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private LoginAccess userType;
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

	public LoginAccess getUserType() {
		return userType;
	}

	public void setUserType(LoginAccess userType) {
		this.userType = userType;
	}

	public int getLoyalityIndex() {
		return loyalityIndex;
	}

	public void setLoyalityIndex(int loyalityIndex) {
		this.loyalityIndex = loyalityIndex;
	}

	public String getSelectAll() {
		return "select * from library_users";
	}

	// userTypeNum: Admin = 1, User = 0;
	public String getInsert() {
		int userTypeNum;
		if (getUserType() == LoginAccess.ADMIN) {
			userTypeNum = 1;
		} else {
			userTypeNum = 0;
		}
		return "insert into library_users (uuid, name, user_type, loyality_index, password) " + "values ('" + getUUID()
				+ "','" + this.name + "','" + userTypeNum + "','" + getLoyalityIndex() + "','" + getPassword() + "')";
	}

	public String getUpdate() {
		return "update library_users set name='" + this.name + "' where uuid='" + getUUID() + "'";
	}

	public String getDelete() {
		return "delete from library_users where uuid='" + getUUID() + "'";
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", userType=" + userType + ", loyalityIndex=" + loyalityIndex + "]";
	}

	@Override
	public String getSelectByUUID(String uuid) {
		return "select * from library_users where uuid='" + uuid + "'";
	}

	public String getSelectByName(String name) {
		return "select * from library_users where uuid='" + getName() + "'";
	}
}
