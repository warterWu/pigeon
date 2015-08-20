/**
 * Dianping.com Inc.
 * Copyright (c) 2003-2013 All Rights Reserved.
 */
package com.dianping.pigeon.remoting.netty.codec;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.ChannelHandlerContext;

public final class NettyCodecUtils {

	private NettyCodecUtils() {
	}

	@SuppressWarnings("unchecked")
	public static void setAttachment(ChannelHandlerContext ctx, int seq, Object value) {
		Map<Integer, Object> attachments = (Map<Integer, Object>) ctx.getAttachment();
		if (attachments == null) {
			attachments = createAttachment(ctx);
		}
		attachments.put(seq, value);
	}

	@SuppressWarnings("unchecked")
	public static Object getAttachmentNotRemove(ChannelHandlerContext ctx, int seq) {
		Map<Integer, Object> attachments = (Map<Integer, Object>) ctx.getAttachment();
		if (attachments == null) {
			//attachments = createAttachment(ctx);
			return null;
		}
		return attachments.get(seq);
	}

	@SuppressWarnings("unchecked")
	public static Object getAttachment(ChannelHandlerContext ctx, int seq) {
		Map<Integer, Object> attachments = (Map<Integer, Object>) ctx.getAttachment();
		if (attachments == null) {
			//attachments = createAttachment(ctx);
			return null;
		}
		return attachments.remove(seq);
	}

	@SuppressWarnings("unchecked")
	private static Map<Integer, Object> createAttachment(ChannelHandlerContext ctx) {
		synchronized (ctx) {
			Map<Integer, Object> attachments = (Map<Integer, Object>) ctx.getAttachment();
			if (attachments == null) {
				attachments = new ConcurrentHashMap<Integer, Object>();
				ctx.setAttachment(attachments);
			}
			return attachments;
		}
	}

}
