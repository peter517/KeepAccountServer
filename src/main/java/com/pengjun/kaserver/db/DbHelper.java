package com.pengjun.kaserver.db;

import com.pengjun.db.orm.BaseDbHelper;
import com.pengjun.kaserver.db.model.UserInfo;

public class DbHelper extends BaseDbHelper {

	private final static String DB_URL = "jdbc:h2:file:ka";
	@SuppressWarnings("rawtypes")
	public static final Class[] DBCLASSES = { UserInfo.class };

	protected DbHelper(String dbUrl, Class[] dbClass) {
		super(dbUrl, dbClass);
	}

	private static DbHelper dbHelper = null;

	public static DbHelper getSingleInstance() {
		if (dbHelper == null) {
			dbHelper = new DbHelper(DB_URL, DBCLASSES);
		}
		return dbHelper;
	}

}
