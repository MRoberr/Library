package edu.msg.library_common.model;

public class Author extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 *{@link author#name}
	 *The name of the author, contains the last name and first name.
	 */
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
		return "select * from authors";
	}

	@Override
	public String getInsert() {
		return "insert into authors (uuid, name) " + "values ('" + getUUID() + "','" + this.name + "')";
	}

	@Override
	public String getUpdate() {
		return "update authors set name='" + this.name + "' where uuid='" + getUUID() + "'";
	}

	@Override
	public String getDelete() {
		return "delete from authors where uuid='" + getUUID() + "'";
	}

	@Override
	public String getSelectByUUID(String uuid) {
		return "select * from authors where uuid='" + uuid + "'";
	}

}
