package com.zzz.draw.handler.impl;

import com.google.protobuf.MessageLite;
import com.zzz.draw.handler.AbstractMessageHandler;
import com.zzz.draw.pojo.UserInfo;
import com.zzz.draw.server.ServerApplication;
import com.zzz.draw.util.PlayerUtil;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.DrawMessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zha on 2018/4/17.
 */

public class LoginMessageHandler extends AbstractMessageHandler {

	
	
    @Override
    protected MessageLite exec(ChannelHandlerContext ctx, ProtocolMessage message) throws Exception {
        DrawMessageProto.LoginReq req = DrawMessageProto.LoginReq.parseFrom(message.getBuf());
        String name = req.getName();
        int userId = ServerApplication.addUserInfo(ctx, name);
        AttributeKey<Integer> attributeKey = AttributeKey.valueOf("userId");
        ctx.channel().attr(attributeKey).set(userId);
        message.setSessionId(userId);
        DrawMessageProto.LoginResp resp = DrawMessageProto.LoginResp.newBuilder()
                .setId(userId)
                .build();
       ServerApplication.context.getBean(PlayerUtil.class).luckyPlayer();
        return resp;
    }
}
