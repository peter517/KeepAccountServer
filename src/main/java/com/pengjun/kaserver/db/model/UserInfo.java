package com.pengjun.kaserver.db.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = -4580617869739349891L;

	public static final String COL_ID = "id";
	public static final String COL_USER_NAME = "userName";
	public static final String COL_USER_PASSWORD = "password";
	public static final String COL_UPDATE_TIME = "updateTime";
	public static final String COL_CREATE_DATE = "createDate";

	@DatabaseField(generatedId = true, columnName = "_id")
	int id;

	@DatabaseField(canBeNull = false)
	String userName;

	@DatabaseField(canBeNull = false)
	String password;

	@DatabaseField(canBeNull = false)
	String createDate;

	@DatabaseField(canBeNull = false)
	String updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
