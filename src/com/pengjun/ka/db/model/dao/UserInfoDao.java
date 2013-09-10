package com.pengjun.ka.db.model.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.pengjun.ka.db.model.DbHelper;
import com.pengjun.ka.db.model.UserInfo;

public class UserInfoDao {
	private static ConnectionSource cs = DbHelper.getConnectionSource();

	private static Dao<UserInfo, Integer> dao = null;

	static {
		try {
			dao = DaoManager.createDao(cs, UserInfo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insert(UserInfo userInfo) {

		try {
			dao.create(userInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean loginVerify(String userName, String password) {

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
