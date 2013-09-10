package com.pengjun.ka.net;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.pengjun.ka.net.protobuf.KaProtocol.KaMsg;
import com.pengjun.ka.net.protobuf.KaProtocol.MsgType;
import com.pengjun.ka.utils.LoggerUtils;

public class KaServerHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		if (e instanceof ChannelStateEvent) {
			LoggerUtils.serviceLogger.info(e.toString());
		}
		super.handleUpstream(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {

		KaMsg kaMsg = (KaMsg) e.getMessage();

		switch (kaMsg.getMsgType()) {
		case REGISTER:
			LoggerUtils.serviceLogger.info("kaMsg.getMsgType() :" + kaMsg.getMsgType());
			break;
		case LOGIN:
			LoggerUtils.serviceLogger.info("kaMsg.getMsgType() :" + kaMsg.getMsgType());
			break;
		case BACKUP:
			LoggerUtils.serviceLogger.info("kaMsg.getMsgType() :" + kaMsg.getMsgType());
			break;
		case RESTORE:
			LoggerUtils.serviceLogger.info("kaMsg.getMsgType() :" + kaMsg.getMsgType());
			break;
		}
		KaMsg.Builder builder = KaMsg.newBuilder();
		builder.setMsgType(MsgType.REGISTER);
		e.getChannel().write(builder.build());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		LoggerUtils.serviceLogger.warn("Unexpected exception from downstream.", e.getCause());
		e.getChannel().close();
	}

}
