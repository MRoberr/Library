package edu.msg.library_common.model;

import java.util.UUID;

public abstract class BaseEntity implements Entity {
	/**
	 * {@link baseEntity#uuId} Represents a unique id.
	 */
	private String uuId;

	public String getUUID() {
		if (uuId == null) {
			uuId = UUID.randomUUID().toString();
		}
		return uuId;
	}

	public void setUUID(String uuID) {
		uuId = uuID;
	}

}
