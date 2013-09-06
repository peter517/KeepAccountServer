package com.pengjun.ka.net;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

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

		LoggerUtils.kaLogger.info("main start");
		new KaServer(8000).run();
	}
}
