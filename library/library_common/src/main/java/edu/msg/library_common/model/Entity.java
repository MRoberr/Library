package edu.msg.library_common.model;

import java.io.Serializable;

public interface Entity extends Serializable {
	/**
	 * 
	 * @return a string representation of all objects returned by the SQL read
	 *         function
	 */
	String getSelectAll();

	/**
	 * 
	 * @return a string representation of the SQL create function
	 */
	String getInsert();

	/**
	 * 
	 * @return a string representation of the SQL update function
	 */
	String getUpdate();

	/**
	 * 
	 * @return a string representation of the SQL delete function
	 */
	String getDelete();

	/**
	 * 
	 * @return a string representation of one object returned by the SQL read
	 *         function
	 */
	String getSelectByUUID(String uuid);

}
