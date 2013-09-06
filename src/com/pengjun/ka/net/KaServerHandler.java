package com.pengjun.ka.net;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.pengjun.ka.net.protobuf.KaProtocol.ArProtocol;
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

		ArProtocol arProtocol = (ArProtocol) e.getMessage();

		System.out.println("server getAccount() :" + arProtocol.getAccount());
		System.out.println("client ip : " + arProtocol.getCreateDate());
		ArProtocol.Builder builder = ArProtocol.newBuilder();
		builder.setAccount(arProtocol.getAccount() - 1);
		builder.setCreateDate(arProtocol.getCreateDate());
		builder.setId(1);
		builder.setTypeId(1);
		builder.setUpdateTime(arProtocol.getUpdateTime());

		e.getChannel().write(builder.build());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		LoggerUtils.serviceLogger.warn("Unexpected exception from downstream.", e.getCause());
		e.getChannel().close();
	}

}
