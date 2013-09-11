package com.pengjun.kaserver.db.service;

import com.pengjun.kaserver.db.dao.UserInfoDao;
import com.pengjun.kaserver.db.model.UserInfo;
import com.pengjun.kaserver.net.protobuf.KaProtocol.KaMsg;
import com.pengjun.kaserver.net.protobuf.KaProtocol.MsgRply;
import com.pengjun.kaserver.net.protobuf.KaProtocol.MsgType;

public class UserInfoService {

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
		userInfo.setPassword(kaMsg.getUserInfo().getPassword());
		userInfo.setCreateDate("");
		userInfo.setUpdateTime("");
		UserInfoDao.getSingleInstance().insert(userInfo);

		builder.setMsgType(MsgType.REGISTER);

		return builder;
	}
}
