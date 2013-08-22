package com.pengjun.ka.db.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccountRecord implements Serializable {

	private static final long serialVersionUID = -4580617869739349892L;

	private static Map<Integer, String> id2TypeMap = new HashMap<Integer, String>();

	public static final String COL_ID = "id";
	public static final String COL_ACOUNT = "account";
	public static final String COL_TYPE_ID = "typeId";
	public static final String COL_CREATE_DATE = "createDate";
	public static final String COL_COMMENT = "comment";
	public static final String COL_UPDATE_TIME = "updateTime";

	int id;

	float account;

	int typeId;

	String createDate;

	String comment;

	String updateTime;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAccount() {
		return account;
	}

	public void setAccount(float acount) {
		this.account = acount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
