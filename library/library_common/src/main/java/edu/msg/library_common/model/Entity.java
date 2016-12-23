package edu.msg.library_common.model;

import java.io.Serializable;

public interface Entity extends Serializable {
	
	String getSelectAll();

	String getInsert();

	String getUpdate();

	String getDelete();
	
	String getSelectByUUID(String uuid);
	
}
