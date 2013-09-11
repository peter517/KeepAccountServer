package com.pengjun.kaserver.db.dao;

import java.sql.SQLException;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.pengjun.db.BaseDao;
import com.pengjun.kaserver.db.DbHelper;
import com.pengjun.kaserver.db.model.UserInfo;

public class UserInfoDao extends BaseDao<UserInfo> {

	private UserInfoDao(ConnectionSource cs, Class<UserInfo> modelClass) {
		super(cs, modelClass);
	}

	private static UserInfoDao userInfoDao = null;

	public static UserInfoDao getSingleInstance() {
		if (userInfoDao == null) {
			userInfoDao = new UserInfoDao(DbHelper.getSingleInstance().getConnectionSource(), UserInfo.class);
		}
		return userInfoDao;
	}

	public boolean loginVerify(String userName, String password) {

		try {
			QueryBuilder<UserInfo, Integer> queryBuilder = dao.queryBuilder();
			Where<UserInfo, Integer> where = queryBuilder.where();
			where.eq(UserInfo.COL_USER_NAME, userName);
			where.and();
			where.eq(UserInfo.COL_USER_PASSWORD, password);

			return dao.query(queryBuilder.prepare()).size() != 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
