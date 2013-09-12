package com.pengjun.kaserver.net;

import com.pengjun.kaserver.utils.KaServerConstants;
import com.pengjun.net.BaseNettyServer;

public class KaServer extends BaseNettyServer {
	private final static int port = 8000;

	private KaServer(int port) {
		super(port);
	}

	private static KaServer kaClient;

	public static KaServer getInstance() {
		if (kaClient == null) {
			kaClient = new KaServer(port);
		}
		return kaClient;
	}

	public void start() {
		super.start(new KaServerPipelineFactory());
	}

	public static void main(String[] args) throws Exception {

		KaServerConstants.serviceLogger.info("main start");
		KaServer.getInstance().start();
	}
}
