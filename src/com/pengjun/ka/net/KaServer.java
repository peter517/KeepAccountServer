package com.pengjun.ka.net;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.pengjun.ka.db.model.UserInfo;
import com.pengjun.ka.db.model.dao.UserInfoDao;
import com.pengjun.ka.utils.LoggerUtils;

public class KaServer {

	private final int port;

	public KaServer(int port) {
		this.port = port;
	}

	public void run() {

		LoggerUtils.serviceLogger.info("Server start");
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		bootstrap.setPipelineFactory(new KaServerPipelineFactory());

		bootstrap.bind(new InetSocketAddress(port));

	}

	public static void main(String[] args) throws Exception {

		LoggerUtils.serviceLogger.info("main start");

		UserInfo userInfo = new UserInfo();
		userInfo.setCreateDate("");
		userInfo.setUpdateTime("");
		userInfo.setUserName("pj");
		userInfo.setPassword("123");

		UserInfoDao.insert(userInfo);

		LoggerUtils.serviceLogger.info("UserInfoDao.loginVerify " + UserInfoDao.loginVerify("pj", "123"));
		new KaServer(8000).run();
	}
}
