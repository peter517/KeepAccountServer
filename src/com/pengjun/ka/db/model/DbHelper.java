package com.pengjun.ka.db.model;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DbHelper {

	private final static String DATABASE_URL = "jdbc:h2:file:ka.db";

	@SuppressWarnings("rawtypes")
	public static final Class[] DATACLASSES = { UserInfo.class };

	private static ConnectionSource cs;

	static {

		try {
			for (int i = 0; i < DATACLASSES.length; i++) {
				TableUtils.createTableIfNotExists(getConnectionSource(), UserInfo.class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionSource getConnectionSource() {
		if (cs == null) {
			try {
				cs = new JdbcConnectionSource(DATABASE_URL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cs;
	}

}
