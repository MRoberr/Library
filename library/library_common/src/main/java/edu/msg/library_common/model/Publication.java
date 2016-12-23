package edu.msg.library_common.model;

import java.util.List;

public class Publication extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String title;
	private int releaseDate;
	
	@Override
	public String getSelectAll() {
		// TODO Auto-generated method stub
		return null;
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
		//Don t implement
		return null;
	}

}
