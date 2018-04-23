package com.zzz.draw.handler.impl;

import com.google.protobuf.MessageLite;
import com.zzz.draw.handler.AbstractMessageHandler;
import com.zzz.draw.pojo.UserInfo;
import com.zzz.draw.server.ServerApplication;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.DrawMessageProto.UserMessageReq;
import com.zzz.game.proto.DrawMessageProto.UserMessageResp;

import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by zha on 2018/4/17.
 */

public class UserMessageMessageHandler extends AbstractMessageHandler {

    @Override
    protected MessageLite exec(ChannelHandlerContext ctx, ProtocolMessage message) throws Exception {
       UserMessageReq req = UserMessageReq.parseFrom(message.getBuf());
       	String  messageStr =   req.getMessage();
       	UserInfo userInfo = ServerApplication.getUserInfo(message.getSessionId());
       	UserMessageResp.Builder builder = UserMessageResp.newBuilder();
       	builder.setMessage(messageStr);
       	builder.setName(userInfo.getName());
       	builder.setUserId(userInfo.getId());
       	Collection<UserInfo> list = ServerApplication.getUserList();
        byte[] buf = builder.build().toByteArray();
       	for (UserInfo userInfo2 : list) {
       		ProtocolMessage clone = message.clone();
       		clone.setBuf(buf);
       		clone.setSessionId(userInfo2.getId());
       		userInfo2.getCtx().writeAndFlush(clone);
		}
        return null;
    }
}
