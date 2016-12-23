package edu.msg.library_common.model;

import java.io.Serializable;

public interface Entity extends Serializable {
	
	String getSelect();

	String getInsert();

	String getUpdate();

	String getDelete();
}
