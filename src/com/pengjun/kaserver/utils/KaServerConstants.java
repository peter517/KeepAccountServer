package com.pengjun.kaserver.utils;

import org.apache.log4j.Logger;

import com.pengjun.utils.LoggerUtils;

public class KaServerConstants {

	public static final Logger serviceLogger = LoggerUtils.getLogger("kaserver");
	public static final String BACKUP_ROOT = "backup";
	public static final String BACKUP_FILE_POSTFIX = ".bk";
}
