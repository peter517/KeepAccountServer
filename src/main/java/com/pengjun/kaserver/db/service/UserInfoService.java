package com.pengjun.kaserver.db.service;

import java.io.File;
import java.util.List;

import com.pengjun.kaserver.db.dao.UserInfoDao;
import com.pengjun.kaserver.db.model.UserInfo;
import com.pengjun.kaserver.net.protobuf.KaProtocol.ArProtocol;
import com.pengjun.kaserver.net.protobuf.KaProtocol.ArTypeProtocol;
import com.pengjun.kaserver.net.protobuf.KaProtocol.KaMsg;
import com.pengjun.kaserver.net.protobuf.KaProtocol.MsgRply;
import com.pengjun.kaserver.net.protobuf.KaProtocol.MsgType;
import com.pengjun.kaserver.utils.KaServerConstants;
import com.pengjun.utils.FileUtils;
import com.pengjun.utils.TimeUtils;

public class UserInfoService {

	static {
		FileUtils.createDirIfNotExist(KaServerConstants.BACKUP_ROOT);
	}

	public static KaMsg.Builder checkLogin(KaMsg kaMsg) {

		KaMsg.Builder builder = KaMsg.newBuilder();
		boolean isLoginOk = UserInfoDao.getSingleInstance().loginVerify(kaMsg.getUserInfo().getUserName(),
				kaMsg.getUserInfo().getPassword());
		if (isLoginOk) {
			builder.setMsgRply(MsgRply.SUCCESS);
		} else {
			builder.setMsgRply(MsgRply.FAILED);
		}

		builder.setMsgType(MsgType.LOGIN);

		return builder;
	}

	public static KaMsg.Builder registerUser(KaMsg kaMsg) {

		KaMsg.Builder builder = KaMsg.newBuilder();

		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(kaMsg.getUserInfo().getUserName());
		if (UserInfoDao.getSingleInstance().search(userInfo) == null) {
			builder.setMsgRply(MsgRply.FAILED);
			builder.setMsgType(MsgType.REGISTER);
			builder.setInfo("注册失败，该账号已被注册");
			return builder;
		}

		userInfo.setPassword(kaMsg.getUserInfo().getPassword());
		userInfo.setCreateDate(TimeUtils.getCurDateStr());
		userInfo.setUpdateTime(TimeUtils.getCurTimeStr());

		UserInfoDao.getSingleInstance().insert(userInfo);

		builder.setMsgRply(MsgRply.SUCCESS);
		builder.setMsgType(MsgType.REGISTER);

		return builder;
	}

	public static KaMsg.Builder backup(KaMsg kaMsg) {
		KaMsg.Builder builder = KaMsg.newBuilder();

		String userName = kaMsg.getUserInfo().getUserName();

		FileUtils.createDirIfNotExist(KaServerConstants.BACKUP_ROOT + File.separator + userName);

		boolean isWriteArOk = FileUtils.<ArProtocol> writeListToFile(kaMsg.getArProtocolList(),
				getBackupPathByUserName(userName, ArProtocol.class.getSimpleName()));

		boolean isWriteArTypeOk = FileUtils.<ArTypeProtocol> writeListToFile(kaMsg.getArTypeProtocolList(),
				getBackupPathByUserName(userName, ArTypeProtocol.class.getSimpleName()));

		builder.setMsgType(MsgType.BACKUP);
		if (isWriteArOk && isWriteArTypeOk) {
			builder.setMsgRply(MsgRply.SUCCESS);
		} else {
			builder.setMsgRply(MsgRply.FAILED);
		}

		return builder;
	}

	public static KaMsg.Builder restore(KaMsg kaMsg) {

		KaMsg.Builder builder = KaMsg.newBuilder();
		String userName = kaMsg.getUserInfo().getUserName();

		List<ArProtocol> arList = FileUtils.<ArProtocol> readListFromFile(getBackupPathByUserName(userName,
				ArProtocol.class.getSimpleName()));

		List<ArTypeProtocol> arTypeList = FileUtils
				.<ArTypeProtocol> readListFromFile(getBackupPathByUserName(userName,
						ArTypeProtocol.class.getSimpleName()));

		builder.addAllArProtocol((Iterable<? extends ArProtocol>) arList.iterator());
		builder.addAllArTypeProtocol((Iterable<? extends ArTypeProtocol>) arTypeList.iterator());

		builder.setMsgRply(MsgRply.SUCCESS);
		builder.setMsgType(MsgType.RESTORE);

		return builder;
	}

	private static String getBackupPathByUserName(String userName, String className) {
		return KaServerConstants.BACKUP_ROOT + File.separator + userName + File.separator + className
				+ KaServerConstants.BACKUP_FILE_POSTFIX;
	}

}
