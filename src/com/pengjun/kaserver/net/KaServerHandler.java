package com.pengjun.kaserver.net;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.pengjun.kaserver.db.service.UserInfoService;
import com.pengjun.kaserver.net.protobuf.KaProtocol.KaMsg;
import com.pengjun.kaserver.utils.KaServerConstants;

public class KaServerHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		if (e instanceof ChannelStateEvent) {
			KaServerConstants.serviceLogger.info(e.toString());
		}
		super.handleUpstream(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {

		KaMsg kaMsg = (KaMsg) e.getMessage();
		KaMsg.Builder builder = null;

		switch (kaMsg.getMsgType()) {
		case REGISTER:
			builder = UserInfoService.registerUser(kaMsg);
			break;
		case LOGIN:
			builder = UserInfoService.checkLogin(kaMsg);
			break;
		case BACKUP:
			break;
		case RESTORE:
			break;
		}
		e.getChannel().write(builder.build());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		KaServerConstants.serviceLogger.error("Unexpected exception from downstream.", e.getCause());
		e.getChannel().close();
	}

}
