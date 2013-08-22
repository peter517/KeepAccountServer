/*
 * Copyright 2011 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.pengjun.ka.net;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.pengjun.ka.net.protobuf.KaProtocol.ArProtocol;

public class KaServerHandler extends SimpleChannelUpstreamHandler {

	private static final Logger logger = Logger.getLogger(KaServerHandler.class.getName());

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		if (e instanceof ChannelStateEvent) {
			logger.info(e.toString());
		}
		super.handleUpstream(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {

		ArProtocol arProtocol = (ArProtocol) e.getMessage();

		System.out.println("server getAccount()" + arProtocol.getAccount());
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
		logger.log(Level.WARNING, "Unexpected exception from downstream.", e.getCause());
		e.getChannel().close();
	}

}
